package com.handler.fatca.utils.senderfilemetadata;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FATCAEntCommunicationTypeCdType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FATCAEntCommunicationTypeCdType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NTF"/>
 *     &lt;enumeration value="RPT"/>
 *     &lt;enumeration value="CAR"/>
 *     &lt;enumeration value="REG"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FATCAEntCommunicationTypeCdType")
@XmlEnum
public enum FATCAEntCommunicationTypeCdType {


    /**
     * FATCA_NOTIFICATION - FATCA Notification communication
     * 
     */
    NTF,

    /**
     * FATCA_REPORT - FATCA Report communication
     * 
     */
    RPT,

    /**
     * FATCA_COMPETENT_AUTHORITY_REQUEST - FATCA Competent Authority Request communication
     * 
     */
    CAR,

    /**
     * FATCA_REGISTRATION_DATA - FATCA Registration Data communication
     * 
     */
    REG;

    public String value() {
        return name();
    }

    public static FATCAEntCommunicationTypeCdType fromValue(String v) {
        return valueOf(v);
    }

}
