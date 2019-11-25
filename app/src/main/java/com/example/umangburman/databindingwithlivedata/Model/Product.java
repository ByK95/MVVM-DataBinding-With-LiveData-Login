package com.example.umangburman.databindingwithlivedata.Model;

public class Product {
    private String strProductId;
    private String strProductName;
    private String strShortIntro;

    public Product() {
    }

    public Product(String strProductId, String strProductName, String strShortIntro) {
        this.strProductId = strProductId;
        this.strProductName = strProductName;
        this.strShortIntro = strShortIntro;
    }

    public String getStrProductId() {
        return strProductId;
    }

    public void setStrProductId(String strProductId) {
        this.strProductId = strProductId;
    }

    public String getstrProductName() {
        return strProductName;
    }

    public String getstrShortIntro() {
        return strShortIntro;
    }

    public void setstrProductName(String strProductName) {
        this.strProductName = strProductName;
    }

    public void setstrShortIntro(String strShortIntro) {
        this.strShortIntro = strShortIntro;
    }

}
