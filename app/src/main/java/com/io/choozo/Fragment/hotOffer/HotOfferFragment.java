package com.io.choozo.Fragment.hotOffer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import com.io.choozo.adapter.hotOfferAdapter.Our_Brand_RvAdapter;
import com.io.choozo.adapter.hotOfferAdapter.TodayDealsRvAdapter;
import com.io.choozo.model.dummydataModel.OfferDataModel;
import com.io.choozo.model.responseModel.FeaturedProductResponseModel;
import com.io.choozo.model.responseModel.OurBrandsResponseModel;
import com.io.choozo.model.responseModel.TodayDealsResponseModel;
import com.io.choozo.util.NewProgressBar;
import com.koushikdutta.async.future.FutureCallback;

import java.util.ArrayList;
import java.util.List;

public class HotOfferFragment extends Fragment {

    RecyclerView rvFeaturedProduct, rvTodayDeals, rvOurBrands;
    FeaturedProductRvAdapter adapter;
    TodayDealsRvAdapter todayDealsAdapter;
    Our_Brand_RvAdapter brand_rvAdapter;
    Activity activity;
    NewProgressBar dialog;
    String endPoint, endPointTodayDeal, endPointBrands;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    final int duration = 10;
    final int pixelsToMove = 30;
    LinearLayoutManager linearLayoutManager,LayoutManager;
    final Runnable SCROLLING_RUNNABLE = new Runnable() {

        @Override
        public void run() {
            rvOurBrands.smoothScrollBy(pixelsToMove, 0);
            mHandler.postDelayed(this, duration);
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hot_offer, container, false);
        intializeViews(view);
        startWorking();
        return view;
    }

    /*-------------------------------------- intailize all Views that are used in this activity -------------------------------------*/

    private void intializeViews(View view) {
        activity = getActivity();
        dialog = new NewProgressBar(activity);
        rvFeaturedProduct = (RecyclerView) view.findViewById(R.id.rv_featured_product);
        rvFeaturedProduct.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        rvTodayDeals = (RecyclerView) view.findViewById(R.id.rv_today_deals);
        linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        rvTodayDeals.setLayoutManager(linearLayoutManager);

        rvOurBrands = (RecyclerView) view.findViewById(R.id.rv_our_brands);
        LayoutManager = new LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false);
        rvOurBrands.setLayoutManager(LayoutManager);

    }

    /* ------------------------------------------------------------  working start ---------------------------------------------------------*/
    private void startWorking() {
        featuredProductRv();
        todayDealsRv();
        ourBrandsRv();
    }


    /* ----------------------------------------------------------api endPoint------------------------------------------------------------*/

    private void apiUrl() {
        endPoint = Config.Url.getfeaturedProduct + "limit=0&offset=0&keyword=&sku=&count=false";
        endPointTodayDeal = Config.Url.getTodayDeals + "limit=0&offset=0&keyword=&sku=";
        endPointBrands = Config.Url.getbrandsDetail + "limit=10&offset=0&keyword=";
    }

    /*----------------------------------------------- featured recycler Api Call -----------------------------------------------------*/

    private void featuredProductRv() {
        dialog.show();
        apiUrl();
        ApiCaller.getFeaturedProduct(activity, endPoint, new FutureCallback<FeaturedProductResponseModel>() {
            @Override
            public void onCompleted(Exception e, FeaturedProductResponseModel result) {
                if (e != null) {
                    return;
                }
                feturedProductData(result);
            }
        });
    }

    /*------------------------------------------ Featured recycler view data set from api ---------------------------------------------*/

    private void feturedProductData(FeaturedProductResponseModel result) {
        if (result.getStatus() == 1) {
            dialog.dismiss();
            adapter = new FeaturedProductRvAdapter(activity, result.getData());
            rvFeaturedProduct.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        } else {
            Toast.makeText(activity, "something went wrong", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }
    }

    /* ------------------------------------------------------ Today Deals Api Call -----------------------------------------------------*/

    private void todayDealsRv() {
        apiUrl();
        ApiCaller.getTodayDeals(activity, endPointTodayDeal, new FutureCallback<TodayDealsResponseModel>() {
            @Override
            public void onCompleted(Exception e, TodayDealsResponseModel result) {
                if (e != null) {
                    return;
                }
                todayDealsProductData(result);
            }
        });
    }

    /* ---------------------------------------------- today deals api data set into recycler view -------------------------------------*/

    private void todayDealsProductData(TodayDealsResponseModel result) {
        if (result.getStatus() == 1) {
            todayDealsAdapter = new TodayDealsRvAdapter(activity, result.getData());
            rvTodayDeals.setAdapter(todayDealsAdapter);
            todayDealsAdapter.notifyDataSetChanged();
          //  autoScroll();

        } else {
            Toast.makeText(activity, "something went wrong", Toast.LENGTH_SHORT).show();


        }
    }

    /*------------------------------------------------- our brands api call---------------------------------------------------------*/
    private void ourBrandsRv() {
        apiUrl();
        ApiCaller.getOurBarnds(activity, endPointBrands, new FutureCallback<OurBrandsResponseModel>() {
            @Override
            public void onCompleted(Exception e, OurBrandsResponseModel result) {
                if (e != null) {
                    return;
                }
                ourBrandsData(result);
            }
        });
    }


    /* ------------------------------------------- our brands api data set into recycler view --------------------------------------*/

    private void ourBrandsData(OurBrandsResponseModel result) {
        if (result.getStatus() == 1) {
            brand_rvAdapter = new Our_Brand_RvAdapter(activity, result.getData());
            rvOurBrands.setAdapter(brand_rvAdapter);
            brand_rvAdapter.notifyDataSetChanged();
            autoScroll();
        } else {
            Toast.makeText(activity, "something went wrong", Toast.LENGTH_SHORT).show();
        }
    }


    /*------------------------------------------ auto Scroll Today Deals Recycler view ---------------------------------------------*/

    private void autoScroll() {

        rvOurBrands.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItem = LayoutManager.findLastCompletelyVisibleItemPosition();
                if (lastItem == LayoutManager.getItemCount() - 1) {
                    mHandler.removeCallbacks(SCROLLING_RUNNABLE);
                    Handler postHandler = new Handler();
                    postHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            rvOurBrands.setAdapter(null);
                            rvOurBrands.setAdapter(brand_rvAdapter);
                            mHandler.postDelayed(SCROLLING_RUNNABLE, 2000);
                        }
                    }, 2000);
                }
            }
        });
        mHandler.postDelayed(SCROLLING_RUNNABLE, 2000);
    }


}
