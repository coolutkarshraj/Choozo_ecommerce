package com.io.choozo.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.io.choozo.Config;
import com.io.choozo.R;
import com.io.choozo.UrlLocator;
import com.io.choozo.activity.homeActivity.CartActivity;
import com.io.choozo.model.dataModel.productListDataModel.ProductList;
import com.io.choozo.model.dummydataModel.ItemCatModel;

import java.util.List;

public class ItemCategoryAdapter extends RecyclerView.Adapter<ItemCategoryAdapter.ViewHolder> {

    Context context;
    List<ProductList> item;
    int productId;
    String image,imagePath ,endPoint,strCutPrice,strPreferPrice;

    public ItemCategoryAdapter(Context context, List<ProductList> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public ItemCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shopingsubcat_cat_design,viewGroup,false);
    return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCategoryAdapter.ViewHolder viewHolder, int i) {

        ProductList model = item.get(i);
        viewHolder.productName.setText(model.getName());
        productId = model.getProductId();
        strCutPrice = model.getPrice();
        strPreferPrice = model.getPricerefer();
        if(strPreferPrice.equals("") ){
            viewHolder.productPrice.setText(model.getPrice());
            viewHolder.rlCutPrice.setVisibility(View.GONE);
        }else {
            viewHolder.rlCutPrice.setVisibility(View.VISIBLE);
            viewHolder.productPrice.setText(model.getPricerefer());
            viewHolder.productCutPrice.setText( "\u20B9" +model.getPrice());
            viewHolder.productCutPrice.setPaintFlags( viewHolder.productCutPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        viewHolder.productCutPrice.setText(model.getPrice());
        image = model.getImages().getImage();
        imagePath = model.getImages().getContainerName();
        imageResizeApi(image,imagePath);
        Glide.with(context).load(UrlLocator.getFinalUrl(endPoint)).into(viewHolder.productImage);
        viewHolder.data(item.get(i));

    }


    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView productName,productPrice,productCutPrice;
        ImageView productImage ,Like,Dislike;
        TextView line;
        RelativeLayout relativeLayout,rlCutPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = (TextView)itemView.findViewById(R.id.tv_dress);
            productPrice = (TextView)itemView.findViewById(R.id.tv_mrp);
            productCutPrice = (TextView)itemView.findViewById(R.id.tv_cutprice);
            productImage = (ImageView) itemView.findViewById(R.id.imageview);
            Like = (ImageView) itemView.findViewById(R.id.like);
            Dislike = (ImageView) itemView.findViewById(R.id.heart);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rl_click);
            rlCutPrice = (RelativeLayout)itemView.findViewById(R.id.rl_cut);
            Like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dislike.setVisibility(View.VISIBLE);
                    Like.setVisibility(View.GONE);
                }
            });
            Dislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dislike.setVisibility(View.GONE);
                    Like.setVisibility(View.VISIBLE);
                }
            });
        }

        public void data(ProductList itemCatModel) {
            final ProductList model = itemCatModel;

            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i =new Intent(context, CartActivity.class);
                    i.putExtra("productId",model.getProductId());
                    i.putExtra("toolbarName",Config.toolbarName);
                    context.startActivity(i);
                }
            });
        }
    }

    private void imageResizeApi(String image, String imagePath) {
     endPoint = Config.Url.imageResize +"width=260&height=360&name="+image+"&path="+imagePath+"";
    }

}
