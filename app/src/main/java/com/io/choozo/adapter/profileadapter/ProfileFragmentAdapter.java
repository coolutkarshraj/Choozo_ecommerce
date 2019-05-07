package com.io.choozo.adapter.profileadapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragmentAdapter extends FragmentStatePagerAdapter {

    List<Fragment> mfragments = new ArrayList<>();
    List<String> mfragmentlist = new ArrayList<>();

    public ProfileFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment f1,String s1){
        mfragments.add(f1);
        mfragmentlist.add(s1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mfragmentlist.get(position);
    }

    @Override
    public Fragment getItem(int i) {
        return mfragments.get(i);
    }

    @Override
    public int getCount() {
        return mfragmentlist.size();
    }
}
