package com.io.choozo.Fragment.checkout;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.io.choozo.Config;
import com.io.choozo.R;

public class Payment extends Fragment implements View.OnClickListener {

    RelativeLayout rl_confirmation;
    Activity activity;

    public Payment(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment,container,false);
        intializeViews(view);
        bindListner();
        return  view;
    }

    /* -------------------------------------- intialize all Views that are used in this activity -----------------------------------*/

    private void intializeViews(View view) {
        activity = getActivity();
        rl_confirmation = (RelativeLayout)view.findViewById(R.id.rl_confirmation);
    }

    /*--------------------------------------------- bind all views that are used in this activity ---------------------------------- */

    private void bindListner(){
      rl_confirmation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_confirmation :
                Config.viewPager.setCurrentItem(2);
        }

    }
}
