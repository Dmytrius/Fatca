package com.handler.fatca.utils.intf;

import java.security.PublicKey;
import java.security.cert.X509Certificate;

public interface ISignatureVerifier {
	//Enveloping signature - meaning payload is enclosed within 'Signature' element

	//DOM based signing. DOM reads the entire xml in memory and thus xml file size is limited by heap size. 
	//JDK supports DOM based XML signing 
	boolean verifySignature(String signedXmlFile) throws Exception;
	boolean verifySignature(String signedXmlFile, PublicKey sigPublicKey) throws Exception;
	boolean verifySignature(String signedXmlFile, X509Certificate sigCert) throws Exception;
    
    //for signature verification, streaming based API can be used to sign very large XML file.
	boolean verifySignatureStreaming(String signedXmlFile) throws Exception;
	boolean verifySignatureStreaming(String signedXmlFile, PublicKey sigPublicKey) throws Exception;
	boolean verifySignatureStreaming(String signedXmlFile, X509Certificate sigCert) throws Exception;
	
	boolean getVerificationFlag();
}
