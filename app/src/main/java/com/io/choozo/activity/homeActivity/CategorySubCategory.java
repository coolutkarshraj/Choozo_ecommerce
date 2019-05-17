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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.BubbleThumbRangeSeekbar;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.io.choozo.Config;
import com.io.choozo.R;
import com.io.choozo.adapter.CategoryAdapter;
import com.io.choozo.adapter.ChooseColorAdapter;
import com.io.choozo.adapter.ChooseColorForFilterAdapter;
import com.io.choozo.adapter.ItemCategoryAdapter;
import com.io.choozo.adapter.SelectFilterSizeAdapter;
import com.io.choozo.adapter.SelectSizeAdapter;
import com.io.choozo.adapter.SubCategoryAdapter;
import com.io.choozo.model.dummydataModel.ChooseColorModel;
import com.io.choozo.model.dummydataModel.ItemCatModel;
import com.io.choozo.model.dummydataModel.SelectSizeDataMode;

import java.util.ArrayList;
import java.util.List;

public class CategorySubCategory extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

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
    String[] country = {"- Nothing Selected -","Prada", "Gucci", "Louis Vuittion", "Hermes","Tommy Hilfiger","Nike","Ralph Lauren","Levi Strauss & Co.",
    "Burberry","Adidas","Versace","Diesel","Calvin Klein"};
    String[] Category = {"- Nothing Selected -","Mens Fashion", "Womens Fashion", "Electronics", "Laptops","Provisional & Utensils","Baby & Kids"};
    String[] discount = {"- Nothing Selected -","20 %", "30 % ", "40 %", "50%"};
    Spinner spin,spinCategory,discountSpin;
    RecyclerView rv_color,rv_producrtsize;
    ChooseColorForFilterAdapter adapter;
    SelectFilterSizeAdapter selectSizeAdapter;
    List<SelectSizeDataMode> itemslectsize = new ArrayList<>();
    List<ChooseColorModel> item1 = new ArrayList<>();

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
        spin = (Spinner) findViewById(R.id.spinner);
        spinCategory = (Spinner) findViewById(R.id.spinner1);
        discountSpin = (Spinner) findViewById(R.id.spinner2);
        rv_producrtsize = (RecyclerView)findViewById(R.id.rv_productsize);
        rv_color = (RecyclerView)findViewById(R.id.rv_choosecolor);

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
        adapterSetToSpinner();
        rv_setSizeofProductData();
        rv_SetData();
        adapterSetToSpinnerforCategory();
        adapterSetToSpinnerfordiscount();
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

    /*--------------------------------------------------- list of brands--------------------------------------------------------------*/

    private void adapterSetToSpinner() {
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
    }
    /*--------------------------------------------------- list of brands--------------------------------------------------------------*/

    private void adapterSetToSpinnerforCategory() {
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Category);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCategory.setAdapter(aa);
    }
    /*--------------------------------------------------- discount--------------------------------------------------------------*/

    private void adapterSetToSpinnerfordiscount() {
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,discount);
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
        selectSizeAdapter = new SelectFilterSizeAdapter(activity,itemslectsize);
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
