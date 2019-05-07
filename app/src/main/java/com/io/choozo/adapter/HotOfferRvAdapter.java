package com.io.choozo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.io.choozo.R;
import com.io.choozo.model.dummydataModel.OfferDataModel;

import java.util.ArrayList;
import java.util.List;

public class HotOfferRvAdapter extends RecyclerView.Adapter<HotOfferRvAdapter.ViewHolder> {

    Context context;
    List<OfferDataModel> list;

    public HotOfferRvAdapter(Context context, List<OfferDataModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater .from(viewGroup.getContext()).inflate(R.layout.hot_offer_card_design,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
      OfferDataModel offerDataModel = list.get(i);
        Glide.with(context).load(offerDataModel.getImage()).into(viewHolder.Image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView Image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Image = (ImageView)itemView.findViewById(R.id.imageview);
        }
    }
}
