package com.io.choozo.Fragment.hotOffer;

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
import android.widget.Toast;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.io.choozo.ApiCaller;
import com.io.choozo.Config;
import com.io.choozo.R;
import com.io.choozo.adapter.HotOfferRvAdapter;
import com.io.choozo.adapter.hotOfferAdapter.FeaturedProductRvAdapter;
import com.io.choozo.model.dummydataModel.OfferDataModel;
import com.io.choozo.model.responseModel.FeaturedProductResponseModel;
import com.io.choozo.util.NewProgressBar;
import com.koushikdutta.async.future.FutureCallback;

import java.util.ArrayList;
import java.util.List;

public class HotOfferFragment extends Fragment {

    RecyclerView rvFeaturedProduct;
    FeaturedProductRvAdapter adapter;
    Activity activity;
    NewProgressBar dialog;
    String endPoint;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hot_offer,container,false);
        intializeViews(view);
        startWorking();
        return  view;
    }

    private void intializeViews(View view) {
        activity = getActivity();
        dialog = new NewProgressBar(activity);
        rvFeaturedProduct = (RecyclerView)view.findViewById(R.id.rv_featured_product);
        rvFeaturedProduct.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false));

    }


    private void startWorking() {
        dataSetIntoRv();
    }

    private void apiUrl(){
     endPoint = Config.Url.getfeaturedProduct+"limit=0&offset=0&keyword=&sku=&count=false";
    }

    private void dataSetIntoRv() {
     dialog.show();
     apiUrl();
        ApiCaller.getFeaturedProduct(activity, endPoint, new FutureCallback<FeaturedProductResponseModel>() {
            @Override
            public void onCompleted(Exception e, FeaturedProductResponseModel result) {
                if(e!=null){
                    return;
                }
                feturedProductData(result);
            }
        });


    }

    private void feturedProductData(FeaturedProductResponseModel result) {
        if(result.getStatus() == 1){
            adapter = new FeaturedProductRvAdapter(activity,result.getData());
            rvFeaturedProduct.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            dialog.dismiss();
        }else {
            Toast.makeText(activity, "something went wrong", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }
    }
}
