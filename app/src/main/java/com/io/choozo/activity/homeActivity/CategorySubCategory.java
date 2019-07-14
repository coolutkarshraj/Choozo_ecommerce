package com.io.choozo.activity.homeActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.io.choozo.ApiCaller;
import com.io.choozo.Config;
import com.io.choozo.Fragment.Home.HomeFragment;
import com.io.choozo.R;
import com.io.choozo.adapter.BasicAdapter.CategoryAdapter;
import com.io.choozo.adapter.BasicAdapter.ChooseColorForFilterAdapter;
import com.io.choozo.adapter.BasicAdapter.ItemCategoryAdapter;
import com.io.choozo.adapter.BasicAdapter.SelectFilterSizeAdapter;
import com.io.choozo.adapter.BasicAdapter.ShopingCategoryAdapter;
import com.io.choozo.adapter.BasicAdapter.SubCategoryAdapter;
import com.io.choozo.model.dummydataModel.ChooseColorModel;
import com.io.choozo.model.dummydataModel.ItemCatModel;
import com.io.choozo.model.dummydataModel.SelectSizeDataMode;
import com.io.choozo.model.responseModel.ProductListResponseModel;
import com.io.choozo.util.NewProgressBar;
import com.koushikdutta.async.future.FutureCallback;

import java.util.ArrayList;
import java.util.List;

