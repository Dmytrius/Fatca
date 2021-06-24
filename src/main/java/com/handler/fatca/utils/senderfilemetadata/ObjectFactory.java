package com.handler.fatca.utils.senderfilemetadata;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fatca.senderfilemetadata package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FATCAEntitySenderId_QNAME = new QName("urn:fatca:idessenderfilemetadata", "FATCAEntitySenderId");
    private final static QName _FileRevisionInd_QNAME = new QName("urn:fatca:idessenderfilemetadata", "FileRevisionInd");
    private final static QName _FATCAIDESSenderFileMetadata_QNAME = new QName("urn:fatca:idessenderfilemetadata", "FATCAIDESSenderFileMetadata");
    private final static QName _BinaryEncodingSchemeCd_QNAME = new QName("urn:fatca:idessenderfilemetadata", "BinaryEncodingSchemeCd");
    private final static QName _FileCreateTs_QNAME = new QName("urn:fatca:idessenderfilemetadata", "FileCreateTs");
    private final static QName _FATCAEntCommunicationTypeCd_QNAME = new QName("urn:fatca:idessenderfilemetadata", "FATCAEntCommunicationTypeCd");
    private final static QName _FATCAEntityReceiverId_QNAME = new QName("urn:fatca:idessenderfilemetadata", "FATCAEntityReceiverId");
    private final static QName _TaxYear_QNAME = new QName("urn:fatca:idessenderfilemetadata", "TaxYear");
    private final static QName _OriginalIDESTransmissionId_QNAME = new QName("urn:fatca:idessenderfilemetadata", "OriginalIDESTransmissionId");
    private final static QName _FileFormatCd_QNAME = new QName("urn:fatca:idessenderfilemetadata", "FileFormatCd");
    private final static QName _SenderFileId_QNAME = new QName("urn:fatca:idessenderfilemetadata", "SenderFileId");
    private final static QName _SenderContactEmailAddressTxt_QNAME = new QName("urn:fatca:idessenderfilemetadata", "SenderContactEmailAddressTxt");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fatca.senderfilemetadata
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FATCAIDESSenderFileMetadataType }
     * 
     */
    public FATCAIDESSenderFileMetadataType createFATCAIDESSenderFileMetadataType() {
        return new FATCAIDESSenderFileMetadataType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:fatca:idessenderfilemetadata", name = "FATCAEntitySenderId")
    public JAXBElement<String> createFATCAEntitySenderId(String value) {
        return new JAXBElement<String>(_FATCAEntitySenderId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:fatca:idessenderfilemetadata", name = "FileRevisionInd")
    public JAXBElement<Boolean> createFileRevisionInd(Boolean value) {
        return new JAXBElement<Boolean>(_FileRevisionInd_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FATCAIDESSenderFileMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:fatca:idessenderfilemetadata", name = "FATCAIDESSenderFileMetadata")
    public JAXBElement<FATCAIDESSenderFileMetadataType> createFATCAIDESSenderFileMetadata(FATCAIDESSenderFileMetadataType value) {
        return new JAXBElement<FATCAIDESSenderFileMetadataType>(_FATCAIDESSenderFileMetadata_QNAME, FATCAIDESSenderFileMetadataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BinaryEncodingSchemeCdType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:fatca:idessenderfilemetadata", name = "BinaryEncodingSchemeCd")
    public JAXBElement<BinaryEncodingSchemeCdType> createBinaryEncodingSchemeCd(BinaryEncodingSchemeCdType value) {
        return new JAXBElement<BinaryEncodingSchemeCdType>(_BinaryEncodingSchemeCd_QNAME, BinaryEncodingSchemeCdType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:fatca:idessenderfilemetadata", name = "FileCreateTs")
    public JAXBElement<String> createFileCreateTs(String value) {
        return new JAXBElement<String>(_FileCreateTs_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FATCAEntCommunicationTypeCdType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:fatca:idessenderfilemetadata", name = "FATCAEntCommunicationTypeCd")
    public JAXBElement<FATCAEntCommunicationTypeCdType> createFATCAEntCommunicationTypeCd(FATCAEntCommunicationTypeCdType value) {
        return new JAXBElement<FATCAEntCommunicationTypeCdType>(_FATCAEntCommunicationTypeCd_QNAME, FATCAEntCommunicationTypeCdType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:fatca:idessenderfilemetadata", name = "FATCAEntityReceiverId")
    public JAXBElement<String> createFATCAEntityReceiverId(String value) {
        return new JAXBElement<String>(_FATCAEntityReceiverId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:fatca:idessenderfilemetadata", name = "TaxYear")
    public JAXBElement<XMLGregorianCalendar> createTaxYear(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_TaxYear_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:fatca:idessenderfilemetadata", name = "OriginalIDESTransmissionId")
    public JAXBElement<String> createOriginalIDESTransmissionId(String value) {
        return new JAXBElement<String>(_OriginalIDESTransmissionId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FileFormatCdType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:fatca:idessenderfilemetadata", name = "FileFormatCd")
    public JAXBElement<FileFormatCdType> createFileFormatCd(FileFormatCdType value) {
        return new JAXBElement<FileFormatCdType>(_FileFormatCd_QNAME, FileFormatCdType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:fatca:idessenderfilemetadata", name = "SenderFileId")
    public JAXBElement<String> createSenderFileId(String value) {
        return new JAXBElement<String>(_SenderFileId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:fatca:idessenderfilemetadata", name = "SenderContactEmailAddressTxt")
    public JAXBElement<String> createSenderContactEmailAddressTxt(String value) {
        return new JAXBElement<String>(_SenderContactEmailAddressTxt_QNAME, String.class, null, value);
    }

}
