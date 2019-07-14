package com.io.choozo.adapter.BasicAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.io.choozo.R;
import com.io.choozo.holder.HotOfferRVHolder;
import com.io.choozo.model.dummydataModel.OfferDataModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HotOfferRvAdapter extends RecyclerView.Adapter<HotOfferRVHolder> {

    Context context;
    List<OfferDataModel> list;

    public HotOfferRvAdapter(Context context, List<OfferDataModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HotOfferRVHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater .from(viewGroup.getContext()).inflate(R.layout.hot_offer_card_design,viewGroup,false);
        return new HotOfferRVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotOfferRVHolder viewHolder, int i) {
      OfferDataModel offerDataModel = list.get(i);
      Glide.with(context).load(offerDataModel.getImage()).into(viewHolder.imageView);
        ViewGroup.LayoutParams lp = viewHolder.imageView.getLayoutParams();
        if (lp instanceof FlexboxLayoutManager.LayoutParams) {
            FlexboxLayoutManager.LayoutParams flexboxLp = (FlexboxLayoutManager.LayoutParams)lp;
            flexboxLp.setFlexGrow(1.0f);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    }

