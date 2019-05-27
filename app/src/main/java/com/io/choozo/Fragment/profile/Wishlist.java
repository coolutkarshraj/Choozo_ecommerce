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
import com.io.choozo.adapter.profileadapter.WishListRVAdapter;
import com.io.choozo.model.dummydataModel.WishListDataModel;

import java.util.ArrayList;
import java.util.List;

public class Wishlist extends Fragment {

    RecyclerView rvWishList;
    Activity activity;
    WishListRVAdapter adapter;
    List<WishListDataModel> item = new ArrayList<>();

    public Wishlist(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.wishlist,container,false);
        intializeView(v);
        return v;
    }

    private void intializeView(View v) {
        activity = getActivity();
        rvWishList = (RecyclerView)v.findViewById(R.id.rv_wishlist);
        setDataToRV();
    }

    private void setDataToRV() {
        rvWishList.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));
        item.clear();
        item.add(new WishListDataModel(R.drawable.bluestriptop,"Blue Strip Top","1299","1500","In Stock",R.color.green));
        item.add(new WishListDataModel(R.drawable.roundneckdree,"Round Neck Dress","2399","3000","Out of Stock",R.color.red));
        item.add(new WishListDataModel(R.drawable.green,"Green Crop T- Shirt","800","1400","In Stock",R.color.green));
        adapter = new WishListRVAdapter(activity,item);
        rvWishList.setAdapter(adapter);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }

}
