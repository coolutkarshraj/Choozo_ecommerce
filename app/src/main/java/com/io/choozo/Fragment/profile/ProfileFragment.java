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

import com.io.choozo.R;
import com.io.choozo.adapter.profileadapter.ProfileFragmentAdapter;

public class ProfileFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    ProfileFragmentAdapter profileFragmentAdapter;
    Activity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profilefragment,container,false);
        intializeView(view);
        return  view;
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
        profileFragmentAdapter.addFragment(new Profile_Information(),"Profile");
        profileFragmentAdapter.addFragment(new MyOrder(),"My order");
        profileFragmentAdapter.addFragment(new Wishlist(),"Wishlist");
        profileFragmentAdapter.addFragment(new SavedAddress(),"Saved Address");
        viewPager.setAdapter(profileFragmentAdapter);
    }
}
