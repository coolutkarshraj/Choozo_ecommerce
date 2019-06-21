package com.io.choozo.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.io.choozo.R;
import com.io.choozo.activity.homeActivity.CategorySubCategory;
import com.io.choozo.model.dataModel.CategoryDataModel;
import com.io.choozo.model.dummydataModel.ShoppingBagModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ShopingBagAdapter extends RecyclerView.Adapter<ShopingBagAdapter.ViewHolder> {

    Context context;
    List<ShoppingBagModel> item;
    int strQty,strAmount;
    int strTotalAmt;
    ArrayList amt = new ArrayList();

    public ShopingBagAdapter(Context context, List<ShoppingBagModel> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public ShopingBagAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mycart_card_design,viewGroup,false);
    return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ShopingBagAdapter.ViewHolder viewHolder, int i) {

        ShoppingBagModel model = item.get(i);
        viewHolder.dressName.setText(model.getName());
        viewHolder.dressSize.setText("s");
        viewHolder.dressColor.setText("");
        strAmount = Integer.parseInt(model.getPrice());
        strQty = Integer.parseInt(model.getQuantity());
        strTotalAmt = strAmount * strQty;
        amt.add(strAmount);
        Log.e("TotalAmt",""+amt);


        viewHolder.dressPrice.setText(model.getPrice());
        viewHolder.tvQty.setText(model.getQuantity());
        Glide.with(context).load(model.getImage()).into(viewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dressName,dressSize,dressColor,dressPrice,dressPriceCut,tvQty;
        ImageView imageView;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dressName = (TextView)itemView.findViewById(R.id.tv_itemname);
            dressSize = (TextView)itemView.findViewById(R.id.tv_size);
            dressColor = (TextView)itemView.findViewById(R.id.tv_color);
            dressPrice = (TextView)itemView.findViewById(R.id.tv_mrp);
            dressPriceCut = (TextView)itemView.findViewById(R.id.tv_cutprice);
            tvQty = (TextView)itemView.findViewById(R.id.tv_qty);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
        }
    }
}
