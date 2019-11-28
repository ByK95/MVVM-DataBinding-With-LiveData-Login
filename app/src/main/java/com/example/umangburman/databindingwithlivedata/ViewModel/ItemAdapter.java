package com.example.umangburman.databindingwithlivedata.ViewModel;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.umangburman.databindingwithlivedata.Interface.ItemClickListener;
import com.example.umangburman.databindingwithlivedata.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {

    private ArrayList<String> itemNameList;
    private ArrayList<String> itemPriceList;
    private ArrayList<String> itemImageList;
    private ItemClickListener clickListener;

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

        itemHolder.nameText.setText(itemNameList.get(i));
        itemHolder.priceText.setText(itemPriceList.get(i) + " ₺");
        Picasso.get().load(itemImageList.get(i)).into(itemHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return itemNameList.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView nameText;
        TextView priceText;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            imageView = itemView.findViewById(R.id.itemView);
            nameText = itemView.findViewById(R.id.itemName);
            priceText = itemView.findViewById(R.id.itemPrice);
        }
        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

}
