package com.handler.fatca.utils.senderfilemetadata;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 * 				
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;Component xmlns="urn:fatca:idessenderfilemetadata" xmlns:xmime="http://www.w3.org/2005/05/xmlmime" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;
 * 					&lt;DictionaryEntryNm&gt;FATCA IDES Sender File Metadata Type&lt;/DictionaryEntryNm&gt;
 * 					&lt;MajorVersionNum&gt;1&lt;/MajorVersionNum&gt;
 * 					&lt;MinorVersionNum&gt;0&lt;/MinorVersionNum&gt;
 * 					&lt;VersionEffectiveBeginDt&gt;2014-08-29&lt;/VersionEffectiveBeginDt&gt;
 * 					&lt;VersionDescriptionTxt&gt;Initial Version&lt;/VersionDescriptionTxt&gt;
 * 					&lt;Description&gt;Type for a group that defines the information contained in the FATCA IDES Sender File Metadata&lt;/Description&gt;
 * 				&lt;/Component&gt;
 * </pre>
 * 
 * 			
 * 
 * <p>Java class for FATCAIDESSenderFileMetadataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FATCAIDESSenderFileMetadataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:fatca:idessenderfilemetadata}FATCAEntitySenderId"/>
 *         &lt;element ref="{urn:fatca:idessenderfilemetadata}FATCAEntityReceiverId"/>
 *         &lt;element ref="{urn:fatca:idessenderfilemetadata}FATCAEntCommunicationTypeCd"/>
 *         &lt;element ref="{urn:fatca:idessenderfilemetadata}SenderFileId"/>
 *         &lt;element ref="{urn:fatca:idessenderfilemetadata}FileFormatCd" minOccurs="0"/>
 *         &lt;element ref="{urn:fatca:idessenderfilemetadata}BinaryEncodingSchemeCd" minOccurs="0"/>
 *         &lt;element ref="{urn:fatca:idessenderfilemetadata}FileCreateTs"/>
 *         &lt;element ref="{urn:fatca:idessenderfilemetadata}TaxYear"/>
 *         &lt;element ref="{urn:fatca:idessenderfilemetadata}FileRevisionInd"/>
 *         &lt;element ref="{urn:fatca:idessenderfilemetadata}OriginalIDESTransmissionId" minOccurs="0"/>
 *         &lt;element ref="{urn:fatca:idessenderfilemetadata}SenderContactEmailAddressTxt" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FATCAIDESSenderFileMetadataType", propOrder = {
    "fatcaEntitySenderId",
    "fatcaEntityReceiverId",
    "fatcaEntCommunicationTypeCd",
    "senderFileId",
    "fileFormatCd",
    "binaryEncodingSchemeCd",
    "fileCreateTs",
    "taxYear",
    "fileRevisionInd",
    "originalIDESTransmissionId",
    "senderContactEmailAddressTxt"
})
public class FATCAIDESSenderFileMetadataType {

    @XmlElement(name = "FATCAEntitySenderId", required = true)
    protected String fatcaEntitySenderId;
    @XmlElement(name = "FATCAEntityReceiverId", required = true)
    protected String fatcaEntityReceiverId;
    @XmlElement(name = "FATCAEntCommunicationTypeCd", required = true)
    @XmlSchemaType(name = "string")
    protected FATCAEntCommunicationTypeCdType fatcaEntCommunicationTypeCd;
    @XmlElement(name = "SenderFileId", required = true)
    protected String senderFileId;
    @XmlElement(name = "FileFormatCd")
    @XmlSchemaType(name = "string")
    protected FileFormatCdType fileFormatCd;
    @XmlElement(name = "BinaryEncodingSchemeCd")
    @XmlSchemaType(name = "string")
    protected BinaryEncodingSchemeCdType binaryEncodingSchemeCd;
    @XmlElement(name = "FileCreateTs", required = true)
    protected String fileCreateTs;
    @XmlElement(name = "TaxYear", required = true)
    @XmlSchemaType(name = "gYear")
    protected XMLGregorianCalendar taxYear;
    @XmlElement(name = "FileRevisionInd")
    protected boolean fileRevisionInd;
    @XmlElement(name = "OriginalIDESTransmissionId")
    protected String originalIDESTransmissionId;
    @XmlElement(name = "SenderContactEmailAddressTxt")
    protected String senderContactEmailAddressTxt;

