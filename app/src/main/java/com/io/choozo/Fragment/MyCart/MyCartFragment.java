package com.io.choozo.Fragment.MyCart;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.io.choozo.Config;
import com.io.choozo.R;
import com.io.choozo.SqlDB.DbHelper;
import com.io.choozo.activity.checkout.CheckOutActivity;
import com.io.choozo.adapter.BasicAdapter.ShopingBagAdapter;
import com.io.choozo.model.dummydataModel.ShoppingBagModel;
import com.io.choozo.util.NewProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyCartFragment extends Fragment implements View.OnClickListener {
    public static RecyclerView rv_ShoppingBag;
    public static ShopingBagAdapter adapter;
    List<ShoppingBagModel> list = new ArrayList<>();
    Activity activity;
    RelativeLayout btn_PlaceOrder;
    DbHelper dbHelper;
    public static TextView tvTotalAmount;
    float strAmount;
    int strQty;
    NewProgressBar dialog;
    float strTotalAmt;
    double sum = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.carrt_fragment, container, false);
        intializeView(view);
        bindListner();
        startWork();
        return view;
    }
    /* ------------------------------------- intialize all Views that are used in this activity --------------------------------------*/

    private void intializeView(View view) {
        activity = getActivity();
        dbHelper = new DbHelper(activity);
        dialog = new NewProgressBar(activity);
        tvTotalAmount = (TextView) view.findViewById(R.id.tv_total_price);
        rv_ShoppingBag = (RecyclerView) view.findViewById(R.id.rv_mycart);
        btn_PlaceOrder = (RelativeLayout) view.findViewById(R.id.rl_button);
    }

    /*--------------------------------------- bind all views that are used in this activity ------------------------------------------*/

    private void bindListner() {
        btn_PlaceOrder.setOnClickListener(this);
    }

    /*--------------------------------------------------- Click Listner ------------------------------------------------------------*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_button:
                Intent i = new Intent(activity, CheckOutActivity.class);
                startActivity(i);
                return;
        }

    }

    /* ----------------------------------------------------- start Working --------------------------------------------------------*/

    private void startWork() {
        dialog.show();
        getSqliteData();
        if (Config.CartData.equals("")) {
            dialog.dismiss();
            btn_PlaceOrder.setVisibility(View.GONE);
            Toast.makeText(activity, "Cart is Empty", Toast.LENGTH_SHORT).show();
        } else {
            dialog.dismiss();
            allShopingDataSetIntoRecyclerView();
            btn_PlaceOrder.setVisibility(View.VISIBLE);
        }
    }
    /* ----------------------------------------- get data from local storage and convert into json --------------------------------*/

    private void getSqliteData() {

        Cursor cursor = dbHelper.getData();
        if (cursor.getCount() == 0) {
            Log.e("Error", "no Data");
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
        Log.d("datajson", "data" + datajson);
        Config.CartData = datajson;
    }

    /* --------------------------------------------- Recycler view set json data ------------------------------------------------------*/

    private void allShopingDataSetIntoRecyclerView() {
        JSONArray jArray = null;
        try {
            jArray = new JSONArray(Config.CartData);
            list.clear();
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                ShoppingBagModel shoppingBagModel = new ShoppingBagModel();
                shoppingBagModel.setId(json_data.getString("Id"));
                shoppingBagModel.setName(json_data.getString("Name"));
                String img = json_data.getString("Image");
                img.replaceAll("\\\\", "");
                shoppingBagModel.setImage(img);
                shoppingBagModel.setQuantity(json_data.getString("Quantity"));
                shoppingBagModel.setPrice(json_data.getString("Price"));

                shoppingBagModel.setPID(json_data.getString("P_ID"));
                list.add(shoppingBagModel);

                rv_ShoppingBag.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
                adapter = new ShopingBagAdapter(activity, list);
                rv_ShoppingBag.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                strAmount = Float.parseFloat(json_data.getString("Price"));
                strQty = Integer.parseInt(json_data.getString("Quantity"));
            }

            Log.e("strAmount", "" + strAmount);
            Log.e("strQty", "" + strQty);
            strTotalAmt = strAmount * strQty; // total amount every product acoording to quantity
            Log.e("strTotalAmt", "" + strTotalAmt);
            sum = strTotalAmt + sum; // sum of all products
            Log.e("totalamt", "" + sum);
            Config.paymentAmount = String.valueOf(sum);
            tvTotalAmount.setText(String.valueOf(sum));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }


}
