package com.handler.fatca.utils.intf;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

public interface IFATCAPackager extends IPackager {
	//this method signs an XML using streaming api (to calculate signature digest) and creates IDES pkg
    //Metadata fileFormatCd=XML, binaryEncodingSchemeCd=NONE, commCd=NTF|RPT|CAR|REG
	//commCd=null sets <FATCAEntCommunicationTypeCd> to RPT
	String signAndCreatePkgStreaming(String unsignedXml, PrivateKey senderPrivateKey, X509Certificate senderPublicCert,
			String senderGiin, String receiverGiin, X509Certificate receiverPublicCert, int taxyear, String commCd) throws Exception;
	
	//this method signs an XML using signature DOM api and creates IDES pkg - as DOM reads entire XML in memory, XML file size is restricted by heap 
    //Metadata fileFormatCd=XML, binaryEncodingSchemeCd=NONE, commCd=NTF|RPT|CAR|REG
	//commCd=null sets <FATCAEntCommunicationTypeCd> to RPT
	String signAndCreatePkg(String unsignedXml, PrivateKey senderPrivateKey, X509Certificate senderPublicCert,
			String senderGiin, String receiverGiin, X509Certificate receiverPublicCert, int taxyear, String commCd) throws Exception;
	
	//this method creates IDES pkg 
    //Metadata fileFormatCd=XML|TXT|PDF|JPG|RTF, binaryEncodingSchemeCd=NONE|BASE64, commCd=NTF|RPT|CAR|REG
	//commCd=null sets <FATCAEntCommunicationTypeCd> to RPT
	String createPkg(String signedFile, String senderGiin, String receiverGiin,  X509Certificate receiverPublicCert, int taxyear,
	String fileFormatCd, String binaryEncodingSchemeCd, String commCd) throws Exception;
	
	//this method wraps base64 binary in xml, signs and creates IDES pkg 
    //Metadata fileFormatCd=PDF|JPG|RTF, binaryEncodingSchemeCd=BASE64, commCd=NTF|RPT|CAR|REG
	//commCd=null sets <FATCAEntCommunicationTypeCd> to RPT
	String signBinaryFileAndCreatePkgStreaming(String unsignedBinaryDoc, PrivateKey senderPrivateKey, X509Certificate senderPublicCert,
	String senderGiin, String receiverGiin, X509Certificate receiverPublicCert, int taxyear,
			String fileFormatCd, String commCd) throws Exception;

	//this method wraps base64 binary in xml, signs and creates IDES pkg 
    //Metadata fileFormatCd=PDF|JPG|RTF, binaryEncodingSchemeCd=BASE64, commCd=NTF|RPT|CAR|REG
	//commCd=null sets <FATCAEntCommunicationTypeCd>RPT</<FATCAEntCommunicationTypeCd> in metadata
	String signBinaryFileAndCreatePkg(String unsignedBinaryDoc, PrivateKey senderPrivateKey, X509Certificate senderPublicCert,
	String senderGiin, String receiverGiin, X509Certificate receiverPublicCert, int taxyear,
	String fileFormatCd, String commCd) throws Exception;
	
	//this method wraps text in xml, signs and creates IDES pkg 
    //Metadata fileFormatCd=TXT, binaryEncodingSchemeCd=NONE, commCd=NTF|RPT|CAR|REG
	//commCd=null sets <FATCAEntCommunicationTypeCd>RPT</<FATCAEntCommunicationTypeCd> in metadata
	String signTextFileAndCreatePkgStreaming(String unsignedText, PrivateKey senderPrivateKey, X509Certificate senderPublicCert,
	String senderGiin, String receiverGiin, X509Certificate receiverPublicCert, int taxyear,
	String commCd) throws Exception;

	//this method wraps text in xml, signs and creates IDES pkg 
    //Metadata fileFormatCd=TXT, binaryEncodingSchemeCd=NONE, commCd=NTF|RPT|CAR|REG
	//commCd=null sets <FATCAEntCommunicationTypeCd>RPT</<FATCAEntCommunicationTypeCd> in metadata
	String signTextFileAndCreatePkg(String unsignedText, PrivateKey senderPrivateKey, X509Certificate senderPublicCert,
	String senderGiin, String receiverGiin, X509Certificate receiverPublicCert, int taxyear,
	String commCd) throws Exception;

	//this method takes zipped signed xml payload and creates IDES pkg 
	//fileFormat=null sets <FileFormatCd> to XML, binaryEncodingSchemeCd=null sets <BinaryEncodingSchemeCd> to NONE
	//commCd=null sets <FATCAEntCommunicationTypeCd> to RPT
	String encryptZipPkg(String xmlzipFilename, String senderGiin, String receiverGiin, X509Certificate receiverPublicCert,
	String approverGiin, X509Certificate approverPublicCert, int taxyear,
	String fileFormatCd, String binaryEncodingSchemeCd, String commCd) throws Exception;
	
	//this method signs an XML using streaming api (to calculate signature digest) and creates IDES pkg for approver - model1 option2 
    //Metadata fileFormatCd=XML, binaryEncodingSchemeCd=NONE, commCd=NTF|RPT|CAR|REG
	//commCd=null sets <FATCAEntCommunicationTypeCd> to RPT
	String signAndCreatePkgWithApproverStreaming(String unsignedXml, PrivateKey senderPrivateKey, X509Certificate senderPublicCert,
	String senderGiin, String receiverGiin, X509Certificate receiverPublicCert, String approverGiin, X509Certificate approverPublicCert,
	int taxyear, String commCd) throws Exception;
	
