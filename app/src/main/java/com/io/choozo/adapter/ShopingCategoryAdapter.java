package com.io.choozo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.io.choozo.R;
import com.io.choozo.model.dummydataModel.ShopCategoryModel;

import java.util.List;

public class ShopingCategoryAdapter extends RecyclerView.Adapter<ShopingCategoryAdapter.ViewHolder> {

    Context context;
    List<ShopCategoryModel> item;

    public ShopingCategoryAdapter(Context context, List<ShopCategoryModel> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public ShopingCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shoping_category_cardview,viewGroup,false);
    return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopingCategoryAdapter.ViewHolder viewHolder, int i) {

        ShopCategoryModel model = item.get(i);
        viewHolder.name.setText(model.getCategoryName());
        Glide.with(context).load(model.getCategoryImageUrl()).into(viewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.tv_name);
            imageView = (ImageView)itemView.findViewById(R.id.imageview);
        }
    }
}
