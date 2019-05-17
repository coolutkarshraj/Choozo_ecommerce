package com.io.choozo.Fragment.MyCart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.io.choozo.R;
import com.io.choozo.activity.checkout.CheckOutActivity;
import com.io.choozo.adapter.ShopingBagAdapter;
import com.io.choozo.adapter.ShopingCategoryAdapter;
import com.io.choozo.model.dataModel.CategoryDataModel;
import com.io.choozo.model.dummydataModel.ShoppingBagModel;

import java.util.ArrayList;
import java.util.List;

public class MyCartFragment extends Fragment implements View.OnClickListener{
    RecyclerView rv_ShoppingBag;
    ShopingBagAdapter adapter;
    List<ShoppingBagModel> list = new ArrayList<>();
    Activity activity;
    RelativeLayout btn_PlaceOrder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.carrt_fragment,container,false);
        intializeView(view);
        bindListner();
        return  view;
    }

    private void intializeView(View view) {
        activity = getActivity();
        rv_ShoppingBag = (RecyclerView)view.findViewById(R.id.rv_mycart);
        btn_PlaceOrder = (RelativeLayout) view.findViewById(R.id.rl_button) ;
        allShopingDataSetIntoRecyclerView();
    }


    private void bindListner() {
        btn_PlaceOrder.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_button :
                Intent i =new Intent(activity, CheckOutActivity.class);
                startActivity(i);
                return;
        }

    }

    private void allShopingDataSetIntoRecyclerView() {
        rv_ShoppingBag.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        list.clear();
        list.add(new ShoppingBagModel(R.drawable.bluestriptop,"Blue Strip Top","Size: S","Color: Red","3299","4999","3"));
        list.add(new ShoppingBagModel(R.drawable.roundneckdree,"Round Neck Dress","Size: S","Color: Black","1299","2000","1"));
        list.add(new ShoppingBagModel(R.drawable.green,"Green Crop T- Shirt","Size: M","Color: Green","900","20000","5"));
        list.add(new ShoppingBagModel(R.drawable.green,"Green Crop T- Shirt","Size: M","Color: Green","900","20000","5"));
        list.add(new ShoppingBagModel(R.drawable.green,"Green Crop T- Shirt","Size: M","Color: Green","900","20000","5"));
        list.add(new ShoppingBagModel(R.drawable.green,"Green Crop T- Shirt","Size: M","Color: Green","900","20000","5"));
        adapter = new ShopingBagAdapter(activity,list);
        rv_ShoppingBag.setAdapter(adapter);

    }


}
