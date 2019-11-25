package com.example.umangburman.databindingwithlivedata.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.umangburman.databindingwithlivedata.Model.Product;
import com.example.umangburman.databindingwithlivedata.R;
import com.example.umangburman.databindingwithlivedata.ViewModel.ProductViewModel;
import com.example.umangburman.databindingwithlivedata.databinding.ActivityProductListBinding;

public class ProductListActivity extends AppCompatActivity {

    private ActivityProductListBinding binding;
    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.initFirebase();
        binding = DataBindingUtil.setContentView(ProductListActivity.this, R.layout.activity_product_list);

        binding.setLifecycleOwner(this);
        binding.setProductViewModel(productViewModel);

        productViewModel.getProduct().observe(this, new Observer<Product>() {
            @Override
            public void onChanged(@Nullable Product product) {
                if (product.getstrProductName() == null){

                }
                if (product.getstrShortIntro() == null){

                }
            }
        });
    }
}
