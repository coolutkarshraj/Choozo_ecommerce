package com.io.choozo.Fragment.hotOffer;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.io.choozo.R;
import com.io.choozo.adapter.HotOfferRvAdapter;
import com.io.choozo.model.dummydataModel.OfferDataModel;

import java.util.ArrayList;
import java.util.List;

public class HotOfferFragment extends Fragment {

    RecyclerView rvHotOffer;
    HotOfferRvAdapter adapter;
    List<OfferDataModel> item = new ArrayList<>();
    Activity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hot_offer,container,false);
        intializeViews(view);
        return  view;
    }

    private void intializeViews(View view) {
        activity = getActivity();
        rvHotOffer = (RecyclerView)view.findViewById(R.id.rv_offer);
        dataSetIntoRv();
    }

    private void dataSetIntoRv() {
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(activity);
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setJustifyContent(JustifyContent.SPACE_AROUND);
      //  flexboxLayoutManager.setAlignItems(AlignItems.STRETCH);
        rvHotOffer.setLayoutManager(flexboxLayoutManager);
        item.clear();
        item.add(new OfferDataModel(R.drawable.shop2));
        item.add(new OfferDataModel(R.drawable.offer2));
        item.add(new OfferDataModel(R.drawable.offer3));
        item.add(new OfferDataModel(R.drawable.offer4));
        item.add(new OfferDataModel(R.drawable.offer5));
        item.add(new OfferDataModel(R.drawable.offer6));
        adapter = new HotOfferRvAdapter(activity,item);
        rvHotOffer.setAdapter(adapter);
    }
}
