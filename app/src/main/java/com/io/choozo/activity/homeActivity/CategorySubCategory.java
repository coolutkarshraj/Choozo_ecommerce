package com.io.choozo.activity.homeActivity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.io.choozo.Config;
import com.io.choozo.R;
import com.io.choozo.adapter.CategoryAdapter;
import com.io.choozo.adapter.SubCategoryAdapter;

public class CategorySubCategory extends AppCompatActivity {

    RecyclerView catRecyclerView,subcategoryrecyclerview;
    Activity activity;
    CategoryAdapter categoryAdapter;
    SubCategoryAdapter subCategoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_sub_category);
        intilaizeviews();
        startWorking();
    }


    private void intilaizeviews() {
        activity = CategorySubCategory.this;
        catRecyclerView = (RecyclerView)findViewById(R.id.rv_category);
        subcategoryrecyclerview = (RecyclerView)findViewById(R.id.rv_subcategory);


    }

    private void startWorking() {
        categoryRecyclerViewData();
      subCategoryRecyclerViewData();
    }


    private void categoryRecyclerViewData() {
        catRecyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        categoryAdapter = new CategoryAdapter(activity,Config.childDataModel);
        catRecyclerView.setAdapter(categoryAdapter);
    }

    private void subCategoryRecyclerViewData() {
        subcategoryrecyclerview.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        subCategoryAdapter = new SubCategoryAdapter(activity,Config.subChildDataModels);
        subcategoryrecyclerview.setAdapter(subCategoryAdapter);
    }


}
