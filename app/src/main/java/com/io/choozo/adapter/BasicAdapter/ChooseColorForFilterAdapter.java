package com.io.choozo.adapter.BasicAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.io.choozo.R;
import com.io.choozo.model.dummydataModel.ChooseColorModel;

import java.util.List;

public class ChooseColorForFilterAdapter extends RecyclerView.Adapter<ChooseColorForFilterAdapter.ViewHolder> {

    Context context;
    List<ChooseColorModel> item;

    public ChooseColorForFilterAdapter(Context context, List<ChooseColorModel> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public ChooseColorForFilterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.choose_color_for_product_cardviewfor_filter,viewGroup,false);
    return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseColorForFilterAdapter.ViewHolder viewHolder, int i) {

        ChooseColorModel model = item.get(i);
       viewHolder.colorImage.setBackgroundResource(model.getColor());
        //viewHolder.colorImage.setBackgroundTintList(model.getColor());


    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout colorImage;
        LinearLayout linearLayout;
        @SuppressLint("WrongViewCast")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_color);

            colorImage = (LinearLayout) itemView.findViewById(R.id.ll_choosecolor);

    }
}}
