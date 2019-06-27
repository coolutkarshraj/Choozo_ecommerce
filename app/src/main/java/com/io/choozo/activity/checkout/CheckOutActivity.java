package com.io.choozo.activity.checkout;


/*
 * Choozo Android
 * version 2.1
 * http://android.choozo.com
 *
 * Copyright (c) 2019 choozo ltd
 * Author choozo ltd <choozo@support.com>
 * Licensed under the MIT license.
 */


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.io.choozo.Config;
import com.io.choozo.Fragment.checkout.Confirmation;
import com.io.choozo.Fragment.checkout.Payment;
import com.io.choozo.Fragment.checkout.Shipping;
import com.io.choozo.R;
import com.io.choozo.adapter.fragmentadapter.CheckoutAdapter;

public class CheckOutActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager viewPager;
    CheckoutAdapter checkoutAdapter;
    TabLayout tabLayout;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        intializeView();
        bindListner();
    }


    /*----------------------------------- initialize all views that are used in this activity----------------------------*/

    private void intializeView() {
        checkoutAdapter = new CheckoutAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        Config.viewPager = viewPager;
        tabLayout = (TabLayout) findViewById(R.id.tab);
        setUpFragment(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        back = (ImageView) findViewById(R.id.back);

    }

    private void bindListner() {
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                return;
        }
    }

    /* -------------------------------------------------Set up of Fragments-------------------------------------*/

    private void setUpFragment(ViewPager viewPager) {
        CheckoutAdapter checkoutAdapter = new CheckoutAdapter(getSupportFragmentManager());
        checkoutAdapter.addFragment(new Shipping(), "Shipping");
        checkoutAdapter.addFragment(new Payment(), "Payment");
        checkoutAdapter.addFragment(new Confirmation(), "Confirmation");
        viewPager.setAdapter(checkoutAdapter);


    }


}
