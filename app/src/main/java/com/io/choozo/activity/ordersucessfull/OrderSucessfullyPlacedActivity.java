package com.io.choozo.activity.ordersucessfull;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.io.choozo.R;
import com.io.choozo.activity.homeActivity.MainActivity;

public class OrderSucessfullyPlacedActivity extends AppCompatActivity implements View.OnClickListener {

    String strId;
    Activity activity;
    TextView tvOrderId;
    RelativeLayout rl_continue_shopping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_sucessfully_placed);
        intializeViews();
        getDataFromIntent();
        bindListner();
        startWorking();
    }


    /*----------------------------------------- intialize all views that are used in this activity ----------------------------------*/

    private void intializeViews() {
        activity = OrderSucessfullyPlacedActivity.this;
        tvOrderId = (TextView)findViewById(R.id.tv_orderid);
        rl_continue_shopping = (RelativeLayout) findViewById(R.id.rl_continue_shopping);
    }

    /*--------------------------------------------------- get Data From Intent -----------------------------------------------------*/

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            strId=intent.getStringExtra("orderid");
        }
    }

    /*----------------------------------------- bind all views that are used in this activity -------------------------------------*/

    private void bindListner() {
        rl_continue_shopping.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_continue_shopping:
                Intent i = new Intent(activity, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                return;
        }

    }

    /*------------------------------------------------------ start working -----------------------------------------------------*/
    private void startWorking() {
        tvOrderId.setText(strId);
    }



}
