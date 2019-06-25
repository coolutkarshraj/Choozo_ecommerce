package com.io.choozo.adapter.profileadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.io.choozo.Config;
import com.io.choozo.R;
import com.io.choozo.UrlLocator;
import com.io.choozo.model.dataModel.getWishlistDataModel.WishlistProductDataModel;
import com.io.choozo.model.dummydataModel.WishListDataModel;

import java.util.List;

public class WishListRVAdapter extends RecyclerView.Adapter<WishListRVAdapter.ViewHolder> {

    Context context;
    List<WishlistProductDataModel> item;
    int color,stock;

    String image,imagePath,endPoint;

    public WishListRVAdapter(Context context, List<WishlistProductDataModel> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public WishListRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wishlist_carddesign,viewGroup,false);
    return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishListRVAdapter.ViewHolder viewHolder, int i) {

        WishlistProductDataModel model = item.get(i);
        viewHolder.productName.setText(model.getProduct().getName());
        viewHolder.productPrice.setText(model.getProduct().getPrice());
        image = model.getProductImage().getImage();
        imagePath = model.getProductImage().getContainerName();
        imageResizeApi(image,imagePath);
        Glide.with(context).load(UrlLocator.getFinalUrl(endPoint)).into(viewHolder.productImage);
        stock = model.getProduct().getStockStatusId();

        viewHolder.inStock.setTextColor(ContextCompat.getColor(context,color));

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView productName,productPrice,productCutPrice,inStock;
        ImageView productImage ,Like,Dislike;
        TextView line;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = (TextView)itemView.findViewById(R.id.tv_dressname);
            productPrice = (TextView)itemView.findViewById(R.id.tv_mrp);
            productCutPrice = (TextView)itemView.findViewById(R.id.tv_cutprice);
            productImage = (ImageView) itemView.findViewById(R.id.imageView);
            inStock = (TextView) itemView.findViewById(R.id.tv_stock);

        }
    }

    /* ----------------------------------------------Image Resize Api-----------------------------------------------------------*/

    private void imageResizeApi(String image, String imagePath) {
        endPoint = Config.Url.imageResize +"width=260&height=360&name="+image+"&path="+imagePath+"";
    }

}
