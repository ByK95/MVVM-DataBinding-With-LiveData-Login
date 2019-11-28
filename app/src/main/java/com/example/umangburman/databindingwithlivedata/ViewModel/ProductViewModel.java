package com.example.umangburman.databindingwithlivedata.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.view.View;

import com.example.umangburman.databindingwithlivedata.Model.Product;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.sql.Timestamp;

public class ProductViewModel extends ViewModel {

    private boolean _created = true;
    private DatabaseReference databaseReferenceItems;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean is_created() {
        return _created;
    }

    private void set_created() {
        this._created = false;
    }

    public void initFirebase(){
        databaseReferenceItems = FirebaseDatabase.getInstance().getReference("items");
    }

    public MutableLiveData<String> strProductName = new MutableLiveData<>();
    public MutableLiveData<String> strShortIntro = new MutableLiveData<>();
    public MutableLiveData<String> strCategory = new MutableLiveData<>();
    public MutableLiveData<String> price = new MutableLiveData<>();


    private MutableLiveData<Product> productMutableLiveData;

    public MutableLiveData<Product> getProduct() {

        if (productMutableLiveData == null) {
            productMutableLiveData = new MutableLiveData<>();
        }
        return productMutableLiveData;

    }

    public void onClick(View view) {

        Timestamp timestamp= new Timestamp(System.currentTimeMillis());
        String time = timestamp.toString();
        String id = databaseReferenceItems.push().getKey();
        Product product  = new Product(id,strProductName.getValue(),strShortIntro.getValue(),strCategory.getValue(),this.getUrl(),price.getValue(),time);
        productMutableLiveData.setValue(product);
        boolean is_valid = product.validate();
        boolean is_valid_cat = product.validateCategory();
        boolean imageSelected = product.imageSelected();
       if(!is_valid) {
           if (is_valid_cat && imageSelected) {
               databaseReferenceItems.child(id).setValue(product);

           }else{
               set_created();
           }
       }
    }
}
