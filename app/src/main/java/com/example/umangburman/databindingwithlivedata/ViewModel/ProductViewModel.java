package com.example.umangburman.databindingwithlivedata.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.view.View;

import com.example.umangburman.databindingwithlivedata.Model.LoginUser;
import com.example.umangburman.databindingwithlivedata.Model.Product;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProductViewModel extends ViewModel {

    private DatabaseReference databaseReferenceItems;

    public void initFirebase(){
        databaseReferenceItems = FirebaseDatabase.getInstance().getReference("items");
    }

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

        String id =databaseReferenceItems.push().getKey();
        Product product  = new Product(id,strProductName.getValue(),strShortIntro.getValue());
        productMutableLiveData.setValue(product);
        databaseReferenceItems.child(id).setValue(product);




    }

}