	//this method signs an XML using signature DOM api and creates IDES pkg for approver - model1 option2 - as DOM reads entire XML in memory, XML file size is restricted by heap 
    //Metadata fileFormatCd=XML, binaryEncodingSchemeCd=NONE, commCd=NTF|RPT|CAR|REG
	//commCd=null sets <FATCAEntCommunicationTypeCd> to RPT
	String signAndCreatePkgWithApprover(String unsignedXml, PrivateKey senderPrivateKey, X509Certificate senderPublicCert,
			String senderGiin, String receiverGiin, X509Certificate receiverPublicCert, String approverGiin, X509Certificate approverPublicCert, 
			int taxyear, String commCd) throws Exception;

	//this method wraps a binary file in XML, signs using signature DOM api and creates IDES pkg for approver - model1 option2 - as DOM reads entire XML in memory, XML file size is restricted by heap 
    //Metadata fileFormatCd=PDF|JPG|RTF, binaryEncodingSchemeCd=BASE64, commCd=NTF|RPT|CAR|REG
	//commCd=null sets <FATCAEntCommunicationTypeCd> to RPT
	String signBinaryFileAndCreatePkgWithApprover(String unsignedXml, PrivateKey senderPrivateKey, X509Certificate senderPublicCert,
			String senderGiin, String receiverGiin, X509Certificate receiverPublicCert, String approverGiin, X509Certificate approverPublicCert, 
			int taxyear, String fileFormet, String commCd) throws Exception;

	//this method wraps a binary file in XML, signs using streaming api (to calculate signature digest) and creates IDES pkg for approver - model1 option2 
    //Metadata fileFormatCd=PDF|JPG|RTF, binaryEncodingSchemeCd=BASE64, commCd=NTF|RPT|CAR|REG
	//commCd=null sets <FATCAEntCommunicationTypeCd> to RPT
	String signBinaryFileAndCreatePkgWithApproverStreaming(String unsignedXml, PrivateKey senderPrivateKey, X509Certificate senderPublicCert,
			String senderGiin, String receiverGiin, X509Certificate receiverPublicCert, String approverGiin, X509Certificate approverPublicCert, 
			int taxyear, String fileFormet, String commCd) throws Exception;
	
	//this method wraps a text file in XML, signs using signature DOM api and creates IDES pkg for approver - model1 option2 - as DOM reads entire XML in memory, XML file size is restricted by heap 
    //Metadata fileFormatCd=TXT, binaryEncodingSchemeCd=NONE, commCd=NTF|RPT|CAR|REG
	//commCd=null sets <FATCAEntCommunicationTypeCd> to RPT
	String signTextFileAndCreatePkgWithApprover(String unsignedXml, PrivateKey senderPrivateKey, X509Certificate senderPublicCert,
			String senderGiin, String receiverGiin, X509Certificate receiverPublicCert, String approverGiin, X509Certificate approverPublicCert, 
			int taxyear, String commCd) throws Exception;

	//this method wraps a text file in XML, signs using streaming api (to calculate signature digest) and creates IDES pkg for approver - model1 option2 
    //Metadata fileFormatCd=TXT, binaryEncodingSchemeCd=NONE, commCd=NTF|RPT|CAR|REG
	//commCd=null sets <FATCAEntCommunicationTypeCd> to RPT
	String signTextFileAndCreatePkgWithApproverStreaming(String unsignedXml, PrivateKey senderPrivateKey, X509Certificate senderPublicCert,
			String senderGiin, String receiverGiin, X509Certificate receiverPublicCert, String approverGiin, X509Certificate approverPublicCert, 
			int taxyear, String commCd) throws Exception;
	
	//this method creates IDES pkg for approver - model1 option2 
    //Metadata fileFormatCd=XML|TXT|PDF|JPG|RTF, binaryEncodingSchemeCd=NONE|BASE64, commCd=NTF|RPT|CAR|REG
	//fileFormat=null sets <FileFormatCd> to XML, binaryEncodingSchemeCd=null sets <BinaryEncodingSchemeCd> to NONE
	//commCd=null sets <FATCAEntCommunicationTypeCd> to RPT
	String createPkgWithApprover(String signedXmlFile, String senderGiin, String receiverGiin, X509Certificate receiverPublicCert,
			String approverGiin, X509Certificate approverPublicCert, int taxyear, String fileFormatCd, 
			String binaryEncodingSchemeCd, String commCd) throws Exception;
	
	//this method unpack an IDES pkg for approver - model1 option2 
	ArrayList<String> unpackForApprover(String idesPkgFile, String approverKeystoreType, String approverKeystoreFile,
			String approverKeystorePwd, String approverKeyPwd, String approverKeyAlias) throws Exception;
	
	//this method unpack an IDES pkg for approver - model1 option2 
	ArrayList<String> unpackForApprover(String idesPkgFile, PrivateKey approverPrivateKey) throws Exception;
	
	ArrayList<String> unencryptZipPkg(String pkgFile, PrivateKey privateKey, boolean isApprover) throws Exception;
}
