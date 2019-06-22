package com.io.choozo.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.io.choozo.Config;
import com.io.choozo.Fragment.MyCart.MyCartFragment;
import com.io.choozo.R;
import com.io.choozo.SqlDB.DbHelper;
import com.io.choozo.activity.homeActivity.CategorySubCategory;
import com.io.choozo.model.dataModel.CategoryDataModel;
import com.io.choozo.model.dummydataModel.ShoppingBagModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class ShopingBagAdapter extends RecyclerView.Adapter<ShopingBagAdapter.ViewHolder> {

    Context context;
    List<ShoppingBagModel> item;
    int strQty;

    List<Integer> numbers = new ArrayList<Integer>();

    int count;
    DbHelper dbHelper;

    double sum =0;

    List<Double> doubles = new ArrayList<Double>();
    float strAmount;
    float strTotalAmt;


    String productid;

    public ShopingBagAdapter(Context context, List<ShoppingBagModel> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public ShopingBagAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mycart_card_design,viewGroup,false);
    dbHelper = new DbHelper(context);
    return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ShopingBagAdapter.ViewHolder viewHolder, int i) {

        ShoppingBagModel model = item.get(i);
        viewHolder.dressName.setText(model.getName());
        viewHolder.dressSize.setText("s");
        viewHolder.dressColor.setText("");
        viewHolder.dressPrice.setText(model.getPrice());
        viewHolder.tvQty.setText(model.getQuantity());
        Glide.with(context).load(model.getImage()).into(viewHolder.imageView);
        productid = model.getPID();
        viewHolder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increaseQuantity(model,viewHolder);
            }
        });
        viewHolder.ivMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               decreaseQuantity(model,viewHolder);

            }
        });

    }


    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dressName,dressSize,dressColor,dressPrice,dressPriceCut,tvQty;
        ImageView imageView;
        Button ivAdd,ivMinus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dressName = (TextView)itemView.findViewById(R.id.tv_itemname);
            dressSize = (TextView)itemView.findViewById(R.id.tv_size);
            dressColor = (TextView)itemView.findViewById(R.id.tv_color);
            dressPrice = (TextView)itemView.findViewById(R.id.tv_mrp);
            dressPriceCut = (TextView)itemView.findViewById(R.id.tv_cutprice);
            tvQty = (TextView)itemView.findViewById(R.id.tv_qty);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
            ivAdd = (Button)itemView.findViewById(R.id.iv_add);
            ivMinus = (Button)itemView.findViewById(R.id.iv_minus);
        }
    }

    /* ---------------------------------------------------- product Quantity increase ---------------------------------------------*/

    private void increaseQuantity(ShoppingBagModel model, ViewHolder viewHolder){

        count = Integer.parseInt(model.getQuantity());
        count++;
        if(count >= 101){
            Toast.makeText(context, "Quantity no exceed then avaliable", Toast.LENGTH_SHORT).show();
        }else {
            viewHolder.tvQty.setText(String.valueOf(count));
            increaseData(model.getName(), model.getImage(), String.valueOf(count), model.getPrice(), model.getPID());
        }
    }

    /* ------------------------------------------------- product Quantity Decrease -----------------------------------------------*/

    private void decreaseQuantity(ShoppingBagModel model, ViewHolder viewHolder){
        count = Integer.parseInt(model.getQuantity());
        count--;
        if(count <=0){
            Toast.makeText(context, "Quantity no less than one", Toast.LENGTH_SHORT).show();
        }else {
            decreaseData(model.getName(), model.getImage(), String.valueOf(count), model.getPrice(), model.getPID());
        }
    }

    /*------------------------------------------------  sqlite update quantity(increase) ------------------------------------------*/

    private void increaseData(String name, String image, String s, String price, String pid) {
        boolean isupdated=dbHelper.updateData(name,image,s,price,pid);
        if(isupdated==true) {
            getSqliteData();
        }else {
            Toast.makeText(context,"something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    /* ------------------------------------------------ sqlite decrease quantity -------------------------------------------------*/

    private void decreaseData(String name, String image, String s, String price, String pid) {
        boolean isupdated=dbHelper.updateData(name,image,s,price,pid);
        if(isupdated==true) {
            getSqliteData();
        }else {
            Toast.makeText(context,"something went wrong", Toast.LENGTH_SHORT).show();
        }
    }


    /* ---------------------------------- get data from local storage(SQQLITE 3) and convert into json ------------------------------*/

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
        allShopingDataSetIntoRecyclerView();
    }

    /* --------------------------------------------- Recycler view set json data ------------------------------------------------------*/

    private void allShopingDataSetIntoRecyclerView() {
        JSONArray jArray =null;
        try {
            jArray = new JSONArray(Config.CartData);
            item.clear();
            for(int i=0;i<jArray.length();i++){
                JSONObject json_data = jArray.getJSONObject(i);
                ShoppingBagModel shoppingBagModel = new ShoppingBagModel();
                shoppingBagModel.setId(json_data.getString("Id"));
                shoppingBagModel.setName(json_data.getString("Name"));
                String  img = json_data.getString("Image");
                img.replaceAll("\\\\","");
                shoppingBagModel.setImage(img);
                shoppingBagModel.setQuantity(json_data.getString("Quantity"));
                shoppingBagModel.setPrice(json_data.getString("Price"));
                shoppingBagModel.setPID(json_data.getString("P_ID"));
                strAmount = Float.parseFloat(json_data.getString("Price"));
                strQty = Integer.parseInt(json_data.getString("Quantity"));
                strTotalAmt = strAmount * strQty; // total amount every product acoording to quantity
                sum= strTotalAmt+sum;
                item.add(shoppingBagModel);

                MyCartFragment.rv_ShoppingBag.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                MyCartFragment.adapter = new ShopingBagAdapter(context,item);
                MyCartFragment.rv_ShoppingBag.setAdapter(MyCartFragment.adapter);
                MyCartFragment.adapter.notifyDataSetChanged();
            }
            MyCartFragment.tvTotalAmount.setText(String.valueOf(sum));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }




}
