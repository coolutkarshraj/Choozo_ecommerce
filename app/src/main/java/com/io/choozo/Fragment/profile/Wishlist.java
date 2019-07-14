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
import android.widget.Toast;

import com.google.gson.Gson;
import com.io.choozo.ApiCaller;
import com.io.choozo.Config;
import com.io.choozo.R;
import com.io.choozo.adapter.profileadapter.WishListRVAdapter;
import com.io.choozo.localStorage.PreferenceManager;
import com.io.choozo.model.dummydataModel.WishListDataModel;
import com.io.choozo.model.responseModel.GetWishlistResponseModel;
import com.io.choozo.model.responseModel.LoginResponseModel;
import com.io.choozo.util.NewProgressBar;
import com.koushikdutta.async.future.FutureCallback;

import java.util.ArrayList;
import java.util.List;

public class Wishlist extends Fragment {

    RecyclerView rvWishList;
    Activity activity;
    WishListRVAdapter adapter;
    PreferenceManager preferenceManager;
    String token, endPoint;
    NewProgressBar dialog;

    public Wishlist() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.wishlist, container, false);
        intializeView(v);
        startWorking();
        return v;
    }

    /*------------------------------------ intailize all Views that are used in this activity ----------------------------------------*/

    private void intializeView(View v) {
        activity = getActivity();
        dialog = new NewProgressBar(activity);
        preferenceManager = new PreferenceManager(activity);
        rvWishList = (RecyclerView) v.findViewById(R.id.rv_wishlist);
        rvWishList.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        getDataFromLocalStorage();
    }

    /* --------------------------------------- login data get from local storgae(for token get) --------------------------------------- */

    private void getDataFromLocalStorage() {
        Gson gson = new Gson();
        String getJson = preferenceManager.getString(PreferenceManager.loginData);
        LoginResponseModel obj = gson.fromJson(getJson, LoginResponseModel.class);
        token = obj.getData().getToken();
    }

    /* ------------------------------------------------ start working ------------------------------------------------------------------*/

    private void startWorking() {
        getWishListDataRv();
    }

    /* ----------------------------------------- Api url for get Wishlist Product ------------------------------------------------------*/

    private void apiUrl() {
        endPoint = Config.Url.getWishlistProduct;
    }

    /* ------------------------------------------------------------ Api Call ----------------------------------------------------------*/

    private void getWishListDataRv() {
        apiUrl();
        ApiCaller.getWishlistList(activity, endPoint, token,
                new FutureCallback<GetWishlistResponseModel>() {
                    @Override
                    public void onCompleted(Exception e, GetWishlistResponseModel result) {
                        if (e != null) {
                            return;
                        }
                        wishlistData(result);
                    }
                });
    }

    /*-------------------------------------------- api Data set into recycler view ---------------------------------------------------*/

    private void wishlistData(GetWishlistResponseModel result) {
        if (result.getStatus() == 1) {
            adapter = new WishListRVAdapter(activity, result.getData());
            rvWishList.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }else {

            Toast.makeText(activity, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }

}
