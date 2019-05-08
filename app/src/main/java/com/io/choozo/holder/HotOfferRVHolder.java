package com.io.choozo.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.io.choozo.R;

public class HotOfferRVHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public HotOfferRVHolder(@NonNull View itemView) {
        super(itemView);
        imageView = (ImageView)itemView.findViewById(R.id.imageview);
    }
}
