package com.io.choozo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.io.choozo.R;
import com.io.choozo.activity.homeActivity.CartActivity;
import com.io.choozo.model.dummydataModel.ItemCatModel;

import java.util.List;

public class ItemCategoryAdapter extends RecyclerView.Adapter<ItemCategoryAdapter.ViewHolder> {

    Context context;
    List<ItemCatModel> item;

    public ItemCategoryAdapter(Context context, List<ItemCatModel> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public ItemCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shopingsubcat_cat_design,viewGroup,false);
    return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCategoryAdapter.ViewHolder viewHolder, int i) {

        ItemCatModel model = item.get(i);
        viewHolder.productName.setText(model.getProductName());
        viewHolder.productPrice.setText(model.getProductMRP());
        viewHolder.productCutPrice.setText(model.getProductCutPrice());
        Glide.with(context).load(model.getImage()).into(viewHolder.productImage);
        viewHolder.data(item.get(i));

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView productName,productPrice,productCutPrice;
        ImageView productImage ,Like,Dislike;
        TextView line;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = (TextView)itemView.findViewById(R.id.tv_dress);
            productPrice = (TextView)itemView.findViewById(R.id.tv_mrp);
            productCutPrice = (TextView)itemView.findViewById(R.id.tv_cutprice);
            productImage = (ImageView) itemView.findViewById(R.id.imageview);
            Like = (ImageView) itemView.findViewById(R.id.like);
            Dislike = (ImageView) itemView.findViewById(R.id.heart);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rl_click);
            Like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dislike.setVisibility(View.VISIBLE);
                    Like.setVisibility(View.GONE);
                }
            });
            Dislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dislike.setVisibility(View.GONE);
                    Like.setVisibility(View.VISIBLE);
                }
            });
        }

        public void data(ItemCatModel itemCatModel) {
            final ItemCatModel model = itemCatModel;

            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i =new Intent(context, CartActivity.class);
                    i.putExtra("productName",model.getProductName());
                    i.putExtra("productImage",model.getImage());
                    i.putExtra("productMRP",model.getProductMRP());
                    i.putExtra("productpricecut",model.getProductCutPrice());
                    context.startActivity(i);
                }
            });
        }
    }
}