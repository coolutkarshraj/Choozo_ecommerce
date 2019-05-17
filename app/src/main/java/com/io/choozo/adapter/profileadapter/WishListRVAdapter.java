package com.io.choozo.adapter.profileadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.io.choozo.R;
import com.io.choozo.model.dummydataModel.WishListDataModel;

import java.util.List;

public class WishListRVAdapter extends RecyclerView.Adapter<WishListRVAdapter.ViewHolder> {

    Context context;
    List<WishListDataModel> item;
    int color;

    public WishListRVAdapter(Context context, List<WishListDataModel> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public WishListRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wishlist_carddesign,viewGroup,false);
    return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishListRVAdapter.ViewHolder viewHolder, int i) {

        WishListDataModel model = item.get(i);
        viewHolder.productName.setText(model.getD_name());
        viewHolder.productPrice.setText(model.getD_amount());
        viewHolder.productCutPrice.setText(model.getD_amt_cut());
        Glide.with(context).load(model.getImage()).into(viewHolder.productImage);
        viewHolder.inStock.setText(model.getD_in_stock());
        color = model.getColor();
        viewHolder.inStock.setTextColor(ContextCompat.getColor(context,color));

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView productName,productPrice,productCutPrice,inStock;
        ImageView productImage ,Like,Dislike;
        TextView line;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = (TextView)itemView.findViewById(R.id.tv_dressname);
            productPrice = (TextView)itemView.findViewById(R.id.tv_mrp);
            productCutPrice = (TextView)itemView.findViewById(R.id.tv_cutprice);
            productImage = (ImageView) itemView.findViewById(R.id.imageView);
            inStock = (TextView) itemView.findViewById(R.id.tv_stock);

        }
    }
}
