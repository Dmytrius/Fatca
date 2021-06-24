package com.handler.fatca.utils.senderfilemetadata;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FileFormatCdType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FileFormatCdType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="XML"/>
 *     &lt;enumeration value="TXT"/>
 *     &lt;enumeration value="PDF"/>
 *     &lt;enumeration value="RTF"/>
 *     &lt;enumeration value="JPG"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FileFormatCdType")
@XmlEnum
public enum FileFormatCdType {


    /**
     * XML
     * 
     */
    XML,

    /**
     * Plain Text
     * 
     */
    TXT,

    /**
     * PDF
     * 
     */
    PDF,

    /**
     * Rich Text Format
     * 
     */
    RTF,

    /**
     * Picture in JEPG Format
     * 
     */
    JPG;

    public String value() {
        return name();
    }

    public static FileFormatCdType fromValue(String v) {
        return valueOf(v);
    }

}
