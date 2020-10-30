package com.io.choozo.Fragment.Home;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.io.choozo.ApiCaller;
import com.io.choozo.Config;
import com.io.choozo.R;
import com.io.choozo.UrlLocator;
import com.io.choozo.adapter.BasicAdapter.ShopingCategoryAdapter;
import com.io.choozo.model.dataModel.CategoryDataModel;
import com.io.choozo.model.dataModel.ChildDataModel;
import com.io.choozo.model.dataModel.SubChildDataModel;
import com.io.choozo.model.responseModel.CategoryResponseModel;
import com.io.choozo.model.responseModel.GetBannerListResponseModel;
import com.io.choozo.util.CategorySubCatChildCat;
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
    TextView tvSliderName;
    RecyclerView rv_Shop;
    String endPoint,endPointBanner,strImagePath,endPointImageResize;
    ShopingCategoryAdapter adapter;
    List<CategoryDataModel> list = new ArrayList<>();
    List<ChildDataModel> list1 = new ArrayList<>();
    List<SubChildDataModel> list2 = new ArrayList<>();
    public static CategorySubCatChildCat ad_interface;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment,container,false);
        initializeView(view);
        startWorking();
        return  view;
    }

    /* ------------------------------------------- intialize all view that are used in this activity -----------------------------------*/

    private void initializeView(View view) {
        activity = getActivity();
        tvSliderName = (TextView)view.findViewById(R.id.tv_slider_name);
        sliderLayout = view.findViewById(R.id.image);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(2);
        rv_Shop = view.findViewById(R.id.rv_shop);
        StaggeredGridLayoutManager gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv_Shop.setLayoutManager(gaggeredGridLayoutManager);
    }

     /* ------------------------------------------------------- start work ------------------------------------------------------------*/

    private void startWorking() {
        getBannerApi();
        ApiCallData();
    }

    /*-------------------------------------------------- get Banner from Api ---------------------------------------------------------*/

    private void getBannerApi() {
        apiUrl();
        ApiCaller.getBanner(activity, endPointBanner, new FutureCallback<GetBannerListResponseModel>() {
            @Override
            public void onCompleted(Exception e, GetBannerListResponseModel result) {
                if(e!=null){
                    return;
                }
                getBannerDataFromApi(result);
            }
        });
    }

    /* ---------------------------------------------------- banner data from api response -------------------------------------------*/

    private void getBannerDataFromApi(GetBannerListResponseModel result) {
        if(result.getStatus()){
            for (int i=0 ; i<result.getData().size();i++) {
                strImagePath = result.getData().get(i).getAvatarPath();
                //tvSliderName.setText(result.getData().get(i).getTitle());
                endPointImageResize = "https://admin.dincharyamart.com/api/media/render?path="+strImagePath;
                SliderView sliderView = new DefaultSliderView(activity);
                sliderView.setImageUrl(endPointImageResize);
                sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
                sliderView.setDescription(result.getData().get(i).getTittle());
                ((DefaultSliderView) sliderView).setDescriptionTextSize(20);

                final int finalI = i;
                sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                    @Override
                    public void onSliderClick(SliderView sliderView) {

                    }
                });
                sliderLayout.addSliderView(sliderView);
            }
        }else{
            Toast.makeText(activity, "Something went Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    /* ------------------------------------------------------ Api url endPoints ------------------------------------------------------*/

    private void apiUrl() {
        endPoint = Config.Url.categoryList;
        endPointBanner = Config.Url.getBanner;
    }

    /* -------------------------------------------------get all category list api----------------------------------------------------*/

    private void ApiCallData() {
        apiUrl();
        ApiCaller.getCategoryList(activity, endPoint, new
                FutureCallback<CategoryResponseModel>() {
                    @Override
                    public void onCompleted(Exception e, CategoryResponseModel result) {
                        if(e!=null){
                            return;
                        }
                        setAdapterTabs(result);
                        Config.categoryResponseModel = result;

                    }
                });

    }

    /* ------------------------------------------------- data set into recycler view Adapter --------------------------------------------*/

    private void setAdapterTabs(CategoryResponseModel result) {
        adapter = new ShopingCategoryAdapter(activity,ad_interface, result);
        rv_Shop.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Config.childDataModel = list1;
        Config.subChildDataModels = list2;
    }

}
