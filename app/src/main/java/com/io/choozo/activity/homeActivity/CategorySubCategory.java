package com.io.choozo.activity.homeActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.provider.Settings;
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
import com.io.choozo.model.dataModel.StoreSubCategory;
import com.io.choozo.model.dummydataModel.ChooseColorModel;
import com.io.choozo.model.dummydataModel.ItemCatModel;
import com.io.choozo.model.dummydataModel.SelectSizeDataMode;
import com.io.choozo.model.responseModel.ProductListResponseModel;
import com.io.choozo.util.NewProgressBar;
import com.koushikdutta.async.future.FutureCallback;

import java.util.ArrayList;
import java.util.List;

public class CategorySubCategory extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    public static RecyclerView catRecyclerView, itemsRecyclerView;
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
    ArrayList<String> Category;
    ArrayList<String> subCategory ;
    Spinner spin, spinner_cat_id, spinnersub_category_id;
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
    private List<StoreSubCategory> storeSubCategory;
    private String storeSubCategoryId = "-1";
    private String storecategoryId = "-1";
    private String StoreId = "-1";


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_sub_category);
        intilaizeviews();
        bindListner();
        startWorking();
        adapterSetToSpinnerforCategory();

    }

    /* -------------------------------------------- intailize All Views that are used in this activity ---------------------------------*/

    private void intilaizeviews() {
        activity = CategorySubCategory.this;
        back = (ImageView) findViewById(R.id.back);
        dialog = new NewProgressBar(activity);
        catRecyclerView = (RecyclerView) findViewById(R.id.rv_category);
        itemsRecyclerView = (RecyclerView) findViewById(R.id.rv_items_catsub);
        filterbtn = (ImageView) findViewById(R.id.filterbtn);
        rlFilter = (RelativeLayout) findViewById(R.id.rl_filter);
        Cancel = (ImageView) findViewById(R.id.im_cancel);
        rangeSeekbar = (CrystalRangeSeekbar) findViewById(R.id.rangeSeekbar1);
        tvMin = (TextView) findViewById(R.id.textMin1);
        tvMax = (TextView) findViewById(R.id.textMax1);
        spin = (Spinner) findViewById(R.id.spinner);
        spinner_cat_id = (Spinner) findViewById(R.id.spinner_cat_id);
        spinnersub_category_id = (Spinner) findViewById(R.id.spinnersub_category_id);
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
        intentCategoryId = intent.getStringExtra("categoryId");;
        Log.e("category", "" + intentCategoryId);
        for ( int i = 0; i <  Config.categoryResponseModel.getData().size(); i++){
            if(intentCategoryId.equals(String.valueOf(Config.categoryResponseModel.getData().get(i).getStoreCategoryId()))){
                if( Config.categoryResponseModel.getData().get(i).getStore() == null){
                    Toast.makeText(activity, "No Product Found in This Category", Toast.LENGTH_SHORT).show();
                    finish();
                }
               else if( Config.categoryResponseModel.getData().get(i).getStore().size()!= 0){
                   StoreId= String.valueOf(Config.categoryResponseModel.getData().get(i).getStore().get(0).getStoreId());
                  if(Config.categoryResponseModel.getData().get(i).getStoreSubCategories().size() != 0){
                      storeSubCategory = Config.categoryResponseModel.getData().get(i).getStoreSubCategories();
                  }

                   storecategoryId = String.valueOf(Config.categoryResponseModel.getData().get(i).getStore().get(0).getCategoryId());

               }else {
                    Toast.makeText(activity, "No Product Found in This Category", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            }else {

            }
        }
        toolbarName.setText(intentName);
        categoryRecyclerViewData();
        ProductListApi();
    }

    private void bindListner() {

        back.setOnClickListener(this);
        filterbtn.setOnClickListener(this);
        Cancel.setOnClickListener(this);
        spin.setOnItemSelectedListener(this);
       // spinCategory.setOnItemSelectedListener(this);
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

        /*subCategoryRecyclerViewData();
        ProductListApi();
        seekBarSet();
        adapterSetToSpinner();
        rv_setSizeofProductData();
        rv_SetData();
        adapterSetToSpinnerforCategory();
        adapterSetToSpinnerfordiscount();
*/    }


    /* -----------------------------------------------child data set into recyclerView-------------------------------------------*/

    private void categoryRecyclerViewData() {
        catRecyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        categoryAdapter = new CategoryAdapter(activity, HomeFragment.ad_interface, storeSubCategory);
        catRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
    }

    /*------------------------------------------------ Sub Child CustomerRegistrationDataModel into recyclerView-------------------------------------*/

    /* ----------------------------------------------------------- Api url of product List ----------------------------------------------------*/
    private void apiUrl() {
        endPoint = Config.Url.productlist +"/"+StoreId+"/"+storecategoryId+"/"+intentCategoryId +"/"+"-1";

        // endPoint = Config.Url.productlist;
    }


    /*----------------------------------------------------------- Product list Api ----------------------------------------------------------*/

    private void ProductListApi() {
        dialog.show();
        apiUrl();
        ApiCaller.productList(activity, endPoint, new FutureCallback<ProductListResponseModel>() {
            @Override
            public void onCompleted(Exception e, ProductListResponseModel result) {
              dialog.dismiss();
                if (e != null) {
                    return;
                }
                CategorySubCategoryDataSetToRv(result);
            }
        });

    }

    /* -------------------------------------------------------Api Data Set Into Recycler View-------------------------------------------------------*/

    private void CategorySubCategoryDataSetToRv(ProductListResponseModel result) {

        if (result.getStatus()) {
            dialog.dismiss();
            if (result.getData().isEmpty()) {
                tvDataNotFound.setVisibility(View.VISIBLE);
                itemsRecyclerView.setVisibility(View.GONE);
            } else {
                tvDataNotFound.setVisibility(View.GONE);
                itemsRecyclerView.setVisibility(View.VISIBLE);
                itemsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                itemCategoryAdapter = new ItemCategoryAdapter(activity, result.getData());
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
        Category = new ArrayList<>();
        subCategory = new ArrayList<String>();
        Category.add("Select Category");
        subCategory.add("Select Sub-Category");

        for (int i = 0; i <Config.categoryResponseModel.getData().size();i++){
            Category.add(Config.categoryResponseModel.getData().get(i).getName());
        }
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Category);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_cat_id.setAdapter(aa);
        spinner_cat_id.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
               /* subCategory = new ArrayList<String>();
                subCategory.add("Select Sub-Category");*/
               int CategoryId =0;
                String categoryName = spinner_cat_id.getSelectedItem().toString();
                for (int i = 0; i <Config.categoryResponseModel.getData().size();i++){
                   if(categoryName.equals( Config.categoryResponseModel.getData().get(i).getName())){
                     CategoryId =   Config.categoryResponseModel.getData().get(i).getStoreCategoryId();
                     break;
                   }
                }

                for (int i = 0; i <Config.categoryResponseModel.getData().size();i++){
                    if(CategoryId == Config.categoryResponseModel.getData().get(i).getStoreCategoryId()){
                        if(Config.categoryResponseModel.getData().get(i).getStoreSubCategories().size()!=0) {
                            for (int j = 0; j < Config.categoryResponseModel.getData().get(i).getStoreSubCategories().size(); j++) {

                                subCategory.add(Config.categoryResponseModel.getData().get(i).getStoreSubCategories().get(j).getName());
                            }
                        }else {
                            subCategory = new ArrayList<>();
                            subCategory.add("Select Sub-Category");
                        }
                       break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        adapterSetToSpinnerforSubCategory();
    }
    /*--------------------------------------------------- discount--------------------------------------------------------------*/

    private void adapterSetToSpinnerforSubCategory() {
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, subCategory);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnersub_category_id.setAdapter(aa);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
          Toast.makeText(getApplicationContext(),country[position] , Toast.LENGTH_LONG).show();
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
