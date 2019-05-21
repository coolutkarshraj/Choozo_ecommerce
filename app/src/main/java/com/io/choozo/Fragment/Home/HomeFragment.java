package com.io.choozo.Fragment.Home;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.io.choozo.ApiCaller;
import com.io.choozo.Config;
import com.io.choozo.R;
import com.io.choozo.adapter.ShopingCategoryAdapter;
import com.io.choozo.model.dataModel.CategoryDataModel;
import com.io.choozo.model.dataModel.ChildDataModel;
import com.io.choozo.model.dataModel.SubChildDataModel;
import com.io.choozo.model.responseModel.CategoryResponseModel;
import com.koushikdutta.async.future.FutureCallback;
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
    String endPoint;
    ShopingCategoryAdapter adapter;
    List<CategoryDataModel> list = new ArrayList<>();
    List<ChildDataModel> list1 = new ArrayList<>();
    List<SubChildDataModel> list2 = new ArrayList<>();


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
        StaggeredGridLayoutManager gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv_Shop.setLayoutManager(gaggeredGridLayoutManager);
    }


    private void startWorking() {
        setSliderViews();
        ApiCallData();
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

    private void apiUrl() {

        endPoint = Config.Url.categoryList;
    }

    private void ApiCallData() {
        apiUrl();
        ApiCaller.getCategoryList(activity, endPoint, new
                FutureCallback<CategoryResponseModel>() {
                    @Override
                    public void onCompleted(Exception e, CategoryResponseModel result) {
                        if(e!=null){
                            return;
                        }
                        setData(result);

                    }
                });

    }

    private void setData(CategoryResponseModel result) {
        list.clear();
        for (int i = 0 ;i < result.getData().size();i++){
            CategoryDataModel categoryDataModel = new CategoryDataModel();
            categoryDataModel.setName(result.getData().get(i).getName());
            categoryDataModel.setImage(result.getData().get(i).getImage());
            list.add(categoryDataModel);
                    list2.clear();
                    for(int j = 0 ; j<result.getData().get(i).getChildren().size(); j++){
                        ChildDataModel childDataModel = new ChildDataModel();
                        childDataModel.setName(result.getData().get(i).getChildren().get(j).getName());
                        list1.add(childDataModel);
                        list2.clear();
                        try {
                            for( int k =0; k< result.getData().get(i).getChildren().get(j).getChildren().size(); k++){
                                    SubChildDataModel subChildDataModel = new SubChildDataModel();
                                    subChildDataModel.setName(result.getData().get(i).getChildren().get(j).getChildren().get(k).getName());
                                    list2.add(subChildDataModel);
                                }
                                }catch (Exception e){
                                    System.out.println(e);
                                }
                        }
            }
        setAdapterTabs();

    }

    private void setAdapterTabs() {
        adapter = new ShopingCategoryAdapter(activity, list);
        rv_Shop.setAdapter(adapter);
        Config.childDataModel = list1;
        Config.subChildDataModels = list2;
    }


}
