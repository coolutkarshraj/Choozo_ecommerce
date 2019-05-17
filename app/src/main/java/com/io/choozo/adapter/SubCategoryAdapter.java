package com.io.choozo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.io.choozo.R;
import com.io.choozo.model.dataModel.ChildDataModel;
import com.io.choozo.model.dataModel.SubChildDataModel;

import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {

    Context context;
    List<SubChildDataModel> item;
    int i;
    public static int recyclerViewClickPostion;

    public SubCategoryAdapter(Context context, List<SubChildDataModel> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public SubCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.subcategory_design,viewGroup,false);
    return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryAdapter.ViewHolder viewHolder, final int i) {

        SubChildDataModel model = item.get(i);
        viewHolder.name.setText(model.getName());
        viewHolder. linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewClickPostion = i;
                notifyDataSetChanged();
            }
        });
        if (recyclerViewClickPostion == i) {
            viewHolder.name.setTextColor(Color.parseColor("#ff0000"));

        } else {
            viewHolder. name.setTextColor(Color.parseColor("#000000"));

        }


    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView line;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.tv_subcate);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.subcategorylayout);
            i= getAdapterPosition();


        }
    }
}
