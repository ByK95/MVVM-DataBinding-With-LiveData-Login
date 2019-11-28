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

import com.example.umangburman.databindingwithlivedata.Interface.ItemClickListener;
import com.example.umangburman.databindingwithlivedata.R;
import com.example.umangburman.databindingwithlivedata.ViewModel.ItemAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
    private ArrayList<String> itemShortIntroFromFB;
    private ArrayList<String> itemPriceFromFB;
    private ArrayList<String> itemImageFromFB;
    private ArrayList<String> itemIdFromFB;

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
        getData();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter = new ItemAdapter(itemNameFromFB,itemPriceFromFB,itemImageFromFB);
        itemAdapter.setClickListener(ProductListActivity.this);
        recyclerView.setAdapter(itemAdapter);
    }

    public void getData(){
        databaseReference = FirebaseDatabase.getInstance().getReference("items");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot itemSnapshot : dataSnapshot.getChildren()){

                    Map data = (Map) itemSnapshot.getValue();
                    if(data != null){
                        String itemName = (String) data.get("strProductName");
                        String itemPrice = (String) data.get("price");
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
}
