package com.io.choozo.Fragment.profile;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.io.choozo.R;
import com.io.choozo.adapter.profileadapter.ProfileFragmentAdapter;
import com.io.choozo.localStorage.PreferenceManager;
import com.io.choozo.model.responseModel.LoginResponseModel;

public class ProfileFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    String token ="";
    ProfileFragmentAdapter profileFragmentAdapter;
    PreferenceManager preferenceManager;
    Activity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profilefragment,container,false);
        getDataFromLocalStorage();
        intializeView(view);

        return  view;
    }

    private void getDataFromLocalStorage() {
        preferenceManager = new PreferenceManager(getActivity());
        Gson gson = new Gson();
        String getJson = preferenceManager.getString(PreferenceManager.loginData);
        LoginResponseModel obj = gson.fromJson(getJson, LoginResponseModel.class);
        token = obj.getData().getToken();
    }


    private void intializeView(View view) {
        activity = getActivity();
        profileFragmentAdapter = new ProfileFragmentAdapter(((FragmentActivity) activity).getSupportFragmentManager());
        tabLayout = (TabLayout)view.findViewById(R.id.tab);
        viewPager = (ViewPager)view.findViewById(R.id.viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setUpFragment(viewPager);
    }

    private void setUpFragment(ViewPager viewPager) {
        ProfileFragmentAdapter profileFragmentAdapter = new ProfileFragmentAdapter(((FragmentActivity) activity).getSupportFragmentManager());
        if(token==""){

        }else{
            profileFragmentAdapter.addFragment(new Profile_Information(),"Profile");
        }

        profileFragmentAdapter.addFragment(new MyOrder(),"My order");
        profileFragmentAdapter.addFragment(new Wishlist(),"Wishlist");
        profileFragmentAdapter.addFragment(new SavedAddress(),"Saved Address");
        viewPager.setAdapter(profileFragmentAdapter);
    }
}
