package com.io.choozo.Fragment.ProductView;

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
import com.io.choozo.adapter.ProductItemAdapter.ReviewAdapter;
import com.io.choozo.model.dummydataModel.ReviewModel;

import java.util.ArrayList;
import java.util.List;

public class Review extends Fragment {

    RecyclerView rvReview ;
    ReviewAdapter adapter ;
    List<ReviewModel> item = new ArrayList<>();

    public Review(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.review,container,false);
        intializeView(view);
        startWorking();
        return  view;
    }


    private void intializeView(View view) {

        rvReview = (RecyclerView)view.findViewById(R.id.rv_review);
    }


    private void startWorking() {

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rvReview.setLayoutManager(linearLayoutManager);
        item.clear();
        item.add(new ReviewModel("Aman Singh","2 May 2018"));
        item.add(new ReviewModel("kamalpreet Singh","21 Feb 2018"));
        item.add(new ReviewModel("Harmanjot Singh","28 May 2019"));
        adapter = new ReviewAdapter(getActivity(), item);
        rvReview.setAdapter(adapter);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }
}
