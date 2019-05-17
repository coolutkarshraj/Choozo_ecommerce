package com.io.choozo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.io.choozo.R;
import com.io.choozo.model.dataModel.CategoryDataModel;
import com.io.choozo.model.dataModel.ChildDataModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    Context context;
    List<ChildDataModel> item;
    public static  int recyclerviewPostion;

    public CategoryAdapter(Context context, List<ChildDataModel> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_design,viewGroup,false);
    return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder viewHolder, final int i) {

        ChildDataModel model = item.get(i);
        viewHolder.name.setText(model.getName());

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                recyclerviewPostion = i;
                notifyDataSetChanged();

            }
        });

        if (recyclerviewPostion == i) {
            viewHolder.name.setTextColor(Color.parseColor("#ff0000"));
            viewHolder.line.setTextColor(Color.parseColor("#ff0000"));
            viewHolder.line.setVisibility(View.VISIBLE);
        } else {
            viewHolder. name.setTextColor(Color.parseColor("#000000"));
            viewHolder.line.setVisibility(View.GONE);
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
            name = (TextView)itemView.findViewById(R.id.tv_name);
            line = (TextView)itemView.findViewById(R.id.tv_line);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.categorylayout);

        }
    }
}
