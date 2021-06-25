package com.handler.fatca.dto;


import javax.persistence.*;


@Entity
@Table(name="docs")
public class Doc {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String docName;
    private String docType;

    @Lob
    private String data;

    public Doc() {}

    public Doc(String docName, String docType, String data) {
        super();
        this.docName = docName;
        this.docType = docType;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
