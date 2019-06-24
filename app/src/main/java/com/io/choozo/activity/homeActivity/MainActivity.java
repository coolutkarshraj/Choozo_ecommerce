package com.io.choozo.activity.homeActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.io.choozo.Fragment.Home.HomeFragment;
import com.io.choozo.Fragment.MyCart.MyCartFragment;
import com.io.choozo.Fragment.Search.Search;
import com.io.choozo.Fragment.hotOffer.HotOfferFragment;
import com.io.choozo.Fragment.profile.ProfileFragment;
import com.io.choozo.R;
import com.io.choozo.activity.About.AboutUsActivity;
import com.io.choozo.activity.About.ContactUsActivity;
import com.io.choozo.activity.loginRegistrationflow.LoginActivity;
import com.io.choozo.localStorage.PreferenceManager;
import com.io.choozo.model.responseModel.LoginResponseModel;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener {
    ImageView iv_menu_icon;
    DrawerLayout drawer;
    Dialog dialog;
    Activity activity;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    LinearLayout ll_lome,ll_hotoffer,ll_my_cart,ll_search,ll_profile;
    Fragment currFrag;
    HomeFragment homeFragment;
    HotOfferFragment hotOfferFragment;
    MyCartFragment myCartFragment;
    ProfileFragment profileFragment;
    Search searchFragment;
    String localName,localEmail;
    private PreferenceManager preferenceManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        bindListner();
        navigationHeader();
    }

    /* ------------------------------------intialize all views that are used in this activity--------------------------------*/

    private void initView() {
        activity = MainActivity.this;
        preferenceManager = new PreferenceManager(this);
        iv_menu_icon =  findViewById(R.id.iv_menu_icon);
        ll_lome =  findViewById(R.id.ll_lome);
        ll_hotoffer =  findViewById(R.id.ll_hotoffer);
        ll_my_cart =  findViewById(R.id.ll_my_cart);
        ll_search =  findViewById(R.id.ll_search);
        ll_profile =  findViewById(R.id.ll_profile);
        iv_menu_icon =  findViewById(R.id.iv_menu_icon);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        tooglebar();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        homeFragment = new HomeFragment();
        changeFrag(homeFragment,false);
        hotOfferFragment = new HotOfferFragment();
        myCartFragment = new MyCartFragment();
        profileFragment = new ProfileFragment();
        searchFragment = new Search();

        getDataFromLocalStorage();

    }

    /*------------------------------------------- bind all views that are used in this activity-----------------------------*/

    private void bindListner() {
        iv_menu_icon.setOnClickListener(this);
        ll_lome.setOnClickListener(this);
        ll_hotoffer.setOnClickListener(this);
        ll_my_cart.setOnClickListener(this);
        ll_search.setOnClickListener(this);
        ll_profile.setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(this);

    }

    /* ---------------------------------------------------Navigation drawer open and close----------------------------------*/

    private void tooglebar() {
        toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_menu_icon :
                drawer.openDrawer(GravityCompat.START);
                return;

            case R.id.ll_lome :
                ll_lome.setBackgroundColor(Color.parseColor("#ff0000"));
                ll_hotoffer.setBackgroundColor(Color.parseColor("#333333"));
                ll_my_cart.setBackgroundColor(Color.parseColor("#333333"));
                ll_search.setBackgroundColor(Color.parseColor("#333333"));
                ll_profile.setBackgroundColor(Color.parseColor("#333333"));
                changeFrag(homeFragment,false);
                return;

            case R.id.ll_hotoffer :
                ll_lome.setBackgroundColor(Color.parseColor("#333333"));
                ll_hotoffer.setBackgroundColor(Color.parseColor("#ff0000"));
                ll_my_cart.setBackgroundColor(Color.parseColor("#333333"));
                ll_search.setBackgroundColor(Color.parseColor("#333333"));
                ll_profile.setBackgroundColor(Color.parseColor("#333333"));
                changeFrag(hotOfferFragment,false);
                return;

            case R.id.ll_my_cart :
                ll_lome.setBackgroundColor(Color.parseColor("#333333"));
                ll_hotoffer.setBackgroundColor(Color.parseColor("#333333"));
                ll_my_cart.setBackgroundColor(Color.parseColor("#ff0000"));
                ll_search.setBackgroundColor(Color.parseColor("#333333"));
                ll_profile.setBackgroundColor(Color.parseColor("#333333"));
                changeFrag(myCartFragment,false);
                return;

            case R.id.ll_search :
                ll_lome.setBackgroundColor(Color.parseColor("#333333"));
                ll_hotoffer.setBackgroundColor(Color.parseColor("#333333"));
                ll_my_cart.setBackgroundColor(Color.parseColor("#333333"));
                ll_search.setBackgroundColor(Color.parseColor("#ff0000"));
                ll_profile.setBackgroundColor(Color.parseColor("#333333"));
                changeFrag(searchFragment,false);
                return;

            case R.id.ll_profile:
                ll_lome.setBackgroundColor(Color.parseColor("#333333"));
                ll_hotoffer.setBackgroundColor(Color.parseColor("#333333"));
                ll_my_cart.setBackgroundColor(Color.parseColor("#333333"));
                ll_search.setBackgroundColor(Color.parseColor("#333333"));
                ll_profile.setBackgroundColor(Color.parseColor("#ff0000"));
                changeFrag(profileFragment,false);
                return;


        }

    }


    /* ---------------------------------------------login data get from local storgae---------------------------------------*/

    private void getDataFromLocalStorage() {

        Gson gson = new Gson();
        String getJson = preferenceManager.getString(PreferenceManager.loginData);
        LoginResponseModel obj = gson.fromJson(getJson, LoginResponseModel.class);
        localName = obj.getData().getUser().getFirstName();
        localEmail = obj.getData().getUser().getEmail();

    }

    /* -----------------------------------------Navigation header name and email set--------------------------------------*/

    private void navigationHeader() {
        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.tv_name);
        TextView nav_Email = (TextView)hView.findViewById(R.id.tv_email);
        nav_user.setText(localName);
        nav_Email.setText(localEmail);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.profile) {
            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.order_history) {
            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.payment_history) {
            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.track_order) {
            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.about) {
            Intent i = new Intent(activity, AboutUsActivity.class);
            startActivity(i);
        }else if (id == R.id.logout) {
            logoutDialog();
        }else if (id == R.id.nav_share) {
            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.contactus) {
            Intent i = new Intent(activity, ContactUsActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*---------------------------------------------------------- logout dialog-------------------------------------------------------*/

    public void logoutDialog() {
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        dialog.getWindow().setLayout((6 * width) / 7, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.logout_dialog);
        dialog.setTitle("");
        final TextView tvLogout = (TextView) dialog.findViewById(R.id.tv_logout);
        final TextView tvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        final ImageView Clear = (ImageView) dialog.findViewById(R.id.clear);

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
                dialog.dismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Clear.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    /* ------------------------------------------------------- Logout Action ---------------------------------------------------------*/
    private void logout() {
        preferenceManager.putString(PreferenceManager.loginData,"");
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    /* ----------------------------------------------- bottom navigation tabs work --------------------------------------------------*/

    private void changeFrag(Fragment fragment, boolean addToBack) {
        currFrag = fragment;
        FragmentTransaction m = getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_view, fragment);
        if (addToBack) {
            m.addToBackStack(null);
            m.setCustomAnimations(R.anim.fade_in,
                    R.anim.fade_out);
        }
        m.commit();
    }
}
