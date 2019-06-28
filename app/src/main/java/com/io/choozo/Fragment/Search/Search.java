package com.io.choozo.Fragment.Search;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.io.choozo.ApiCaller;
import com.io.choozo.Config;
import com.io.choozo.Fragment.Home.HomeFragment;
import com.io.choozo.R;
import com.io.choozo.adapter.BasicAdapter.CategoryAdapter;
import com.io.choozo.adapter.SearchAdapter.SearchProductRvAdapter;
import com.io.choozo.model.dataModel.SearchResponseDataModel.SearchProductData;
import com.io.choozo.model.dataModel.SearchResponseDataModel.SearchProductList;
import com.io.choozo.model.responseModel.SearchResponseModel;
import com.koushikdutta.async.future.FutureCallback;

import java.util.ArrayList;
import java.util.List;

import static com.io.choozo.activity.homeActivity.CategorySubCategory.catRecyclerView;

public class Search extends Fragment {

    Activity activity;
    RecyclerView rv_search;
    String endPoint;
    SearchProductRvAdapter adapter;
    SearchView searchView;
    List<SearchProductList> item = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_searchview, container, false);
        intializeViews(view);
        bindListner();
        startWorking();
        return view;
    }

    /*--------------------------------------------- intialize all Views that are used in this activity -------------------------------*/

    private void intializeViews(View view) {
        activity = getActivity();
        rv_search = (RecyclerView) view.findViewById(R.id.rv_search);
        searchView = (SearchView) view.findViewById(R.id.serachview);
        rv_search.setLayoutManager(new GridLayoutManager(activity, 2));
    }

    /* --------------------------------------------- bind all Views that are used in this activity ---------------------------------*/

    private void bindListner() {

    }

    /*----------------------------------------------------------- start working ----------------------------------------------------*/

    private void startWorking() {
        SearchProductallApiData();
        searchViewSetUp();
    }


    /* -------------------------------------------------------- api end Point------------------------------------------------------*/

    private void apiUrl(){
        endPoint = Config.Url.searchProduct;
    }

    /*------------------------------------------------------------ api Call -------------------------------------------------------*/

    private void SearchProductallApiData() {
        apiUrl();
        ApiCaller.getsearchData(activity, endPoint, new FutureCallback<SearchResponseModel>() {
            @Override
            public void onCompleted(Exception e, SearchResponseModel result) {
                if(e!=null){
                    return;
                }else {
                    rvSetup(result);
                }
            }
        });
    }

    /*------------------------------------------------ api data set into recycler View --------------------------------------------*/

    private void rvSetup(SearchResponseModel result) {
        if(result.getStatus() == 1){
            adapter = new SearchProductRvAdapter(activity,result.getData().getProductList());
            rv_search.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        else {
            Toast.makeText(activity, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    /*--------------------------------------------------- SearchView SetUp -------------------------------------------------------*/

    private void searchViewSetUp() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //rv_search.setVisibility(View.VISIBLE);
                s = s.toLowerCase();
                List<SearchProductList> newlist = new ArrayList<>();
                for (SearchProductList productList : item) {
                    String name = productList.getName();
                    if (name.contains(s))
                        newlist.add(productList);
                }
                adapter.setFilter(newlist);
                return true;
            }



        });
    }


}
