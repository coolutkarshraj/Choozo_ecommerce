package com.io.choozo.activity.About;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.io.choozo.ApiCaller;
import com.io.choozo.Config;
import com.io.choozo.R;
import com.io.choozo.model.responseModel.GetPageListResponseModel;
import com.io.choozo.util.NewProgressBar;
import com.io.choozo.util.userOnlineInfo;
import com.koushikdutta.async.future.FutureCallback;

public class AboutUsActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvTitle, tvDiscription, tvTitleCompany, tvDiscriptionCompany,
            tvTitleResource, tvDiscriptionResouce, tvTitlePageInfo, tvDiscriptionPageInfo;
    String endPoint;
    Activity activity;
    userOnlineInfo info;
    NewProgressBar dialog;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us2);
        intializeViews();
        bindListner();
        startWorking();
    }

    /* ---------------------------------------------intialize all views that are used in this activity------------------------------*/
    private void intializeViews() {
        activity = AboutUsActivity.this;
        info = new userOnlineInfo();
        dialog = new NewProgressBar(activity);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvDiscription = (TextView) findViewById(R.id.tv_title_discription);
        tvTitleCompany = (TextView) findViewById(R.id.tv_title_company);
        tvDiscriptionCompany = (TextView) findViewById(R.id.tv_title_company_description);
        tvTitleResource = (TextView) findViewById(R.id.tv_title_resource);
        tvDiscriptionResouce = (TextView) findViewById(R.id.tv_discription_resource);
        tvTitlePageInfo = (TextView) findViewById(R.id.tv_title_pageinfo);
        tvDiscriptionPageInfo = (TextView) findViewById(R.id.tv_description_pageinfo);
        back = (ImageView) findViewById(R.id.back);

    }

    /* ---------------------------------------------bind all views that are used in this activity----------------------------------*/
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

    private void startWorking() {
        getPageListApi();
    }

    private void apiUrl() {
        endPoint = Config.Url.getPageList;
    }

    /* ------------------------------------------------get page list Api--------------------------------------------------------*/

    private void getPageListApi() {
        if (info.isOnline(activity)) {
            apiUrl();
            ApiCaller.pageList(activity, endPoint, new
                    FutureCallback<GetPageListResponseModel>() {
                        @Override
                        public void onCompleted(Exception e, GetPageListResponseModel result) {
                            if (e != null) {
                                return;
                            }
                            dataGet(result);
                        }
                    });
        }

    }

    /* --------------------------------------------data get and set into views---------------------------------------*/

    private void dataGet(GetPageListResponseModel result) {
        if (result.getStatus() == 1) {
            tvTitle.setText(result.getData().get(2).getTitle());
            tvTitleCompany.setText(result.getData().get(0).getTitle());
            tvTitleResource.setText(result.getData().get(1).getTitle());
            tvTitlePageInfo.setText(result.getData().get(3).getTitle());
            tvDiscription.setText(Html.fromHtml(result.getData().get(2).getContent().toString().trim()));
            tvDiscriptionCompany.setText(Html.fromHtml(result.getData().get(0).getContent().toString().trim()));
            tvDiscriptionResouce.setText(Html.fromHtml(result.getData().get(1).getContent().toString().trim()));
            tvDiscriptionPageInfo.setText(Html.fromHtml(result.getData().get(3).getContent().toString().trim()));
        } else {
            Toast.makeText(activity, "" + result.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}
