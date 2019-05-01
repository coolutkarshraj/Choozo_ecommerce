package com.io.choozo.custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fifo.R;
import com.fifo.helpers.ApiResponse;
import com.fifo.helpers.CheckConnection;
import com.fifo.helpers.K;
import com.fifo.helpers.OnCitySelected;
import com.fifo.helpers.network.HitApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ayushsingla on 25/02/17.
 */

public class CitySelectorDialog extends Dialog implements View.OnClickListener {
    OnCitySelected citySelected;

    public CitySelectorDialog(Context context, OnCitySelected citySelected) {
        super(context);
        this.citySelected = citySelected;
    }

    NormalEditText etCitySearch;
    NormalTextView tvLocation;
    RecyclerView rvCities;
    MyAdapter mAdapter;
    LinearLayoutManager mLayoutManager;

    CheckConnection chk;
    ProgressDialog pDialog;
    String currentCity;
    ArrayList<String> cities = new ArrayList<>();
    ArrayList<String> searchedCities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_city_selector);

        initialise();
        setValues();
        setListeners();

    }

    public void initialise() {
        chk = new CheckConnection(getContext());
        pDialog = new ProgressDialog(getContext());
        etCitySearch = (NormalEditText) findViewById(R.id.etCitySearch);
        tvLocation = (NormalTextView) findViewById(R.id.tvLocation);
        rvCities = (RecyclerView) findViewById(R.id.rvCities);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new MyAdapter();
    }

    public void setValues() {
        rvCities.setLayoutManager(mLayoutManager);
        rvCities.setHasFixedSize(true);
        rvCities.setAdapter(mAdapter);
        fetchCities();
    }

    public void setListeners() {
        etCitySearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchedCities.clear();

                String query = etCitySearch.getText().toString().trim();
                if (query.equals("")) {
                    searchedCities.addAll(cities);
                    mAdapter.notifyDataSetChanged();
                    return;
                }

                for (String city : cities) {
                    if (city.toLowerCase().contains(query.toLowerCase())) {
                        searchedCities.add(city);
                    }
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tvLocation.setOnClickListener(this);
    }

    public void fetchCities() {
        if (!chk.isConnected()) {
            Toast.makeText(getContext(), K.Errors.noInternet, Toast.LENGTH_LONG).show();
            return;
        }
        HashMap<String, String> params = new HashMap<>();
        params.put("type", K.Api.Type.cities);
        new HitApi(getContext(), K.Api.base, params, "post", new ApiResponse<String>() {
            @Override
            public void startRequesting() {
                pDialog.show();
            }

            @Override
            public void onReceiveResponse(String result) {
                pDialog.cancel();
                try {
                    cities.clear();
                    JSONObject jObj = new JSONObject(result);
                    currentCity = jObj.getString("currentCity");
                    JSONArray list = jObj.getJSONArray("list");
                    for (int i = 0; i < list.length(); i++) {
                        cities.add(list.getString(i));
                        searchedCities.add(list.getString(i));
                    }
                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).execute();
    }

    @Override
    public void onClick(View v) {
        if (v == tvLocation) {

        }
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvCity;

            public ViewHolder(View v) {
                super(v);
                tvCity = (TextView) v.findViewById(android.R.id.text1);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder vh, final int position) {
            vh.tvCity.setText(searchedCities.get(position));
            vh.tvCity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    citySelected.citySelected(searchedCities.get(position));
                    cancel();
                }
            });
        }

        @Override
        public int getItemCount() {
            return searchedCities.size();
        }
    }

}
