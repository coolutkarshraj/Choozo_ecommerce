package com.io.choozo.adapter.hotOfferAdapter;

import android.app.Activity;
import android.content.Context;
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
import com.io.choozo.localStorage.PreferenceManager;
import com.io.choozo.model.dataModel.todayDealsModel.TodayDataModel;
import com.io.choozo.model.responseModel.DeleteProductWishlistResponseModel;
import com.io.choozo.model.responseModel.LoginResponseModel;
import com.io.choozo.model.responseModel.WishlistResponseModel;
import com.koushikdutta.async.future.FutureCallback;

import java.util.List;

public class TodayDealsRvAdapter extends RecyclerView.Adapter<TodayDealsRvAdapter.ViewHolder> {

    Context context;
    List<TodayDataModel> item;
    int productId;
    String image, imagePath, endPoint, strCutPrice, strPreferPrice;
    PreferenceManager preferenceManager;
    String token, endPointDeleteWishlist;
    int wishlistid;

    public TodayDealsRvAdapter(Context context, List<TodayDataModel> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public TodayDealsRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.today_deals_card, viewGroup, false);
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
    public void onBindViewHolder(@NonNull TodayDealsRvAdapter.ViewHolder viewHolder, int i) {

        TodayDataModel model = item.get(i);
        viewHolder.productName.setText(model.getName());
        productId = model.getProductId();
        if (model.getAttributes().get(0).getPrice() == null) {
            strCutPrice = "0";
        } else {
            strCutPrice = model.getAttributes().get(0).getPrice();
        }

        viewHolder.productPrice.setText(strCutPrice);
        image = Config.imageUrl + model.getPosters().get(0).getAvatarPath();
        if (image == null) {
            Glide.with(context).load(Config.noImage).into(viewHolder.productImage);
        } else {
            Glide.with(context).load(image).into(viewHolder.productImage);
        }

        /*viewHolder.Like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProductfromWishList(viewHolder);

            }
        });
        viewHolder.Dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToWishlist(model.getProductId(),viewHolder);
            }
        });
        viewHolder.data(item.get(i));*/
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView productName, productPrice;
        ImageView productImage, Like, Dislike;
        TextView line;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.tv_dress);
            productPrice = (TextView) itemView.findViewById(R.id.tv_mrp);
            productImage = (ImageView) itemView.findViewById(R.id.imageview);
            Like = (ImageView) itemView.findViewById(R.id.like);
            Dislike = (ImageView) itemView.findViewById(R.id.heart);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rl_click);

        }

        /* ---------------------------------------------- Go to the Cart Activity ------------------------------------------------*/
 /*  public void data(TodayDealsProductListDataModel itemCatModel) {
            final TodayDealsProductListDataModel model = itemCatModel;

            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i =new Intent(context, CartActivity.class);
                    i.putExtra("productId",model.getProductId());
                    i.putExtra("toolbarName","Today Deals");
                    context.startActivity(i);
                }
            });
        }
    }*/

        /* ----------------------------------------------Image Resize Api-----------------------------------------------------------*/

        private void imageResizeApi(String image, String imagePath) {

            endPoint = Config.Url.imageResize + "width=260&height=360&name=" + image + "&path=" + imagePath + "";

        }

        /*---------------------------------------------------------  Add Wishlist Api ---------------------------------------------------*/

        private void addToWishlist(int productId, TodayDealsRvAdapter.ViewHolder viewHolder) {
            ApiCaller.wishlistadd(context, Config.Url.wishlistdata, productId, token, new FutureCallback<WishlistResponseModel>() {
                @Override
                public void onCompleted(Exception e, WishlistResponseModel result) {
                    if (e != null) {
                        return;
                    }
                    if (result.getStatus() == 1) {
                        if (result.getMessage().equals("Thank you product added to the wishlist successfully.")) {
                            Toast.makeText(context, "" + result.getMessage(), Toast.LENGTH_SHORT).show();
                            wishlistid = result.getData().getWishlistProductId();
                            viewHolder.Dislike.setVisibility(View.GONE);
                            viewHolder.Like.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(context, "" + result.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "" + result.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


        private void apiurl() {
            endPointDeleteWishlist = Config.Url.deleteproductfromWishList + "/" + wishlistid;
        }

        /*------------------------------------------------ delete product from wishlist --------------------------------------------------*/

        private void deleteProductfromWishList(TodayDealsRvAdapter.ViewHolder viewHolder) {

            apiurl();
            ApiCaller.wishlistDelete((Activity) context, endPointDeleteWishlist, token,
                    new FutureCallback<DeleteProductWishlistResponseModel>() {
                        @Override
                        public void onCompleted(Exception e, DeleteProductWishlistResponseModel result) {
                            if (e != null) {
                                return;
                            }
                            if (result.getStatus() == 1) {
                                Toast.makeText(context, "" + result.getMessage(), Toast.LENGTH_SHORT).show();
                                viewHolder.Dislike.setVisibility(View.VISIBLE);
                                viewHolder.Like.setVisibility(View.GONE);

                            } else {
                                Toast.makeText(context, "" + result.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }

    }
}