    /**
     * Gets the value of the fatcaEntitySenderId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFATCAEntitySenderId() {
        return fatcaEntitySenderId;
    }

    /**
     * Sets the value of the fatcaEntitySenderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFATCAEntitySenderId(String value) {
        this.fatcaEntitySenderId = value;
    }

    /**
     * Gets the value of the fatcaEntityReceiverId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFATCAEntityReceiverId() {
        return fatcaEntityReceiverId;
    }

    /**
     * Sets the value of the fatcaEntityReceiverId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFATCAEntityReceiverId(String value) {
        this.fatcaEntityReceiverId = value;
    }

    /**
     * Gets the value of the fatcaEntCommunicationTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link FATCAEntCommunicationTypeCdType }
     *     
     */
    public FATCAEntCommunicationTypeCdType getFATCAEntCommunicationTypeCd() {
        return fatcaEntCommunicationTypeCd;
    }

    /**
     * Sets the value of the fatcaEntCommunicationTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link FATCAEntCommunicationTypeCdType }
     *     
     */
    public void setFATCAEntCommunicationTypeCd(FATCAEntCommunicationTypeCdType value) {
        this.fatcaEntCommunicationTypeCd = value;
    }

    /**
     * Gets the value of the senderFileId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderFileId() {
        return senderFileId;
    }

    /**
     * Sets the value of the senderFileId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderFileId(String value) {
        this.senderFileId = value;
    }

    /**
     * Gets the value of the fileFormatCd property.
     * 
     * @return
     *     possible object is
     *     {@link FileFormatCdType }
     *     
     */
    public FileFormatCdType getFileFormatCd() {
        return fileFormatCd;
    }

    /**
     * Sets the value of the fileFormatCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileFormatCdType }
     *     
     */
    public void setFileFormatCd(FileFormatCdType value) {
        this.fileFormatCd = value;
    }

    /**
     * Gets the value of the binaryEncodingSchemeCd property.
     * 
     * @return
     *     possible object is
     *     {@link BinaryEncodingSchemeCdType }
     *     
     */
    public BinaryEncodingSchemeCdType getBinaryEncodingSchemeCd() {
        return binaryEncodingSchemeCd;
    }

    /**
     * Sets the value of the binaryEncodingSchemeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link BinaryEncodingSchemeCdType }
     *     
     */
    public void setBinaryEncodingSchemeCd(BinaryEncodingSchemeCdType value) {
        this.binaryEncodingSchemeCd = value;
    }

    /**
     * Gets the value of the fileCreateTs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileCreateTs() {
        return fileCreateTs;
    }

    /**
     * Sets the value of the fileCreateTs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileCreateTs(String value) {
        this.fileCreateTs = value;
    }

    /**
     * Gets the value of the taxYear property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTaxYear() {
        return taxYear;
    }

    /**
     * Sets the value of the taxYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTaxYear(XMLGregorianCalendar value) {
        this.taxYear = value;
    }

    /**
     * Gets the value of the fileRevisionInd property.
     * 
     */
    public boolean isFileRevisionInd() {
        return fileRevisionInd;
    }

    /**
     * Sets the value of the fileRevisionInd property.
     * 
     */
    public void setFileRevisionInd(boolean value) {
        this.fileRevisionInd = value;
    }

    /**
     * Gets the value of the originalIDESTransmissionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginalIDESTransmissionId() {
        return originalIDESTransmissionId;
    }

    /**
     * Sets the value of the originalIDESTransmissionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginalIDESTransmissionId(String value) {
        this.originalIDESTransmissionId = value;
    }

    /**
     * Gets the value of the senderContactEmailAddressTxt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderContactEmailAddressTxt() {
        return senderContactEmailAddressTxt;
    }

    /**
     * Sets the value of the senderContactEmailAddressTxt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderContactEmailAddressTxt(String value) {
        this.senderContactEmailAddressTxt = value;
    }

}
