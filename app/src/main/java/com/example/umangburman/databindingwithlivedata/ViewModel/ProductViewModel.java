package com.example.umangburman.databindingwithlivedata.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.view.View;
import android.widget.Spinner;

import com.example.umangburman.databindingwithlivedata.Model.LoginUser;
import com.example.umangburman.databindingwithlivedata.Model.Product;
import com.example.umangburman.databindingwithlivedata.View.MainActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProductViewModel extends ViewModel {

    private boolean _created = false;
    private DatabaseReference databaseReferenceItems;

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
        String id =databaseReferenceItems.push().getKey();
        Product product  = new Product(id,strProductName.getValue(),strShortIntro.getValue(),strCategory.getValue(),price.getValue(),time);
        productMutableLiveData.setValue(product);
        boolean is_valid = product.validate();
        boolean is_valid_cat = product.validateCategory();
     //   boolean cat_valid = product.validateCategory();
       if(!is_valid) {
           if (is_valid_cat) {
               databaseReferenceItems.child(id).setValue(product);
               set_created(true);
           }
       }
    }

    public boolean is_created() {
        return _created;
    }

    public void set_created(boolean _created) {
        this._created = _created;
    }
}