public class CategorySubCategory extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    public static RecyclerView catRecyclerView, subcategoryrecyclerview, itemsRecyclerView;
    Activity activity;
    ImageView back;
    RelativeLayout rlFilter;
    ImageView Cancel, filterbtn;
    public static CategoryAdapter categoryAdapter;
    public static SubCategoryAdapter subCategoryAdapter;
    public static ItemCategoryAdapter itemCategoryAdapter;
    List<ItemCatModel> item = new ArrayList<>();
    CrystalRangeSeekbar rangeSeekbar;
    public static TextView tvMin, tvMax, toolbarName, tvDataNotFound;
    String[] country = {"- Nothing Selected -", "Prada", "Gucci", "Louis Vuittion", "Hermes", "Tommy Hilfiger", "Nike", "Ralph Lauren", "Levi Strauss & Co.",
            "Burberry", "Adidas", "Versace", "Diesel", "Calvin Klein"};
    String[] Category = {"- Nothing Selected -", "Mens Fashion", "Womens Fashion", "Electronics", "Laptops", "Provisional & Utensils", "Baby & Kids"};
    String[] discount = {"- Nothing Selected -", "20 %", "30 % ", "40 %", "50%"};
    Spinner spin, spinCategory, discountSpin;
    RecyclerView rv_color, rv_producrtsize;
    ChooseColorForFilterAdapter adapter;
    SelectFilterSizeAdapter selectSizeAdapter;
    List<SelectSizeDataMode> itemslectsize = new ArrayList<>();
    List<ChooseColorModel> item1 = new ArrayList<>();
    String intentName, endPoint;
    String intentCategoryId = "";
    String child = "";
    String subchild = "";
    NewProgressBar dialog;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_sub_category);
        intilaizeviews();
        bindListner();
        startWorking();

    }

    /* -------------------------------------------- intailize All Views that are used in this activity ---------------------------------*/

    private void intilaizeviews() {
        activity = CategorySubCategory.this;
        back = (ImageView) findViewById(R.id.back);
        dialog = new NewProgressBar(activity);
        catRecyclerView = (RecyclerView) findViewById(R.id.rv_category);
        subcategoryrecyclerview = (RecyclerView) findViewById(R.id.rv_subcategory);
        itemsRecyclerView = (RecyclerView) findViewById(R.id.rv_items_catsub);
        filterbtn = (ImageView) findViewById(R.id.filterbtn);
        rlFilter = (RelativeLayout) findViewById(R.id.rl_filter);
        Cancel = (ImageView) findViewById(R.id.im_cancel);
        rangeSeekbar = (CrystalRangeSeekbar) findViewById(R.id.rangeSeekbar1);
        tvMin = (TextView) findViewById(R.id.textMin1);
        tvMax = (TextView) findViewById(R.id.textMax1);
        spin = (Spinner) findViewById(R.id.spinner);
        spinCategory = (Spinner) findViewById(R.id.spinner1);
        discountSpin = (Spinner) findViewById(R.id.spinner2);
        rv_producrtsize = (RecyclerView) findViewById(R.id.rv_productsize);
        rv_color = (RecyclerView) findViewById(R.id.rv_choosecolor);
        toolbarName = (TextView) findViewById(R.id.tv_name);
        tvDataNotFound = (TextView) findViewById(R.id.tv_data_notFound);
        getDataFrimIntent();
    }

    /* ------------------------------------------------ get Data from Intent ------------------------------------------------------*/
    private void getDataFrimIntent() {
        Intent intent = getIntent();
        intentName = intent.getStringExtra("name");
        Config.toolbarName = intentName;
        intentCategoryId = String.valueOf(ShopingCategoryAdapter.cateId);
        Log.e("category", "" + intentCategoryId);
        toolbarName.setText(intentName);
    }

    private void bindListner() {

        back.setOnClickListener(this);
        filterbtn.setOnClickListener(this);
        Cancel.setOnClickListener(this);
        spin.setOnItemSelectedListener(this);
        spinCategory.setOnItemSelectedListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                return;
            case R.id.filterbtn:
                rlFilter.setVisibility(View.VISIBLE);
                return;
            case R.id.im_cancel:
                rlFilter.setVisibility(View.GONE);
                return;
        }
    }

    /* -------------------------------------------------------- start working ----------------------------------------------------*/

    private void startWorking() {
        categoryRecyclerViewData();
        subCategoryRecyclerViewData();
        ProductListApi();
        seekBarSet();
        adapterSetToSpinner();
        rv_setSizeofProductData();
        rv_SetData();
        adapterSetToSpinnerforCategory();
        adapterSetToSpinnerfordiscount();
    }


    /* -----------------------------------------------child data set into recyclerView-------------------------------------------*/

    private void categoryRecyclerViewData() {
        catRecyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        categoryAdapter = new CategoryAdapter(activity, HomeFragment.ad_interface, Config.childDataModel);
        catRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
    }

    /*------------------------------------------------ Sub Child CustomerRegistrationDataModel into recyclerView-------------------------------------*/

    private void subCategoryRecyclerViewData() {
        subcategoryrecyclerview.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        subCategoryAdapter = new SubCategoryAdapter(activity, Config.subChildDataModels);
        subcategoryrecyclerview.setAdapter(subCategoryAdapter);

    }


    /* ----------------------------------------------------------- Api url of product List ----------------------------------------------------*/
    private void apiUrl() {
        endPoint = Config.Url.productlist + "limit=10&offset=0&manufacturerId=&categoryId=" + intentCategoryId + "&keyword=&price=1&priceFrom=&priceT";

        // endPoint = Config.Url.productlist;
    }


    /*----------------------------------------------------------- Product list Api ----------------------------------------------------------*/

    private void ProductListApi() {
        dialog.show();
        apiUrl();
        ApiCaller.productList(activity, endPoint, new FutureCallback<ProductListResponseModel>() {
            @Override
            public void onCompleted(Exception e, ProductListResponseModel result) {
                if (e != null) {
                    return;
                }
                CategorySubCategoryDataSetToRv(result);
            }
        });

    }

    /* -------------------------------------------------------Api Data Set Into Recycler View-------------------------------------------------------*/

    private void CategorySubCategoryDataSetToRv(ProductListResponseModel result) {

        if (result.getStatus() == 1) {
            dialog.dismiss();
            if (result.getData().getProductList().isEmpty()) {
                tvDataNotFound.setVisibility(View.VISIBLE);
                itemsRecyclerView.setVisibility(View.GONE);
            } else {
                tvDataNotFound.setVisibility(View.GONE);
                itemsRecyclerView.setVisibility(View.VISIBLE);
                itemsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                itemCategoryAdapter = new ItemCategoryAdapter(activity, result.getData().getProductList());
                itemsRecyclerView.setAdapter(itemCategoryAdapter);
                itemCategoryAdapter.notifyDataSetChanged();
            }
        } else {
            Toast.makeText(activity, "" + result.getMessage(), Toast.LENGTH_SHORT).show();
            dialog.dismiss();

        }

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

    /*--------------------------------------------------- list of brands--------------------------------------------------------------*/

    private void adapterSetToSpinner() {
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
    }
    /*--------------------------------------------------- list of brands--------------------------------------------------------------*/

    private void adapterSetToSpinnerforCategory() {
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Category);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCategory.setAdapter(aa);
    }
    /*--------------------------------------------------- discount--------------------------------------------------------------*/

    private void adapterSetToSpinnerfordiscount() {
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, discount);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        discountSpin.setAdapter(aa);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //  Toast.makeText(getApplicationContext(),country[position] , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /* ---------------------------------------------------------------size -----------------------------------------------------------*/


    private void rv_setSizeofProductData() {
        rv_producrtsize.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        itemslectsize.clear();
        itemslectsize.add(new SelectSizeDataMode("S"));
        itemslectsize.add(new SelectSizeDataMode("M"));
        itemslectsize.add(new SelectSizeDataMode("L"));
        itemslectsize.add(new SelectSizeDataMode("XL"));
        itemslectsize.add(new SelectSizeDataMode("XXL"));
        itemslectsize.add(new SelectSizeDataMode("XXXL"));
        selectSizeAdapter = new SelectFilterSizeAdapter(activity, itemslectsize);
        rv_producrtsize.setAdapter(selectSizeAdapter);

    }

    private void rv_SetData() {
        rv_color.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        item1.clear();
        item1.add(new ChooseColorModel(R.color.skyblue));
        item1.add(new ChooseColorModel(R.color.green));
        item1.add(new ChooseColorModel(R.color.red));
        item1.add(new ChooseColorModel(R.color.lighred));
        item1.add(new ChooseColorModel(R.color.black));
        item1.add(new ChooseColorModel(R.color.colorAccent));
        item1.add(new ChooseColorModel(R.color.grey));

        adapter = new ChooseColorForFilterAdapter(activity, item1);
        rv_color.setAdapter(adapter);
    }

}
