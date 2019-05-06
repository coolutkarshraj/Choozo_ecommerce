package com.io.choozo.activity.homeActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.io.choozo.Config;
import com.io.choozo.R;
import com.io.choozo.adapter.ChooseColorAdapter;
import com.io.choozo.adapter.SelectSizeAdapter;
import com.io.choozo.adapter.SubCategoryAdapter;
import com.io.choozo.model.dummydataModel.ChooseColorModel;
import com.io.choozo.model.dummydataModel.SelectSizeDataMode;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class ProductAddCartActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    SliderLayout sliderLayout;
    String productName;
    String productMRP;
    String productPriceCut;
    String spinnerData;
    int productImage;
    Activity activity;
    ImageView back,Like,Unlike;
    RecyclerView rv_color,rv_producrtsize;
    ChooseColorAdapter adapter;
    SelectSizeAdapter selectSizeAdapter;
    List<SelectSizeDataMode> itemslectsize = new ArrayList<>();
    List<ChooseColorModel> item = new ArrayList<>();
    String[] items = {"0","1", "2", "3", "4", "5","6","7","8","9","10"};
    Spinner spin;
    Button addToCart;
    TextView cartCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add_cart);
        initializeViews();
        bindListner();
        startWorking();
    }

    private void initializeViews() {
        activity = ProductAddCartActivity.this;
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Mens Fashion");
        setSupportActionBar(toolbar);
        sliderLayout = findViewById(R.id.slider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setAutoScrolling(false);
        back = (ImageView)findViewById(R.id.back);
        Like = (ImageView)findViewById(R.id.like);
        Unlike = (ImageView)findViewById(R.id.unlike);
        rv_color = (RecyclerView)findViewById(R.id.rv_choosecolor);
        rv_producrtsize = (RecyclerView)findViewById(R.id.rv_productsize);
        spin = (Spinner) findViewById(R.id.spinner);
        addToCart = (Button)findViewById(R.id.addtocart);
        cartCount = (TextView)findViewById(R.id.tv_cartcount);

    }

    private void bindListner() {
        back.setOnClickListener(this);
        Unlike.setOnClickListener(this);
        Like.setOnClickListener(this);
        spin.setOnItemSelectedListener(this);
        spinnerAdapterSet();
        addToCart.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back :
                onBackPressed();
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


    private void startWorking() {
        getDataFromIntent();
        setSliderViews();
        rv_SetData(); // for color chooser
        rv_setSizeofProductData();
    }


    private void getDataFromIntent() {
        Intent intent = getIntent();
        productName = intent.getStringExtra("productName");
        productImage = intent.getIntExtra("productImage",0);
        productMRP = intent.getStringExtra("productMRP");
        productPriceCut = intent.getStringExtra("productpricecut");
    }

    private void setSliderViews() {

        for (int i = 0; i <= 2; i++) {

            SliderView sliderView = new DefaultSliderView(this);

            switch (i) {
                case 0:
                    sliderView.setImageDrawable(productImage);
                    break;
                case 1:
                    sliderView.setImageDrawable(productImage);
                    break;
                case 2:
                    sliderView.setImageDrawable(productImage);
                    break;

            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {

                }
            });
            sliderLayout.addSliderView(sliderView);
        }
    }

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

    /* ----------------------------------------------Spinner work----------------------------------------------------*/

    private void spinnerAdapterSet() {
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,items);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerData = items[position];
        Config.CartCount = items[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /* ---------------------------------------------------Add to cart work-------------------------------------*/
    private void addToCartData() {
        if(spinnerData.equals("0")){
            cartCount.setVisibility(View.GONE);
        }else{
            cartCount.setVisibility(View.VISIBLE);
            cartCount.setText(spinnerData);
        }

    }


}
