package com.io.choozo.activity.homeActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.io.choozo.ApiCaller;
import com.io.choozo.Config;
import com.io.choozo.Fragment.ProductView.OverView;
import com.io.choozo.Fragment.ProductView.Review;
import com.io.choozo.R;
import com.io.choozo.SqlDB.DbHelper;
import com.io.choozo.UrlLocator;
import com.io.choozo.adapter.ChooseColorAdapter;
import com.io.choozo.adapter.SelectSizeAdapter;
import com.io.choozo.adapter.fragmentadapter.CheckoutAdapter;
import com.io.choozo.model.dummydataModel.ChooseColorModel;
import com.io.choozo.model.dummydataModel.SelectSizeDataMode;
import com.io.choozo.model.responseModel.GetProductDataResponseModel;
import com.koushikdutta.async.future.FutureCallback;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    SliderLayout sliderLayout;
    String strImage,strImagePath,endPointImageResize,strImageUrl;
    String endPointProductDetail;
    String toolbarName;
    String spinnerData;
    String ProductId = "" , Id;
    int productImage ,PID;
    Activity activity;
    ImageView back,Like,Unlike;
    RecyclerView rv_color,rv_producrtsize;
    ChooseColorAdapter adapter;
    SelectSizeAdapter selectSizeAdapter;
    List<SelectSizeDataMode> itemslectsize = new ArrayList<>();
    List<ChooseColorModel> item = new ArrayList<>();
    String[] items = {"0","1", "2", "3", "4", "5","6","7","8","9","10"
                        ,"11", "12", "13", "14", "15","16","17","18","19","20",
                            "21", "22", "23", "24", "25","26","27","28","29","30",
                                "31", "32", "33", "34", "35","36","37","38","39","40",
                                  "41", "42", "43", "44", "45","46","47","48","49","50",
                                      "51", "52", "53", "54", "55","56","57","58","59","60",
                                         "61", "62", "63", "64", "65","66","67","68","69","70",
                                           "71", "72", "73", "74", "75","76","77","78","79","80",
                                             "81", "82", "83", "84", "85","86","87","88","89","90",
                                                 "91", "92", "93", "94", "95","96","97","98","99","100"};
    Spinner spin;
    Button addToCart;
    TextView cartCount,tvProductName,tvActualPrice,tvCutPrice;
    String strActualPrice , strCutPrice , strProductName;
    RelativeLayout rlCut;
    ViewPager viewPager;
    CheckoutAdapter checkoutAdapter;
    TabLayout tabLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;

    DbHelper dbHelper;
    TextView tvCount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initializeViews();
        toolbar();
        bindListner();
        startWorking();
    }

