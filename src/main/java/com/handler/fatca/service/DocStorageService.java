package com.handler.fatca.service;

import com.handler.fatca.dto.Doc;
import com.handler.fatca.repository.DocRepository;
import com.handler.fatca.utils.UtilShared;
import com.handler.fatca.utils.intf.IMetadata;
import com.handler.fatca.utils.intf.ISigner;
import com.handler.fatca.utils.senderfilemetadata.BinaryEncodingSchemeCdType;
import com.handler.fatca.utils.senderfilemetadata.FileFormatCdType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.file.*;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@Slf4j
public class DocStorageService {
    @Autowired
    private DocRepository docRepository;

    @Value("${application.validation-schema.schema-file:src/main/resources/schema/FATCASchema2.0/FatcaXML_v2.0.xsd}")
    private String schemaFile;

    @Value("${application.validationSchema.start-element:{urn:oecd:ties:fatca:v2}FATCA_OECD}")
    private String startElem;

//    @Value("${application.certs.sender-public-cert}")
    private X509Certificate senderPublicCert;

//    @Value("${application.certs.receiver-public-cert}")
    private X509Certificate receiverPublicCert;

    @Value("${application.giin.sender-giin}")
    private String senderGiin;

    @Value("${application.giin.receiver-giin}")
    private String receiverGiin;

    @Value("${application.giin.approver-giin}")
    private String approverGiin;

//    @Value("${application.keys.sender-private-key}")
    private PrivateKey senderPrivateKey;

    @Value("${application.tax-year}")
    private Integer taxyear;


    private String commType;
    private ISigner signer;
    private IMetadata metadata = null;
    private SimpleDateFormat sdfFileName = new SimpleDateFormat("yyyyMMdd'T'HHmmssSSS'Z'");

    public Doc saveFile(MultipartFile file) {
        String docname = file.getOriginalFilename();
        try {
            if(validateSchema(new String(file.getBytes()))) {
                Doc doc = new Doc(docname, file.getContentType(), (new String(signAndCreatePkg(new String(file.getBytes())))));
//                signAndCreatePkg(new String(file.getBytes()));
                return docRepository.save(doc);
            }
        } catch (Exception e) {
            log.error(e.toString());
        }
        return null;
    }
    public Optional<Doc> getFile(Integer fileId) {
        return docRepository.findById(fileId);
    }
    public List<Doc> getFiles(){
        return docRepository.findAll();
    }

    private boolean validateSchema(String xmlFile) throws Exception {
        try {
            QName qname = null;
            if (startElem != null) {
                String elem = null, ns = null;
                Pattern pattern = Pattern.compile("\\{([^}]*)\\}|.+");
                Matcher matcher = pattern.matcher(startElem);
                while (matcher.find()) {
                    if (matcher.group().startsWith("{"))
                        ns = matcher.group(1);
                    else {
                        elem = matcher.group();
                        break;
                    }
                }
                if (ns != null)
                    qname = new QName(ns, elem);
            }
            BufferedReader br = new BufferedReader(new StringReader(xmlFile));
            XMLStreamReader reader = XMLInputFactory.newFactory().createXMLStreamReader(br);
            while(reader.hasNext()) {
                if (reader.getEventType() == XMLStreamConstants.START_ELEMENT) {
                    if (startElem == null)
                        break;
                    if (qname == null && startElem.equalsIgnoreCase(reader.getName().getLocalPart()))
                        break;
                    if (qname != null && qname.equals(reader.getName()))
                        break;
                }
                reader.next();
            }
            if (reader.getEventType() == XMLStreamConstants.END_DOCUMENT)
                throw new Exception(startElem + " element not found");
            SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema").newSchema(new File(schemaFile)).newValidator().validate(new StAXSource(reader));
            if (schemaFile.startsWith("http")) {
                SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema").newSchema(new URL(schemaFile))
                        .newValidator().validate(new StAXSource(reader));
            }
            reader.close();
            br.close();
            return true;
        } catch(Exception e) {
            log.error(e.getStackTrace().toString());
            return false;
        }
    }

