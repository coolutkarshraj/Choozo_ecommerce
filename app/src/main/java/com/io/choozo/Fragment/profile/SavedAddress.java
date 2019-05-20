package com.io.choozo.Fragment.profile;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.io.choozo.R;
import com.io.choozo.adapter.profileadapter.SavedAdressRvAdapter;
import com.io.choozo.model.dummydataModel.SavedAdressDataModel;

import java.util.ArrayList;
import java.util.List;

public class SavedAddress  extends Fragment {

    RecyclerView rvSavedAdress;
    SavedAdressRvAdapter adapter;
    List<SavedAdressDataModel> item =new ArrayList<>();
    Activity activity;

    public SavedAddress(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.saved_address,container,false);
        intializeViews(v);
        return v;
    }

    private void intializeViews(View v) {
        rvSavedAdress = (RecyclerView)v.findViewById(R.id.rv_saved_address);
        dataSetToRecyclerView();
    }

    private void dataSetToRecyclerView() {
        rvSavedAdress.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));
        item.clear();
        item.add(new SavedAdressDataModel("John smith","701, Block - B, Siddhi Vinayak Tower,Ahmedabad-380051, Gujarat, INDIA +91 98765 43210"));
        item.add(new SavedAdressDataModel("Vernon Martin","925 Buddy Motorway, New Street, USA 380152 +91 43210 98765 "));
        item.add(new SavedAdressDataModel("Ian Grant","4855 Durgan Wall, Perfect Arcade, USA 380152 +91 98765 43210"));
        adapter = new SavedAdressRvAdapter(activity,item);
        rvSavedAdress.setAdapter(adapter);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }
}
