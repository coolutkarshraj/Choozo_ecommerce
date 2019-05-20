package com.io.choozo.activity.loginRegistrationflow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.io.choozo.R;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initializeViews();
        bindListner();
    }

    private void initializeViews() {
        back = (ImageView)findViewById(R.id.back);
    }

    private void bindListner() {
        back.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back :
                onBackPressed();
                return;
        }

    }
}
