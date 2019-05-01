package com.io.choozo.activity.homeActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.io.choozo.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView iv_menu_icon;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    LinearLayout ll_lome,ll_hotoffer,ll_my_cart,ll_search,ll_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        bindListner();
    }

    private void bindListner() {
        iv_menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 drawer.openDrawer(GravityCompat.START);
            }
        });

        ll_lome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             ll_lome.setBackgroundColor(Color.parseColor("#00acec"));
             ll_hotoffer.setBackgroundColor(Color.parseColor("#333333"));
             ll_my_cart.setBackgroundColor(Color.parseColor("#333333"));
             ll_search.setBackgroundColor(Color.parseColor("#333333"));
             ll_profile.setBackgroundColor(Color.parseColor("#333333"));
            }
        });

        ll_hotoffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_lome.setBackgroundColor(Color.parseColor("#333333"));
                ll_hotoffer.setBackgroundColor(Color.parseColor("#00acec"));
                ll_my_cart.setBackgroundColor(Color.parseColor("#333333"));
                ll_search.setBackgroundColor(Color.parseColor("#333333"));
                ll_profile.setBackgroundColor(Color.parseColor("#333333"));
            }
        });

        ll_my_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_lome.setBackgroundColor(Color.parseColor("#333333"));
                ll_hotoffer.setBackgroundColor(Color.parseColor("#333333"));
                ll_my_cart.setBackgroundColor(Color.parseColor("#00acec"));
                ll_search.setBackgroundColor(Color.parseColor("#333333"));
                ll_profile.setBackgroundColor(Color.parseColor("#333333"));
            }
        });

        ll_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_lome.setBackgroundColor(Color.parseColor("#333333"));
                ll_hotoffer.setBackgroundColor(Color.parseColor("#333333"));
                ll_my_cart.setBackgroundColor(Color.parseColor("#333333"));
                ll_search.setBackgroundColor(Color.parseColor("#00acec"));
                ll_profile.setBackgroundColor(Color.parseColor("#333333"));
            }
        });

        ll_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_lome.setBackgroundColor(Color.parseColor("#333333"));
                ll_hotoffer.setBackgroundColor(Color.parseColor("#333333"));
                ll_my_cart.setBackgroundColor(Color.parseColor("#333333"));
                ll_search.setBackgroundColor(Color.parseColor("#333333"));
                ll_profile.setBackgroundColor(Color.parseColor("#00acec"));
            }
        });
    }

    private void initView() {
        iv_menu_icon =  findViewById(R.id.iv_menu_icon);
        ll_lome =  findViewById(R.id.ll_lome);
        ll_hotoffer =  findViewById(R.id.ll_hotoffer);
        ll_my_cart =  findViewById(R.id.ll_my_cart);
        ll_search =  findViewById(R.id.ll_search);
        ll_profile =  findViewById(R.id.ll_profile);
        iv_menu_icon =  findViewById(R.id.iv_menu_icon);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_slideshow) {
            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_manage) {
            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_share) {
            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_send) {
            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
