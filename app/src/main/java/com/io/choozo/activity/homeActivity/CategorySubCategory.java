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
import android.view.View;
import android.widget.ImageView;

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
    CategoryAdapter categoryAdapter;
    SubCategoryAdapter subCategoryAdapter;
    ItemCategoryAdapter itemCategoryAdapter;
    List<ItemCatModel> item = new ArrayList<>();

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

    private void startWorking() {
        categoryRecyclerViewData();
        subCategoryRecyclerViewData();
        CategorySubCategoryDataSetToRv();
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


}
