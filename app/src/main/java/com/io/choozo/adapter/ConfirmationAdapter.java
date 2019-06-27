package com.io.choozo.adapter;

import android.content.Context;
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
import com.io.choozo.model.dummydataModel.ConfirmationModel;
import com.io.choozo.model.dummydataModel.ShoppingBagModel;

import java.util.List;

public class ConfirmationAdapter extends RecyclerView.Adapter<ConfirmationAdapter.ViewHolder> {

    Context context;
    List<ConfirmationModel> item;
    String image,imageurl;

    public ConfirmationAdapter(Context context, List<ConfirmationModel> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public ConfirmationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.myconfirmorder_card_design,viewGroup,false);
    return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConfirmationAdapter.ViewHolder viewHolder, int i) {

        ConfirmationModel model = item.get(i);
        viewHolder.dressName.setText(model.getName());
        viewHolder.dressPrice.setText(model.getPrice());
        Glide.with(context).load(model.getImage()).into(viewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dressName,dressSize,dressColor,dressPrice,dressPriceCut;
        ImageView imageView;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dressName = (TextView)itemView.findViewById(R.id.tv_itemname);
            dressSize = (TextView)itemView.findViewById(R.id.tv_size);
            dressColor = (TextView)itemView.findViewById(R.id.tv_color);
            dressPrice = (TextView)itemView.findViewById(R.id.tv_mrp);
            dressPriceCut = (TextView)itemView.findViewById(R.id.tv_cutprice);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);


        }
    }
}
