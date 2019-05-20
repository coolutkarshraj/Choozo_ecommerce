package com.io.choozo.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.io.choozo.R;

public class ReviewRVHolder extends RecyclerView.ViewHolder {

    public TextView name,date;
    public ReviewRVHolder(@NonNull View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.tv_name);
        date = (TextView) itemView.findViewById(R.id.tv_date);
    }
}
