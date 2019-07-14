package com.io.choozo.adapter.hotOfferAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.io.choozo.activity.homeActivity.CartActivity;
import com.io.choozo.localStorage.PreferenceManager;
import com.io.choozo.model.dataModel.OurBrandsDataModel;
import com.io.choozo.model.dataModel.todayDealsModel.TodayDealsProductListDataModel;

import java.util.List;

public class Our_Brand_RvAdapter extends RecyclerView.Adapter<Our_Brand_RvAdapter.ViewHolder> {

    Context context;
    List<OurBrandsDataModel> item;
    int productId;
    String image,imagePath ,endPoint,strCutPrice,strPreferPrice;
    PreferenceManager preferenceManager;
    String token;


    public Our_Brand_RvAdapter(Context context, List<OurBrandsDataModel> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public Our_Brand_RvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.our_brand_card,viewGroup,false);
    return new ViewHolder(view);
    }

    /* ---------------------------------------------login data get from local storgae(for token get)---------------------------------------*/



    @Override
    public void onBindViewHolder(@NonNull Our_Brand_RvAdapter.ViewHolder viewHolder, int i) {

        OurBrandsDataModel model = item.get(i);

        image = model.getImage();
        imagePath = model.getImagePath();
        imageResizeApi(image,imagePath);
        Glide.with(context).load(UrlLocator.getFinalUrl(endPoint)).into(viewHolder.productImage);
    }



    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView productName,productPrice;
        ImageView productImage ,Like,Dislike;
        TextView line;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = (ImageView) itemView.findViewById(R.id.imageview);
        }

    }
    /* ----------------------------------------------Image Resize Api-----------------------------------------------------------*/
    private void imageResizeApi(String image, String imagePath) {
     endPoint = Config.Url.imageResize +"width=260&height=360&name="+image+"&path="+imagePath+"";
    }



}
