package com.io.choozo.adapter.BasicAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.google.gson.Gson;
import com.io.choozo.ApiCaller;
import com.io.choozo.Config;
import com.io.choozo.R;
import com.io.choozo.UrlLocator;
import com.io.choozo.activity.homeActivity.CartActivity;
import com.io.choozo.localStorage.PreferenceManager;
import com.io.choozo.model.dataModel.productListDataModel.ProductList;
import com.io.choozo.model.responseModel.Data;
import com.io.choozo.model.responseModel.DeleteProductWishlistResponseModel;
import com.io.choozo.model.responseModel.LoginResponseModel;
import com.io.choozo.model.responseModel.WishlistResponseModel;
import com.koushikdutta.async.future.FutureCallback;

import java.util.List;

public class ItemCategoryAdapter extends RecyclerView.Adapter<ItemCategoryAdapter.ViewHolder> {

    Context context;
    List<Data> item;
    int productId;
    String image,imagePath ,endPoint,strCutPrice,strPreferPrice;
    PreferenceManager preferenceManager;
    String token,endPointDeleteWishlist;
    int wishlistid;
    private int k =0;


    public ItemCategoryAdapter(Context context, List<Data> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public ItemCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shopingsubcat_cat_design,viewGroup,false);
    preferenceManager = new PreferenceManager(context);
    getDataFromLocalStorage();
    return new ViewHolder(view);
    }

    /* ---------------------------------------------login data get from local storgae(for token get)---------------------------------------*/

    private void getDataFromLocalStorage() {
        Gson gson = new Gson();
        String getJson = preferenceManager.getString(PreferenceManager.loginData);
        LoginResponseModel obj = gson.fromJson(getJson, LoginResponseModel.class);
        token = obj.getData().getToken();
    }


    @Override
    public void onBindViewHolder(@NonNull ItemCategoryAdapter.ViewHolder viewHolder, int i) {

        viewHolder.productName.setText(item.get(i).getName());
        productId = item.get(i).getProductId();
        strCutPrice = item.get(i).getPrice();
        if(strCutPrice.equals("") ){
            viewHolder.productPrice.setText(item.get(i).getPrice());
            viewHolder.rlCutPrice.setVisibility(View.GONE);
        }else {
            if(strCutPrice.equals("") ){
                if (item.get(i).getAttributes() != null || item.get(i).getAttributes().size() != 0) {
                    viewHolder.productPrice.setText(item.get(i).getAttributes().get(0).getPrice());
                }
                viewHolder.rlCutPrice.setVisibility(View.GONE);
            }else {
                viewHolder.rlCutPrice.setVisibility(View.VISIBLE);
                if(item.get(i).getOfferPercentage() != null && item.get(i).getOfferPercentage().equals("0")) {
                    if (item.get(i).getAttributes() != null && item.get(i).getAttributes().size() != 0) {
                        String price = item.get(i).getAttributes().get(0).getPrice();
                        float productPrice = Float.valueOf(price);
                        String percent = "0";
                        percent = item.get(i).getOfferPercentage();
                        float offerPrice = (productPrice * Integer.valueOf(percent)) / 100;
                        if(item.get(i).getOfferPercentage().equals("0")){
                            viewHolder.productPrice.setText(String.valueOf(productPrice));
                        }else {
                            float productPriceOriginal =  productPrice = offerPrice;
                            viewHolder.productPrice.setText(String.valueOf(productPriceOriginal));
                        }
                        viewHolder.productCutPrice.setText( "\u20B9" +String.valueOf(productPrice));
                        viewHolder.productCutPrice.setPaintFlags( viewHolder.productCutPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                    }
                }
            }
          }
        for (int j = 0 ; j <item.get(i).getPosters().size();j++){
            imagePath =  item.get(i).getPosters().get(j).getAvatarPath();
            k = j;
            break;
        }
        String url = "https://admin.dincharyamart.com/api/media/render?path="+ imagePath;

        Glide.with(context).load(url).into(viewHolder.productImage);
        viewHolder.Like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProductfromWishList(viewHolder);

            }
        });
        viewHolder.Dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToWishlist(item.get(i).getProductId(),viewHolder);
            }
        });
       // viewHolder.data(item.get(i));
    }

    private void putLoopOnImages(List<Data> item, int i) {
        for (int j = 0 ; j <item.get(i).getPosters().size();j++){
            imagePath =  item.get(i).getPosters().get(j).getAvatarPath();
            k = j;
            break;
        }
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

        }

        /* ---------------------------------------------- Go to the Cart Activity ------------------------------------------------*/
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
    /* ----------------------------------------------Image Resize Api-----------------------------------------------------------*/

    private void imageResizeApi(String image, String imagePath) {
     endPoint = Config.Url.imageResize +"width=260&height=360&name="+image+"&path="+imagePath+"";
    }


    /*---------------------------------------------------------  Add Wishlist Api ---------------------------------------------------*/

    private void addToWishlist(int productId, ViewHolder viewHolder) {
       /* ApiCaller.wishlistadd(context, Config.Url.wishlistdata, productId, token, new FutureCallback<WishlistResponseModel>() {
            @Override
            public void onCompleted(Exception e, WishlistResponseModel result) {
                if(e!=null){
                    return;
                }
                if(result.getStatus() == 1){
                    if(result.getMessage().equals("Thank you product added to the wishlist successfully.")) {
                        Toast.makeText(context, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
                        wishlistid = result.getData().getWishlistProductId();
                        viewHolder.Dislike.setVisibility(View.GONE);
                        viewHolder.Like.setVisibility(View.VISIBLE);
                    }else {
                        Toast.makeText(context, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(context, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }


    private void apiurl(){
        endPointDeleteWishlist = Config.Url.deleteproductfromWishList+"/"+wishlistid;
    }

    /*------------------------------------------------ delete product from wishlist --------------------------------------------------*/

    private void deleteProductfromWishList(ViewHolder viewHolder) {

        apiurl();
        ApiCaller.wishlistDelete((Activity) context, endPointDeleteWishlist, token,
                                            new FutureCallback<DeleteProductWishlistResponseModel>() {
            @Override
            public void onCompleted(Exception e, DeleteProductWishlistResponseModel result) {
              if(e!=null){
                  return;
              }
                if(result.getStatus() == 1){
                    Toast.makeText(context, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
                    viewHolder.Dislike.setVisibility(View.VISIBLE);
                    viewHolder.Like.setVisibility(View.GONE);

                }else {
                    Toast.makeText(context, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
