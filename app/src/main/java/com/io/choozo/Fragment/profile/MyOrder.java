package com.io.choozo.Fragment.profile;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.io.choozo.R;
import com.io.choozo.adapter.profileadapter.MyOrderRVAdapter;
import com.io.choozo.model.dummydataModel.MyOrderModel;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

public class MyOrder extends Fragment {
    RecyclerView rv_MyOrder;
    MyOrderRVAdapter adapter;
    List<MyOrderModel> item = new ArrayList<>();
    Activity activity;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.my_order,container,false);
        intializeView(v);
        bindListner();
        startWorking();
        return v;
    }

    private void intializeView(View v) {
        activity = getActivity();
        rv_MyOrder = (RecyclerView)v.findViewById(R.id.rv_my_order);
    }

    private void bindListner() {
    }


    private void startWorking() {
        myOrderDataSetToRecyclerView();
    }

    private void myOrderDataSetToRecyclerView() {
        rv_MyOrder.setLayoutManager(new LinearLayoutManager(activity, VERTICAL,false));
        item.clear();
        item.add(new MyOrderModel("08-Jun-2016","#SC123456","Dispatched",R.color.yellow));
        item.add(new MyOrderModel("10-Jun-2016","#SC123456","On Way",R.color.yellow));
        item.add(new MyOrderModel("28-Jun-2016","#SC123456","Deliverd",R.color.green));
        adapter = new MyOrderRVAdapter(activity,item);
        rv_MyOrder.setAdapter(adapter);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }


}
