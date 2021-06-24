package com.handler.fatca.service;

import com.handler.fatca.dto.Doc;
import com.handler.fatca.repository.DocRepository;
import com.handler.fatca.utils.UtilShared;
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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class DocStorageService {
    @Autowired
    private DocRepository docRepository;

    @Value("${application.validationSchema.schema-file:src/main/resources/schema/FATCASchema2.0/FatcaXML_v2.0.xsd}")
    private String schemaFile;

    @Value("${application.validationSchema.start-element:{urn:oecd:ties:fatca:v2}FATCA_OECD}")
    private String startElem;

    public Doc saveFile(MultipartFile file) {
        String docname = file.getOriginalFilename();
        try {
            if(processValidateSchema(new String(file.getBytes()))) {
                Doc doc = new Doc(docname, file.getContentType(), file.getBytes());
                return docRepository.save(doc);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
    public Optional<Doc> getFile(Integer fileId) {
        return docRepository.findById(fileId);
    }
    public List<Doc> getFiles(){
        return docRepository.findAll();
    }

    private boolean processValidateSchema(String xmlFile) throws Exception {
        if (xmlFile == null || schemaFile == null) {
            log.error("xmlFile or schemaFile is null [" + xmlFile + "]");
            return false;
        }
        else {
            return validateSchema(xmlFile, schemaFile, startElem);
        }
    }

    private static boolean validateSchema(String xmlFile, String schemaFile, String startElem) throws Exception {
        log.debug("--> validateSchema(). xmlFile=" + xmlFile + ", schemaFile=" + schemaFile + ", startElem=" + startElem);
        boolean success = false;
        XMLStreamReader reader = null;
        BufferedReader br = null;
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
            br = new BufferedReader(new StringReader(xmlFile));
            reader = XMLInputFactory.newFactory().createXMLStreamReader(br);
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
            log.debug("StartElement=" + reader.getName());
            log.debug("vaLidation about to start. time=" + new Date());
            if (schemaFile.startsWith("http"))
                SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema").newSchema(new URL(schemaFile))
                        .newValidator().validate(new StAXSource(reader));
            else
                SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema").newSchema(new File(schemaFile))
                        .newValidator().validate(new StAXSource(reader));
            success = true;
            reader.close();
            br.close();
            reader = null;
            br = null;
        } catch(Exception e) {
            log.error(e.getStackTrace().toString());
        } finally {
            if (reader != null) reader.close();
            if (br != null) br.close();
        }
        log.debug("Schema Validation Success=" + success + ", xmlFile=" + xmlFile + ", schemaFile=" + schemaFile + ", startElem=" + startElem);
        log.debug("<-- validateSchema()");
        return success;
    }
}
