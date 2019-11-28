package com.example.umangburman.databindingwithlivedata.Model;

import com.google.firebase.database.ServerValue;

import java.util.Date;

public class Product {
    private String strProductId;
    private String strProductName;
    private String strShortIntro;
    private String strCategory;
    private String strItemImageUrl;
    private String price;
    private String time;

    public Product() {
    }

    public Product(String strProductId, String strProductName, String strShortIntro, String strCategory, String strItemImageUrl, String price, String time) {
        this.strProductId = strProductId;
        this.strProductName = strProductName;
        this.strShortIntro = strShortIntro;
        this.strCategory = strCategory;
        this.strItemImageUrl = strItemImageUrl;
        this.price = price;
        this.time = time;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getStrItemImageUrl() {
        return strItemImageUrl;
    }

    public void setStrItemImageUrl(String strItemImageUrl) {
        this.strItemImageUrl = strItemImageUrl;
    }

    public boolean validate(){
        return getstrProductName()== null || getstrShortIntro() == null
                || getStrCategory() == null|| getPrice() == null;
    }

    public boolean validateCategory(){

        boolean _tf = false;

        if(!validate()) {
            if( getStrCategory().equals("Electronics")
                    || getStrCategory().equals("Cosmetic")
                    || getStrCategory().equals("Accessory")
                    || getStrCategory().equals("Home")){
                _tf=true;
            }
        }
        return _tf;
    }

    public boolean imageSelected(){

        return getStrItemImageUrl() != null;
    }
}
