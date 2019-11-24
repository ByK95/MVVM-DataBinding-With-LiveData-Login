package com.example.umangburman.databindingwithlivedata.Model;

public class Product {
    private String strstrProductName;
    private String productShortIntro;

    public Product(String strProductName,String shortintro){
        strProductName = strProductName;
        productShortIntro = shortintro;
    }

    public String getstrProductName() {
        return strProductName;
    }

    public String getProductShortIntro() {
        return productShortIntro;
    }

    public void setstrProductName(String strProductName) {
        this.strProductName = strProductName;
    }

    public void setProductShortIntro(String productShortIntro) {
        this.productShortIntro = productShortIntro;
    }

}
