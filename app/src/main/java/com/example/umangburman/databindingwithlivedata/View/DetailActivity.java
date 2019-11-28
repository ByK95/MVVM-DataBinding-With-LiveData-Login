package com.example.umangburman.databindingwithlivedata.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.umangburman.databindingwithlivedata.R;
import com.example.umangburman.databindingwithlivedata.ViewModel.DetailViewModel;

public class DetailActivity extends AppCompatActivity {

    private DetailViewModel detailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
    }
}
