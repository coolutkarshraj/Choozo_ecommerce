package com.io.choozo.adapter.profileadapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.io.choozo.ApiCaller;
import com.io.choozo.Config;
import com.io.choozo.R;
import com.io.choozo.SqlDB.DbHelper;
import com.io.choozo.UrlLocator;
import com.io.choozo.adapter.ItemCategoryAdapter;
import com.io.choozo.adapter.ShopingBagAdapter;
import com.io.choozo.localStorage.PreferenceManager;
import com.io.choozo.model.dataModel.getWishlistDataModel.WishlistProductDataModel;
import com.io.choozo.model.dummydataModel.ShoppingBagModel;
import com.io.choozo.model.dummydataModel.WishListDataModel;
import com.io.choozo.model.responseModel.DeleteProductWishlistResponseModel;
import com.io.choozo.model.responseModel.LoginResponseModel;
import com.koushikdutta.async.future.FutureCallback;
import com.smarteist.autoimageslider.Transformations.TossTransformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class WishListRVAdapter extends RecyclerView.Adapter<WishListRVAdapter.ViewHolder> {

    Context context;
    List<WishlistProductDataModel> item;
    int color,stock,wishlistid,strProductId,localProductId;
    String image,imagePath,endPoint,endPointDeleteWishlist,token;
    PreferenceManager preferenceManager;
    Dialog dialog;
    String strProductName,strproductImage,strProductQty = "1",strproductPrice;
    DbHelper dbHelper;
    String ActualPrice,TotalPrice;

    public WishListRVAdapter(Context context, List<WishlistProductDataModel> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public WishListRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wishlist_carddesign,viewGroup,false);
    preferenceManager = new PreferenceManager(context);
    dbHelper = new DbHelper(context);
    getDataFromLocalStorage();
    getSqliteData();
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
    public void onBindViewHolder(@NonNull WishListRVAdapter.ViewHolder viewHolder, int i) {
        WishlistProductDataModel model = item.get(i);
        viewHolder.productName.setText(model.getProduct().getName());

        TotalPrice = model.getProduct().getPrice();
        ActualPrice = model.getProduct().getPricerefer();

        if(ActualPrice.equals("")){
            viewHolder.productPrice.setText(TotalPrice);
        }else {
            viewHolder.productPrice.setText(ActualPrice);
        }


        image = model.getProductImage().getImage();
        imagePath = model.getProductImage().getContainerName();
        imageResizeApi(image,imagePath);
        Glide.with(context).load(UrlLocator.getFinalUrl(endPoint)).into(viewHolder.productImage);
        stock = model.getProduct().getStockStatusId();
        viewHolder.deleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wishlistid = model.getWishlistProductId();
                deleteProductFromWishListDialog(i);
            }
        });
        viewHolder.btnAddToBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wishlistid = model.getWishlistProductId();
                strProductName = model.getProduct().getName();
                image = model.getProductImage().getImage();
                imagePath = model.getProductImage().getContainerName();
                imageResizeApi(image,imagePath);
                strproductImage = UrlLocator.getFinalUrl(endPoint);
                TotalPrice = model.getProduct().getPrice();
                ActualPrice = model.getProduct().getPricerefer();
                if(ActualPrice.equals("")){
                    strproductPrice = TotalPrice;
                }else {
                    strproductPrice = ActualPrice;
                }
                strProductId = model.getProductId();

                Log.e("LocalProductid",""+localProductId);
                Log.e("strProductId",""+strProductId);

                if(localProductId == strProductId){
                    Toast.makeText(context, "This Product already add into cart", Toast.LENGTH_SHORT).show();
                }else {
                    insertDataintoSqlite(strProductName, strproductImage, strProductQty, strproductPrice, strProductId, i);
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView productName,productPrice,productCutPrice,inStock;
        ImageView productImage ,Like,Dislike,deleteProduct;
        TextView line;
        RelativeLayout relativeLayout;
        Button btnAddToBag;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = (TextView)itemView.findViewById(R.id.tv_dressname);
            productPrice = (TextView)itemView.findViewById(R.id.tv_mrp);
            productCutPrice = (TextView)itemView.findViewById(R.id.tv_cutprice);
            productImage = (ImageView) itemView.findViewById(R.id.imageView);
            deleteProduct = (ImageView) itemView.findViewById(R.id.iv_delete);
            btnAddToBag = (Button) itemView.findViewById(R.id.add_to_bag);
         //   inStock = (TextView) itemView.findViewById(R.id.tv_stock);

        }
    }

    /* -----------------------------------------------Image Resize Api------------------------------------------------------------*/

    private void imageResizeApi(String image, String imagePath) {
        endPoint = Config.Url.imageResize +"width=260&height=360&name="+image+"&path="+imagePath+"";
    }

    /*----------------------------------------------- delete Product Dialog -----------------------------------------------------*/

    private void deleteProductFromWishListDialog( final int i) {

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        dialog.getWindow().setLayout((6 * width) / 7, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.delete_product_wishlist);
        dialog.setTitle("");
        final TextView Yes = (TextView) dialog.findViewById(R.id.yes);
        final TextView No = (TextView) dialog.findViewById(R.id.no);
        final ImageView Clear = (ImageView) dialog.findViewById(R.id.clear);
        Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProductfromWishList(i);
            }
        });
        No.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Clear.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    /*--------------------------------------api url for delete product from wishlist--------------------------------------------------*/

    private void apiUrl(){
        endPointDeleteWishlist = Config.Url.deleteproductfromWishList+"/"+wishlistid;
    }

    /*-------------------------------------------delete product from wishlist api call-----------------------------------------------*/

    private void deleteProductfromWishList(int i) {

        apiUrl();
        ApiCaller.wishlistDelete((Activity) context, endPointDeleteWishlist, token,
                new FutureCallback<DeleteProductWishlistResponseModel>() {
                    @Override
                    public void onCompleted(Exception e, DeleteProductWishlistResponseModel result) {
                        if(e!=null){
                            return;
                        }

                        if(result.getStatus() == 1){
                            dialog.dismiss();
                            Toast.makeText(context, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
                            item.remove(i);
                            notifyItemRemoved(i);
                            notifyItemRangeRemoved(i,item.size());
                        }else {
                            Toast.makeText(context, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }



                    }
                });

    }
    /*-------------------------------------------------insert data into sqlite and remove from list -------------------------------------*/

    private void insertDataintoSqlite(String strProductName, String strproductImage,
                                                     String strProductQty, String strproductPrice, int strProductId, int i) {
            boolean isInserted = dbHelper.insertData(strProductName, strproductImage, strProductQty,strproductPrice, String.valueOf(strProductId));
            if (isInserted == true) {
                Toast.makeText(context, "Data Inserted", Toast.LENGTH_SHORT).show();
                deleteProductFromWishListAfterAddToCart(i);
            } else {
                Toast.makeText(context, "DATA NOT SUPPORTED", Toast.LENGTH_SHORT).show();
            }



    }

    /*------------------------------------------ delete product from wishlist after add product into cart----------------------------------*/

    private void deleteProductFromWishListAfterAddToCart(int i) {

        apiUrl();
        ApiCaller.wishlistDelete((Activity) context, endPointDeleteWishlist, token,
                new FutureCallback<DeleteProductWishlistResponseModel>() {
                    @Override
                    public void onCompleted(Exception e, DeleteProductWishlistResponseModel result) {
                        if(e!=null){
                            return;
                        }
                        if(result.getStatus() == 1){

                            Toast.makeText(context, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
                            item.remove(i);
                            notifyItemRemoved(i);
                            notifyItemRangeRemoved(i,item.size());
                        }else {
                            Toast.makeText(context, ""+result.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    /* ----------------------------------------- get data from local storage and convert into json --------------------------------*/

    private void getSqliteData() {
        Cursor cursor = dbHelper.getData();
        if(cursor.getCount() == 0){
            Log.e("Error","no Data");
            return;
        }
        JSONArray resultSet = new JSONArray();
        JSONObject returnObj = new JSONObject();
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for (int i = 0; i < totalColumn; i++) {
                if (cursor.getColumnName(i) != null) {
                    try {
                        if (cursor.getString(i) != null) {
                            Log.d("TAG_NAME2", cursor.getString(i));
                            rowObject.put(cursor.getColumnName(i), cursor.getString(i));
                        } else {
                            rowObject.put(cursor.getColumnName(i), "");
                        }
                    } catch (Exception e) {
                        Log.d("TAG_NAME1", e.getMessage());
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }
        cursor.close();
        String datajson = resultSet.toString();
        datajson.replaceAll("\\\\", "");
        Log.d("datajson","data"+datajson);
        Config.CartData = datajson;
        localProductIdGet();
    }

    /* --------------------------------------------- Recycler view set json data ------------------------------------------------------*/

    private void localProductIdGet() {

        JSONArray jArray =null;
        try {
            jArray = new JSONArray(Config.CartData);
            for(int i=0;i<jArray.length();i++){
                JSONObject json_data = jArray.getJSONObject(i);
                localProductId = Integer.parseInt(json_data.getString("P_ID"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
