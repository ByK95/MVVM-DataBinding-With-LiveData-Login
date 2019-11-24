package com.example.umangburman.databindingwithlivedata.Model;

public class Product {
    private String strProductName;
    private String strShortIntro;

    public Product(String strProductName,String shortintro){
        strProductName = strProductName;
        strShortIntro = shortintro;
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
