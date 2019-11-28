package com.example.umangburman.databindingwithlivedata.ViewModel;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.umangburman.databindingwithlivedata.R;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {

    private ArrayList<String> itemNameList;
    private ArrayList<String> itemPriceList;
    private ArrayList<String> itemImageList;

    public ItemAdapter(ArrayList<String> itemNameList, ArrayList<String> itemPriceList, ArrayList<String> itemImageList) {
        this.itemNameList = itemNameList;
        this.itemPriceList = itemPriceList;
        this.itemImageList = itemImageList;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.items,viewGroup,false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int i) {

        itemHolder.nameText.setText("");
        itemHolder.priceText.setText("");

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ItemHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView nameText;
        TextView priceText;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.itemView);
            nameText = itemView.findViewById(R.id.itemName);
            priceText = itemView.findViewById(R.id.itemPrice);
        }
    }

}
