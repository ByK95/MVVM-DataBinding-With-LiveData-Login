package com.example.umangburman.databindingwithlivedata.Model;

import android.support.annotation.NonNull;

import com.google.firebase.database.Exclude;
import java.util.Map;

public class Product {
    private String strProductId;
    private String strProductName;
    private String strShortIntro;
    private String strCategory;
    private String strItemImageUrl;
    private Long price;
    private Map<String, String> timestamp;

    public Product(String strProductId, String strProductName, String strShortIntro, String strCategory, String strItemImageUrl, Long price, Map<String, String> timestamp) {
        this.strProductId = strProductId;
        this.strProductName = strProductName;
        this.strShortIntro = strShortIntro;
        this.strCategory = strCategory;
        this.strItemImageUrl = strItemImageUrl;
        this.price = price;
        this.timestamp = timestamp;
    }

    public String getStrProductName() {
        return strProductName;
    }

    public void setStrProductName(String strProductName) {
        this.strProductName = strProductName;
    }

    public String getStrShortIntro() {
        return strShortIntro;
    }

    public void setStrShortIntro(String strShortIntro) {
        this.strShortIntro = strShortIntro;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Map<String, String> getTimestamp() {
        return timestamp;
    }


    public void setTimestamp(Map<String, String> timestamp) {
        this.timestamp = timestamp;
    }


    public String getStrCategory() {
        return strCategory;
    }


    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }


    public String getStrProductId() {
        return strProductId;
    }


    public void setStrProductId(String strProductId) {
        this.strProductId = strProductId;
    }

    @Exclude
    public String getstrProductName() {
        return strProductName;
    }

    @Exclude
    public String getstrShortIntro() {
        return strShortIntro;
    }

    @Exclude
    public void setstrProductName(String strProductName) {
        this.strProductName = strProductName;
    }

    @Exclude
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
