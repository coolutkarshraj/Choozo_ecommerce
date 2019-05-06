package com.io.choozo.Fragment.checkout;

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
import android.widget.RelativeLayout;

import com.io.choozo.R;
import com.io.choozo.adapter.ConfirmationAdapter;
import com.io.choozo.adapter.ShopingBagAdapter;
import com.io.choozo.model.dummydataModel.ConfirmationModel;
import com.io.choozo.model.dummydataModel.ShoppingBagModel;

import java.util.ArrayList;
import java.util.List;

public class Confirmation extends Fragment {
    RecyclerView rv_Confirmation;
    ConfirmationAdapter adapter;
    List<ConfirmationModel> list = new ArrayList<>();
    Activity activity;

    public Confirmation(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_confirmation,container,false);
        intializeView(view);
        return  view;
    }

    private void intializeView(View view) {
        activity = getActivity();
        rv_Confirmation = (RecyclerView)view.findViewById(R.id.rv_items);

        allConfirmationDatasetToRV();
    }

    private void allConfirmationDatasetToRV() {
        rv_Confirmation.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        list.clear();
        list.add(new ConfirmationModel(R.drawable.bluestriptop,"Blue Strip Top","Size: S","Color: Red","3299","4999","3"));
        list.add(new ConfirmationModel(R.drawable.roundneckdree,"Round Neck Dress","Size: S","Color: Black","1299","2000","1"));
        list.add(new ConfirmationModel(R.drawable.green,"Green Crop T- Shirt","Size: M","Color: Green","900","20000","5"));
        list.add(new ConfirmationModel(R.drawable.green,"Green Crop T- Shirt","Size: M","Color: Green","900","20000","5"));
        list.add(new ConfirmationModel(R.drawable.green,"Green Crop T- Shirt","Size: M","Color: Green","900","20000","5"));
        list.add(new ConfirmationModel(R.drawable.green,"Green Crop T- Shirt","Size: M","Color: Green","900","20000","5"));
        adapter = new ConfirmationAdapter(activity,list);
        rv_Confirmation.setAdapter(adapter);
    }

}
