package com.io.choozo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.io.choozo.R;
import com.io.choozo.model.dummydataModel.ChooseColorModel;
import com.io.choozo.model.dummydataModel.SelectSizeDataMode;

import java.util.List;

import static com.io.choozo.R.drawable.design_size_product;
import static com.io.choozo.adapter.SubCategoryAdapter.recyclerViewClickPostion;

public class SelectSizeAdapter extends RecyclerView.Adapter<SelectSizeAdapter.ViewHolder> {

    Context context;
    List<SelectSizeDataMode> item;
    public  static int recyclerViewClickPostion;

    public SelectSizeAdapter(Context context, List<SelectSizeDataMode> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public SelectSizeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.choose_size_of_product_cardview,viewGroup,false);
    return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectSizeAdapter.ViewHolder viewHolder, final int i) {

        SelectSizeDataMode model = item.get(i);
        viewHolder.productSize.setText(model.getName());
        viewHolder. linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewClickPostion = i;
                notifyDataSetChanged();
            }
        });
        if (recyclerViewClickPostion == i) {
            viewHolder.productSize.setTextColor(Color.parseColor("#ffffff"));
            viewHolder.linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));

        } else {
            viewHolder. productSize.setTextColor(Color.parseColor("#000000"));
            //viewHolder.linearLayout.setBackgroundResource(R.drawable.design_size_product);
            viewHolder.linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
            viewHolder.linearLayout.setBackgroundResource(R.drawable.design_size_product);
        }

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView productSize;
        RelativeLayout linearLayout;
        @SuppressLint("WrongViewCast")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = (RelativeLayout) itemView.findViewById(R.id.rl_product_size);
            productSize = (TextView) itemView.findViewById(R.id.tv_size);

    }
}}