/* ------------------------------------------------ intialize all Views that are used in this activity ---------------------------------*/

    private void initializeViews() {
        activity = CartActivity.this;
        dbHelper = new DbHelper(activity);
        sliderLayout = findViewById(R.id.slider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setAutoScrolling(false);
        back = (ImageView)findViewById(R.id.back);
        Like = (ImageView)findViewById(R.id.like);
        Unlike = (ImageView)findViewById(R.id.unlike);
        rv_color = (RecyclerView)findViewById(R.id.rv_choosecolor);
        rv_producrtsize = (RecyclerView)findViewById(R.id.rv_productsize);
        tvProductName = (TextView)findViewById(R.id.tv_product_name);
        tvActualPrice = (TextView)findViewById(R.id.tv_mrp);
        tvCutPrice = (TextView)findViewById(R.id.tv_cutprice);
        rlCut = (RelativeLayout)findViewById(R.id.rl_pricecut);
        spin = (Spinner) findViewById(R.id.spinner);
        addToCart = (Button)findViewById(R.id.addtocart);
        cartCount = (TextView)findViewById(R.id.tv_cartcount);
        checkoutAdapter = new CheckoutAdapter(getSupportFragmentManager());
        viewPager =(ViewPager)findViewById(R.id.viewPager);
        tabLayout = (TabLayout)findViewById(R.id.tab);
        setUpFragment(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        getDataFromIntent();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        PID = intent.getIntExtra("productId",0);
        Log.e("id",""+PID);
        toolbarName = intent.getStringExtra("toolbarName");


    }

  /* ------------------------------------------------------set up of toolbar-------------------------------------------------------*/

    private void toolbar() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(toolbarName);
        setSupportActionBar(toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.transparent)); // transperent color = #00000000
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.rgb(0, 0, 0)); //Color of your title
        setFont();
    }

    /*---------------------------------------------- font style apply on toolbar text-------------------------------------------------*/
    private void setFont() {

        final Typeface tf = Typeface.createFromAsset(activity.getAssets(), "fonts/seguisb.ttf");
        collapsingToolbarLayout.setCollapsedTitleTypeface(tf);
        collapsingToolbarLayout.setExpandedTitleTypeface(tf);
    }

    /* ----------------------------------------------bind all views that are used in this activity-------------------------------------*/

    private void bindListner() {
      back.setOnClickListener(this);
      spin.setOnItemSelectedListener(this);
      spinnerAdapterSet();
      addToCart.setOnClickListener(this);
    }

    /*------------------------------------------------------------- Click Listner ----------------------------------------------------------*/

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back :
               /* onBackPressed();*/
                getSqliteData1();
                return;
            case R.id.unlike :
                Unlike.setVisibility(View.GONE);
                Like.setVisibility(View.VISIBLE);
                return;
            case R.id.like :
                Unlike.setVisibility(View.VISIBLE);
                Like.setVisibility(View.GONE);
                return;
            case R.id.addtocart :
                addToCartData();
        }

    }

  /*----------------------------------------------------------- start Working---------------------------------------------------------*/
    private void startWorking() {
       productdetailApi();
       rv_SetData(); // for color chooser
       rv_setSizeofProductData();
       getSqliteData();
    }

    /*------------------------------------------------------------ Api end points -------------------------------------------------- */

    private void apiurl(){
        endPointProductDetail = Config.Url.productdetail+PID;
        endPointImageResize = Config.Url.imageResize;
    }

    /* ---------------------------------------------------------api product detail--------------------------------------------------*/

    private void productdetailApi(){

        apiurl();
        ApiCaller.getproductDetail(activity,
                endPointProductDetail, new FutureCallback<GetProductDataResponseModel>() {
            @Override
            public void onCompleted(Exception e, GetProductDataResponseModel result) {
                if(e!=null){
                    return;
                }
              apiResponseData(result);

            }
        });
    }

    /* ------------------------------------------------------------api data set----------------------------------------------------------*/
    private void apiResponseData(GetProductDataResponseModel result) {

        if(result.getStatus() == 1){
            strProductName = result.getData().get(0).getName();
            tvProductName.setText(strProductName);
            strActualPrice = result.getData().get(0).getPrice();
            strCutPrice = result.getData().get(0).getPricerefer();
            if(strCutPrice.equals("")){
                rlCut.setVisibility(View.GONE);
                tvActualPrice.setText(strActualPrice);
            }else {
                rlCut.setVisibility(View.VISIBLE);
                tvActualPrice.setText(strCutPrice);
                tvCutPrice.setText(strActualPrice);
            }
              for (int i=0 ; i<result.getData().get(0).getProductImage().size();i++){
                  strImage = result.getData().get(0).getProductImage().get(i).getImage();
                  strImagePath = result.getData().get(0).getProductImage().get(i).getContainerName();
                  strImageUrl = UrlLocator.getFinalUrl(endPointImageResize +"width=3840&height=2160&name="+strImage+"&path="+strImagePath+"");
                  SliderView sliderView = new DefaultSliderView(activity);
                  sliderView.setImageUrl(strImageUrl);
                  sliderView.setImageScaleType(ImageView.ScaleType.FIT_CENTER);
                  final int finalI = i;
                  sliderLayout.addSliderView(sliderView);
              }
        }else {
            Toast.makeText(activity, "Something wentWrong", Toast.LENGTH_SHORT).show();
        }
    }
    /* --------------------------------------------------------color recycler view-------------------------------------------------*/
    private void rv_SetData() {
        rv_color.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        item.clear();
        item.add(new ChooseColorModel(R.color.skyblue));
        item.add(new ChooseColorModel(R.color.green));
        item.add(new ChooseColorModel(R.color.red));
        item.add(new ChooseColorModel(R.color.lighred));
        item.add(new ChooseColorModel(R.color.black));
        item.add(new ChooseColorModel(R.color.colorAccent));
        item.add(new ChooseColorModel(R.color.grey));

        adapter = new ChooseColorAdapter(activity, item);
        rv_color.setAdapter(adapter);
    }

    /* ---------------------------------------------------------------size recycler view-------------------------------------------*/

    private void rv_setSizeofProductData() {
        rv_producrtsize.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        itemslectsize.clear();
        itemslectsize.add(new SelectSizeDataMode("S"));
        itemslectsize.add(new SelectSizeDataMode("M"));
        itemslectsize.add(new SelectSizeDataMode("L"));
        itemslectsize.add(new SelectSizeDataMode("XL"));
        itemslectsize.add(new SelectSizeDataMode("XXL"));
        itemslectsize.add(new SelectSizeDataMode("XXXL"));
        selectSizeAdapter = new SelectSizeAdapter(activity,itemslectsize);
        rv_producrtsize.setAdapter(selectSizeAdapter);

    }

    /* ----------------------------------------------------Spinner work-------------------------------------------------------------*/

    private void spinnerAdapterSet() {
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,items);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerData = items[position];
        Config.CartCount = items[position];
        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /* ---------------------------------------------------Add to cart work---------------------------------------------------------*/
    private void addToCartData() {
        if(spinnerData.equals("0")){

        }else{
            if(ProductId.equals("") ){
                    boolean isInserted = dbHelper.insertData(strProductName,strImageUrl,spinnerData,strActualPrice,String.valueOf(PID));
                    if (isInserted == true) {
                        Toast.makeText(activity, "Data Inserted", Toast.LENGTH_SHORT).show();
                        getSqliteData();
                    } else {
                        Toast.makeText(activity, "DATA NOT SUPPORTED", Toast.LENGTH_SHORT).show();
                    }
            }else
            {
                boolean isupdated=dbHelper.updateData(strProductName,strImageUrl,spinnerData,strActualPrice,String.valueOf(PID));
                if(isupdated==true)
                {
                    Toast.makeText(activity,"Data updated", Toast.LENGTH_SHORT).show();
                    getSqliteData();
                }else
                {
                    Toast.makeText(activity,"data is not Updated", Toast.LENGTH_SHORT).show();
                }
            }

        }

    }
   /* ------------------------------------------------- get particular data from sqlite ------------------------------------------------*/
    private void getSqliteData() {

        Cursor cursor = dbHelper.getDataq(String.valueOf(PID));
        if(cursor.getCount() == 0){
            Log.e("Error","no Data");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            buffer.append("Id:"+cursor.getString(0)+"\n");
            Id = cursor.getString(0);
            buffer.append("Name:"+cursor.getString(1)+"\n");
            buffer.append("Image:"+cursor.getString(2)+"\n");
            buffer.append("Quantity:"+cursor.getString(3)+"\n");
            buffer.append("Price:"+cursor.getString(4)+"\n");
            buffer.append("P_ID:"+cursor.getString(5)+"\n");
            ProductId = cursor.getString(5);
            Log.e("ProductId",ProductId);
        }
        Log.e("getDatasingle",buffer.toString());
    }

    /* ----------------------------------- get all table data from sqlite  and convert into json formet ----------------------------------*/

    private void getSqliteData1() {

        Cursor cursor = dbHelper.getData();
        if(cursor.getCount() == 0){
            Log.e("Error","no Data");
            return;
        }
        JSONArray resultSet = new JSONArray();
        JSONObject returnObj = new JSONObject();

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {

            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for (int i = 0; i < totalColumn; i++) {
                if (cursor.getColumnName(i) != null) {
                    try {
                        if (cursor.getString(i) != null) {
                            Log.d("TAG_NAME2", cursor.getString(i));
                            rowObject.put(cursor.getColumnName(i), cursor.getString(i));
                        } else {
                            rowObject.put(cursor.getColumnName(i), "");
                        }
                    } catch (Exception e) {
                        Log.d("TAG_NAME1", e.getMessage());
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }
        cursor.close();
        String datajson = resultSet.toString();
        datajson.replaceAll("\\\\", "");
        Log.d("datajson",datajson);
        Config.CartData = datajson;
    }


    /* -------------------------------------------------Set up of Fragments---------------------------------------------------------*/

    private void setUpFragment(ViewPager viewPager) {
        CheckoutAdapter checkoutAdapter= new CheckoutAdapter(getSupportFragmentManager());
        checkoutAdapter.addFragment(new OverView(),"Product Review");
        checkoutAdapter.addFragment(new Review(),"Product Review");
        viewPager.setAdapter(checkoutAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart, menu);
        MenuItem item = menu.findItem(R.id.badge);
        MenuItem item1 = menu.findItem(R.id.action_drawer_search);
        MenuItemCompat.setActionView(item, R.layout.carticon);
        MenuItemCompat.setActionView(item1, R.layout.cartlike);
        RelativeLayout notifCount = (RelativeLayout)   MenuItemCompat.getActionView(item);
        tvCount = (TextView) notifCount.findViewById(R.id.tv_cartcount);
        notifCount.setVisibility(View.VISIBLE);
        tvCount.setText(spinnerData);
         return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ProductId = "";
    }
}
