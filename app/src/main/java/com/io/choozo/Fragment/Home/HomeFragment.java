package com.io.choozo.Fragment.Home;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.io.choozo.R;
import com.io.choozo.activity.homeActivity.MainActivity;
import com.io.choozo.adapter.ShopingCategoryAdapter;
import com.io.choozo.model.dummydataModel.ShopCategoryModel;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    Activity activity;
    SliderLayout sliderLayout;
    RecyclerView rv_Shop;
    ShopingCategoryAdapter adapter;
    List<ShopCategoryModel> list = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment,container,false);
        initializeView(view);
        startWorking();
        return  view;
    }

    private void initializeView(View view) {
        activity = getActivity();
        sliderLayout = view.findViewById(R.id.image);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(2);
        rv_Shop = view.findViewById(R.id.rv_shop);
    }


    private void startWorking() {
        setSliderViews();
        recyclerViewData();
    }



    private void setSliderViews() {

        for (int i = 0; i <= 2; i++) {

            SliderView sliderView = new DefaultSliderView(activity);

            switch (i) {
                case 0:
                    sliderView.setImageDrawable(R.drawable.shop1);
                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.shop2);
                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.shop3);
                    break;

            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {

                }
            });
            sliderLayout.addSliderView(sliderView);
        }
    }

    private void recyclerViewData() {
       StaggeredGridLayoutManager gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv_Shop.setLayoutManager(gaggeredGridLayoutManager);
        list.clear();
        list.add(new ShopCategoryModel(R.drawable.boy,"Kids Wear"));
        list.add(new ShopCategoryModel(R.drawable.boy,"Kids Wear"));
        list.add(new ShopCategoryModel(R.drawable.boy,"Kids Wear"));
        list.add(new ShopCategoryModel(R.drawable.boy,"Kids Wear"));
        list.add(new ShopCategoryModel(R.drawable.boy,"Kids Wear"));
        list.add(new ShopCategoryModel(R.drawable.boy,"Kids Wear"));
        adapter = new ShopingCategoryAdapter(activity, list);
        rv_Shop.setAdapter(adapter);


    }
}
