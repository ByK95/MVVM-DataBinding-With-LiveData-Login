package com.example.umangburman.databindingwithlivedata.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.umangburman.databindingwithlivedata.Interface.ItemClickListener;
import com.example.umangburman.databindingwithlivedata.R;
import com.example.umangburman.databindingwithlivedata.ViewModel.ItemAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class ProductListActivity extends AppCompatActivity implements ItemClickListener {

    public static final String ITEM_ID = "ItemName" ;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private ItemAdapter itemAdapter;
    private DatabaseReference databaseReference;
    private ArrayList<String> itemNameFromFB;
    private ArrayList<Long> itemPriceFromFB;
    private ArrayList<String> itemImageFromFB;
    private ArrayList<String> itemIdFromFB;
    private Query query;
    private Spinner spinnerCategory;
    private Spinner spinnerSort;
    private Integer spinnerPosition1 = 0;
    private Integer spinnerPosition2 = 0;
    private String categorySelect = "items";
    private RecyclerView recyclerView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.addPoduct){

            Intent intentAdd = new Intent(ProductListActivity.this,ProductAddActivity.class);
            startActivity(intentAdd);

        }else if (item.getItemId() == R.id.signOut){

            firebaseAuth.signOut();
            Intent intentMain = new Intent(ProductListActivity.this,MainActivity.class);
            startActivity(intentMain);
            finish();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        itemNameFromFB = new ArrayList<>();
        itemPriceFromFB = new ArrayList<>();
        itemImageFromFB = new ArrayList<>();
        itemIdFromFB = new ArrayList<>();

        spinnerCategory = findViewById(R.id.spinner);
        spinnerSort = findViewById(R.id.spinner2);
          //Spinner 1
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.addressArray,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setSpinnerPosition1(i);
                String categori[] = getResources().getStringArray(R.array.addressArray);
                if(i>0){
                    setCategorySelect(categori[i]);
                }else
                {
                    setCategorySelect("items");
                }
                spinnerEvent();
                getData();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                setSpinnerPosition1(0);
            }
        });
         //Spinner 2
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,R.array.orderArray,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSort.setAdapter(adapter2);

        spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setSpinnerPosition2(i);


            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                setSpinnerPosition2(0);
            }
        });


        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        spinnerEvent();
        getData();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter = new ItemAdapter(itemNameFromFB,itemPriceFromFB,itemImageFromFB);
        itemAdapter.setClickListener(ProductListActivity.this);
        recyclerView.setAdapter(itemAdapter);
    }

    public void getData(){

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemNameFromFB.clear();
                itemPriceFromFB.clear();
                itemImageFromFB.clear();
                itemIdFromFB.clear();
                for(DataSnapshot itemSnapshot : dataSnapshot.getChildren()){

                    Map data = (Map) itemSnapshot.getValue();
                    if(data != null){
                        String itemName = (String) data.get("strProductName");
                        Long itemPrice = (Long) data.get("price") ;
                        String itemImage = (String) data.get("strItemImageUrl");
                        String itemId = (String) data.get("strProductId");
                        itemNameFromFB.add(itemName);
                        itemPriceFromFB.add(itemPrice);
                        itemImageFromFB.add(itemImage);
                        itemAdapter.notifyDataSetChanged();
                        itemIdFromFB.add(itemId);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    @Override
    public void onClick(View view, int position) {

        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        intent.putExtra(ITEM_ID, itemIdFromFB.get(position));
        startActivity(intent);
    }

    public Integer getSpinnerPosition1() {
        return spinnerPosition1;
    }

    public void setSpinnerPosition1(Integer spinnerPosition1) {
        this.spinnerPosition1 = spinnerPosition1;
    }

    public Integer getSpinnerPosition2() {
        return spinnerPosition2;
    }

    public void setSpinnerPosition2(Integer spinnerPosition2) {
        this.spinnerPosition2 = spinnerPosition2;
    }

    public String getCategorySelect() {
        return categorySelect;
    }

    public void setCategorySelect(String categorySelect) {
        this.categorySelect = categorySelect;
    }

    public void spinnerEvent(){
        switch (getSpinnerPosition1()){
            case 0 :
                switch (getSpinnerPosition2()){
                    case 0 : query = FirebaseDatabase.getInstance().getReference(getCategorySelect());

                        break;
                    case 1 :

                        break;
                    case 2 :

                        break;

                }
                break;
            case 1 :
                switch (getSpinnerPosition2()){
                    case 0 :

                        break;
                    case 1 :

                        break;
                    case 2 :

                        break;

                }

                break;
            case 2 :
                switch (getSpinnerPosition2()){
                    case 0 : query = FirebaseDatabase.getInstance().getReference("items").child(getCategorySelect());

                        break;
                    case 1 :

                        break;
                    case 2 :

                        break;

                }

                break;
            case 3 :
                switch (getSpinnerPosition2()){
                    case 0 : query = FirebaseDatabase.getInstance().getReference(getCategorySelect()).child(getCategorySelect());

                        break;
                    case 1 :

                        break;
                    case 2 :

                        break;

                }

                break;
            case 4 :
                switch (getSpinnerPosition2()){
                    case 0 :

                        break;
                    case 1 :

                        break;
                    case 2 :

                        break;

                }

                break;
        }
    }
}