    private String signAndCreatePkg(String unsignedXml) throws Exception {
        approverGiin = null;
        X509Certificate approverPublicCert = null;
        return signAndCreatePkgWithApprover(unsignedXml, senderPrivateKey, senderPublicCert, senderGiin, receiverGiin,
                receiverPublicCert, approverGiin, approverPublicCert, taxyear, FileFormatCdType.XML.value(),
                BinaryEncodingSchemeCdType.NONE.value(), commType, false);
    }

    private String signAndCreatePkgWithApprover(String unsignedXml, PrivateKey senderPrivateKey, X509Certificate senderPublicCert,
                                                String senderGiin, String receiverGiin, X509Certificate receiverPublicCert, String approverGiin, X509Certificate approverPublicCert,
                                                int taxyear, String fileFormat, String binaryEncoding, String commType, boolean isStreaming) throws Exception {
        String signedxml = UtilShared.getTmpFileName(unsignedXml, "signed.xml");
        boolean success;
        String ret = null;
        if (isStreaming)
            success = signer.signXmlFileStreaming(unsignedXml, signedxml, senderPrivateKey, senderPublicCert);
        else
            success = signer.signXmlFile(unsignedXml, signedxml, senderPrivateKey, senderPublicCert);
        if (success)
            ret = createPkgWithApprover(signedxml, senderGiin, receiverGiin, receiverPublicCert, approverGiin, approverPublicCert, taxyear,
                    fileFormat, binaryEncoding, commType);
            UtilShared.renameToNextSequencedFile(signedxml, null, unsignedXml, ".signed.xml");
        return ret;
    }

    private String createPkgWithApprover(String signedXmlFile, String senderGiin, String receiverGiin, X509Certificate receiverPublicCert,
                                         String approverGiin, X509Certificate approvercert, int taxyear, String fileFormat,
                                         String binaryEncoding, String commType) throws Exception {
        if (fileFormat != null && binaryEncoding != null) {
            if (FileFormatCdType.TXT.value().equalsIgnoreCase(fileFormat) || FileFormatCdType.XML.value().equalsIgnoreCase(fileFormat)) {
                if (!BinaryEncodingSchemeCdType.NONE.value().equalsIgnoreCase(binaryEncoding))
                    throw new Exception("incorrect combination. fileFormat=" + fileFormat + ", binaryEncoding=" + binaryEncoding);
            } else {
                if (!BinaryEncodingSchemeCdType.BASE_64.value().equalsIgnoreCase(binaryEncoding))
                    throw new Exception("incorrect combination. fileFormat=" + fileFormat + ", binaryEncoding=" + binaryEncoding);
            }
        }
        String folder = new File(signedXmlFile).getAbsoluteFile().getParent();
        String xmlzipFilename = UtilShared.getTmpFileName(folder, senderGiin, "Payload.zip");
        createZipPkg(signedXmlFile, senderGiin, xmlzipFilename);
        String idesOutFile = encryptZipPkg(xmlzipFilename, senderGiin, receiverGiin, receiverPublicCert,
                approverGiin, approvercert, taxyear, fileFormat, binaryEncoding, commType);
        File file = new File(xmlzipFilename);
        if (file.exists()&&!file.delete())file.deleteOnExit();
        return idesOutFile;
    }

