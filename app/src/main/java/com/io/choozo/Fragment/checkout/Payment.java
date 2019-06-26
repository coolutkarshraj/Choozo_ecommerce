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
import android.widget.TextView;

import com.io.choozo.Config;
import com.io.choozo.R;

public class Payment extends Fragment implements View.OnClickListener {

    RelativeLayout rl_confirmation;
    Activity activity;
    TextView tv_amount;

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
        tv_amount = (TextView)view.findViewById(R.id.amount);
        tv_amount.setText(Config.paymentAmount);
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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }
}
