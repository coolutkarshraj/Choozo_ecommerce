package com.io.choozo.adapter.ProductItemAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.io.choozo.R;
import com.io.choozo.holder.HotOfferRVHolder;
import com.io.choozo.holder.ReviewRVHolder;
import com.io.choozo.model.dummydataModel.OfferDataModel;
import com.io.choozo.model.dummydataModel.ReviewModel;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewRVHolder> {

    Context context;
    List<ReviewModel> list;

    public ReviewAdapter(Context context, List<ReviewModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ReviewRVHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater .from(viewGroup.getContext()).inflate(R.layout.productreview,viewGroup,false);
        return new ReviewRVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewRVHolder viewHolder, int i) {
        ReviewModel offerDataModel = list.get(i);
        viewHolder.name.setText(offerDataModel.getName());
        viewHolder.date.setText(offerDataModel.getDate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    }

