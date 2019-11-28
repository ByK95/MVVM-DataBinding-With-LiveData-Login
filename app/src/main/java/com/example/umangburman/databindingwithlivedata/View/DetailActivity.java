package com.example.umangburman.databindingwithlivedata.View;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.umangburman.databindingwithlivedata.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private ImageView imageView;
    private TextView itemName;
    private TextView itemShortIntro;
    private TextView itemPrice;

    public DetailActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        databaseReference = FirebaseDatabase.getInstance().getReference("items").child(intent.getStringExtra(ProductListActivity.ITEM_ID));

        imageView = findViewById(R.id.imageDetail);
        itemName = findViewById(R.id.itemNameText);
        itemShortIntro = findViewById(R.id.itemIntroText);
        itemPrice = findViewById(R.id.itemPriceText);
        writeDetails();
    }
    public void writeDetails(){

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Map data = (Map) dataSnapshot.getValue();
                    if(data != null){
                        itemName.setText((String) data.get("strProductName"));
                        itemShortIntro.setText((String) data.get("strShortIntro"));
                        itemPrice.setText((String) data.get("price")+ " ₺");
                        Picasso.get().load((String) data.get("strItemImageUrl")).into(imageView);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
