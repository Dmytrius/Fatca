package com.handler.fatca.utils.senderfilemetadata;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BinaryEncodingSchemeCdType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BinaryEncodingSchemeCdType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NONE"/>
 *     &lt;enumeration value="BASE64"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BinaryEncodingSchemeCdType")
@XmlEnum
public enum BinaryEncodingSchemeCdType {


    /**
     * No Special Encoding
     * 
     */
    NONE("NONE"),

    /**
     * Base64 Encoded
     * 
     */
    @XmlEnumValue("BASE64")
    BASE_64("BASE64");
    private final String value;

    BinaryEncodingSchemeCdType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BinaryEncodingSchemeCdType fromValue(String v) {
        for (BinaryEncodingSchemeCdType c: BinaryEncodingSchemeCdType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