    private String encryptZipPkg(String xmlzipFilename, String senderGiin, String receiverGiin, X509Certificate receiverPublicCert,
                                 String approverGiin, X509Certificate approverPublicCert, int taxyear, String fileFormat, String binaryEncoding,
                                 String commType) throws Exception {
        boolean success = false;
        String folder = new File(xmlzipFilename).getAbsoluteFile().getParent();
        Date date = new Date();
        String idesOutFile = getPkgFileName(folder, senderGiin);
        File file = new File(idesOutFile);
        String senderFileId = file.getName();
        String metadatafile = null;
        metadatafile = metadata.createMetadata(folder, senderGiin, receiverGiin, commType, senderFileId, fileFormat, binaryEncoding, date, taxyear);
        Certificate[] certs = null;
        String[] encryptedAESKeyOutFiles = null;
        if (approverPublicCert != null && approverGiin != null) {
            certs = new X509Certificate[] {receiverPublicCert, approverPublicCert};
            encryptedAESKeyOutFiles = new String[]{UtilShared.getTmpFileName(folder, receiverGiin, "Key"),
                    UtilShared.getTmpFileName(folder, approverGiin, "Key")};
        } else if (receiverPublicCert != null){
            certs = new X509Certificate[] {receiverPublicCert};
            encryptedAESKeyOutFiles = new String[]{UtilShared.getTmpFileName(folder, receiverGiin, "Key")};
        } else
            throw new Exception ("both approvingEntityCert and receivingEntityCert is null");
        String xmlZippedEncryptedFile = UtilShared.getTmpFileName(folder, senderGiin, "Payload");
        success = encrypt(xmlzipFilename, xmlZippedEncryptedFile, certs, encryptedAESKeyOutFiles);
        if (! success)
            throw new Exception("encryption failed. xmlzipFilename=" + xmlzipFilename);
        int count = 0;
        String[] infiles = new String[encryptedAESKeyOutFiles.length + 2];
        for (count = 0; count < encryptedAESKeyOutFiles.length; count++)
            infiles[count] = encryptedAESKeyOutFiles[count];
        infiles[count++] =  xmlZippedEncryptedFile;
        infiles[count] = metadatafile;
        success = createZipFile(infiles, idesOutFile);
        if (success) {
            if (encryptedAESKeyOutFiles.length == 2)
                success = renameZipEntries(idesOutFile, new String[]{getFileName(xmlZippedEncryptedFile), getFileName(metadatafile),
                                getFileName(encryptedAESKeyOutFiles[0]), getFileName(encryptedAESKeyOutFiles[1])},
                        new String[]{senderGiin + "_Payload", senderGiin + "_Metadata.xml",
                                receiverGiin + "_Key", approverGiin + "_Key"});
            else
                success = renameZipEntries(idesOutFile, new String[]{getFileName(xmlZippedEncryptedFile), getFileName(metadatafile),
                                getFileName(encryptedAESKeyOutFiles[0])},
                        new String[]{senderGiin + "_Payload", senderGiin + "_Metadata.xml",
                                receiverGiin + "_Key"});
        }
        if (!success)
            throw new Exception("unable to create zip file " + idesOutFile);
        for (int i = 0; i < infiles.length; i++) {
            file = new File(infiles[i]);
            if (file.exists()&&!file.delete()) file.deleteOnExit();
        }
        log.debug("<-- encryptZipPkg()");
        return idesOutFile;
    }

    private boolean encrypt(String zippedSignedPlainTextFile, String cipherTextOutFile, Certificate[] receiversPublicCert,
                            String[] encryptedAESKeyOutFiles) throws Exception {
        PublicKey[] pubkeys = new PublicKey[receiversPublicCert.length];
        for (int i = 0; i < receiversPublicCert.length; i++)
            pubkeys[i] = receiversPublicCert[i].getPublicKey();
        boolean flag = encrypt(zippedSignedPlainTextFile, cipherTextOutFile, (Certificate[]) pubkeys, encryptedAESKeyOutFiles);
        return flag;
    }

    private boolean renameZipEntries(String zipFile, String[] entryNames, String[] newEntryNames) throws Exception {
        boolean ret = false;
        if (entryNames.length != newEntryNames.length)
            throw new Exception("renameZipEntries entryNames and newEntryNames length should be same");
        HashMap<String, String> props = new HashMap<String, String>();
        props.put("create", "false");
        URI zipDisk = URI.create("jar:" + new File(zipFile).toURI());
        FileSystem zipfs = FileSystems.newFileSystem(zipDisk, props);
        Path pathInZipfile, renamedZipEntry;
        for (int i = 0; i < entryNames.length; i++) {
            pathInZipfile = zipfs.getPath(entryNames[i]);
            renamedZipEntry = zipfs.getPath(newEntryNames[i]);
            Files.move(pathInZipfile, renamedZipEntry, StandardCopyOption.ATOMIC_MOVE);
        }
        zipfs.close();
        ret = true;
        log.debug("<-- renameZipEntries()");
        return ret;
    }

