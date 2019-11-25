package com.example.umangburman.databindingwithlivedata.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.view.View;

import com.example.umangburman.databindingwithlivedata.Model.LoginUser;
import com.example.umangburman.databindingwithlivedata.Model.Product;

public class ProductViewModel extends ViewModel {

    public MutableLiveData<String> strProductName = new MutableLiveData<>();
    public MutableLiveData<String> strShortIntro = new MutableLiveData<>();

    private MutableLiveData<Product> productMutableLiveData;

    public MutableLiveData<Product> getProduct() {

        if (productMutableLiveData == null) {
            productMutableLiveData = new MutableLiveData<>();
        }
        return productMutableLiveData;

    }

    public void onClick(View view) {

        Product product  = new Product(strProductName.getValue(),strShortIntro.getValue());

        productMutableLiveData.setValue(product);

    }

}
