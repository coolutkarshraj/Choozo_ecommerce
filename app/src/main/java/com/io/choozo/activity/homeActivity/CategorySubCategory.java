package com.io.choozo.activity.homeActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.BubbleThumbRangeSeekbar;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.io.choozo.Config;
import com.io.choozo.R;
import com.io.choozo.adapter.CategoryAdapter;
import com.io.choozo.adapter.ItemCategoryAdapter;
import com.io.choozo.adapter.SubCategoryAdapter;
import com.io.choozo.model.dummydataModel.ItemCatModel;

import java.util.ArrayList;
import java.util.List;

public class CategorySubCategory extends AppCompatActivity implements View.OnClickListener {

    RecyclerView catRecyclerView,subcategoryrecyclerview,itemsRecyclerView;
    Activity activity;
    ImageView back;
    RelativeLayout rlFilter;
    ImageView Cancel ,filterbtn;
    CategoryAdapter categoryAdapter;
    SubCategoryAdapter subCategoryAdapter;
    ItemCategoryAdapter itemCategoryAdapter;
    List<ItemCatModel> item = new ArrayList<>();
    CrystalRangeSeekbar  rangeSeekbar;
    TextView tvMin,tvMax;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_sub_category);
        intilaizeviews();
        bindListner();
        startWorking();
    }




    private void intilaizeviews() {
        activity = CategorySubCategory.this;
        back = (ImageView)findViewById(R.id.back);
        catRecyclerView = (RecyclerView)findViewById(R.id.rv_category);
        subcategoryrecyclerview = (RecyclerView)findViewById(R.id.rv_subcategory);
        itemsRecyclerView = (RecyclerView)findViewById(R.id.rv_items_catsub);
        filterbtn = (ImageView)findViewById(R.id.filterbtn);
        rlFilter = (RelativeLayout) findViewById(R.id.rl_filter);
        Cancel = (ImageView)findViewById(R.id.im_cancel);
        rangeSeekbar = (CrystalRangeSeekbar )findViewById(R.id.rangeSeekbar1);
        tvMin = (TextView)findViewById(R.id.textMin1);
        tvMax = (TextView)findViewById(R.id.textMax1);

    }
    private void bindListner() {

        back.setOnClickListener(this);
        filterbtn.setOnClickListener(this);
        Cancel.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back :
                onBackPressed();
                return;
            case  R.id.filterbtn :
                rlFilter.setVisibility(View.VISIBLE);
                return;
            case R.id.im_cancel :
                 rlFilter.setVisibility(View.GONE);
                 return;
        }

    }

    private void startWorking() {
        categoryRecyclerViewData();
        subCategoryRecyclerViewData();
        CategorySubCategoryDataSetToRv();
        seekBarSet();
    }


    /* -----------------------------------------------child data set into recyclerView-------------------------------------------*/

    private void categoryRecyclerViewData() {
        catRecyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        categoryAdapter = new CategoryAdapter(activity,Config.childDataModel);
        catRecyclerView.setAdapter(categoryAdapter);
    }

    /*------------------------------------------------ Sub Child Data into recyclerView-------------------------------------*/

    private void subCategoryRecyclerViewData() {
        subcategoryrecyclerview.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        subCategoryAdapter = new SubCategoryAdapter(activity,Config.subChildDataModels);
        subcategoryrecyclerview.setAdapter(subCategoryAdapter);
    }

    private void CategorySubCategoryDataSetToRv() {

        itemsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        item.clear();
        item.add(new ItemCatModel(R.drawable.boy,"Round Neck Dress","1200","2000"));
        item.add(new ItemCatModel(R.drawable.redbluetop,"Red Blue Strip Top","3000","3500"));
        item.add(new ItemCatModel(R.drawable.bluestriptop,"Blue Strip Top","1800","2200"));
        item.add(new ItemCatModel(R.drawable.green,"Green Crop T- Shirt","1200","1800"));
        itemCategoryAdapter = new ItemCategoryAdapter(this,item);
        itemsRecyclerView.setAdapter(itemCategoryAdapter);
    }

    private void seekBarSet() {
        // set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMin.setText(String.valueOf(minValue));
                tvMax.setText(String.valueOf(maxValue));
            }
        });

// set final value listener
        rangeSeekbar.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number minValue, Number maxValue) {
                Log.d("CRS=>", String.valueOf(minValue) + " : " + String.valueOf(maxValue));
            }
        });
    }

}