    private synchronized String getPkgFileName(String folder, String senderGiin) throws Exception {
        if (!"".equals(folder) && !folder.endsWith("/") && !folder.endsWith("\\"))
            folder += File.separator;
        File file;
        String outfile;
        int attempts = UtilShared.maxAttemptsToCreateNewFile;
        while(true) {
            outfile = folder + sdfFileName.format(new Date(System.currentTimeMillis())) + "_" + senderGiin + ".zip";
            file = new File(outfile);
            if (!file.exists()) {
                if (file.createNewFile() || attempts-- <= 0)
                    break;
            }
            Thread.sleep(100);
        }
        if (attempts <= 0)
            throw new Exception ("Unable to getPkgFileName() - file=" + file.getAbsolutePath());
        return outfile;
    }

    private void createZipPkg(String signedXmlFile, String filePrefix, String outputZipFilename) throws Exception {
        boolean success = false;
        String folder = new File(signedXmlFile).getAbsoluteFile().getParent();
        if (outputZipFilename == null)
            outputZipFilename = UtilShared.getTmpFileName(folder, filePrefix, "Payload.zip");
        success = createZipFile(new String[]{signedXmlFile}, outputZipFilename);
        if (success)
            success = renameZipEntry(outputZipFilename, getFileName(signedXmlFile), filePrefix + "_Payload.xml");
        if (!success)
            throw new Exception("uanble to create " + outputZipFilename);
    }

    private String getFileName(String filename) {
        File f = new File(filename);
        return f.getName();
    }

    private boolean renameZipEntry(String zipFile, String entryName, String newEntryName) throws Exception {
        boolean ret = false;
        HashMap<String, String> props = new HashMap<String, String>();
        props.put("create", "false");
        URI zipDisk = URI.create("jar:" + new File(zipFile).toURI());
        FileSystem zipfs = FileSystems.newFileSystem(zipDisk, props);
        Path pathInZipfile = zipfs.getPath(entryName);
        Path renamedZipEntry = zipfs.getPath(newEntryName);
        Files.move(pathInZipfile, renamedZipEntry, StandardCopyOption.ATOMIC_MOVE);
        zipfs.close();
        ret = true;
        return ret;
    }

    private boolean createZipFile(String[] inFiles, String outFile) throws Exception {
        BufferedInputStream bis = null;
        ZipOutputStream zos = null;
        ZipEntry zipEntry;
        int len;
        boolean ret = false;
        String infile;
        byte[] buf = new byte[UtilShared.defaultBufSize];
        try {
            zos = new ZipOutputStream(new FileOutputStream(new File(outFile)));
            zos.setLevel(Deflater.BEST_COMPRESSION);
            for (int i = 0; i < inFiles.length; i++) {
                // drop folder names
                infile = inFiles[i];
                len = infile.lastIndexOf("/");
                if (len == -1)
                    len = infile.lastIndexOf("\\");
                if (len != -1)
                    infile = infile.substring(len+1);
                zipEntry = new ZipEntry(infile);
                zos.putNextEntry(zipEntry);
                bis = new BufferedInputStream(new FileInputStream(new File(inFiles[i])));
                while((len = bis.read(buf)) != -1)
                    zos.write(buf, 0, len);
                bis.close(); bis = null;
                zos.closeEntry();
            }
            zos.close(); zos = null;
            ret = true;
        } finally {
            if (bis != null) try{bis.close();}catch(Exception e) {}
            if (zos != null) try{zos.close();}catch(Exception e) {}
        }
        return ret;
    }
}
