package com.io.choozo.adapter.profileadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.io.choozo.R;
import com.io.choozo.model.dummydataModel.MyOrderModel;

import java.util.List;

public class MyOrderRVAdapter extends RecyclerView.Adapter<MyOrderRVAdapter.ViewHolder> {

    Context context;
    List<MyOrderModel> item;
    int color;

    public MyOrderRVAdapter(Context context, List<MyOrderModel> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public MyOrderRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.myorder_card_design,viewGroup,false);
    return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderRVAdapter.ViewHolder viewHolder, int i) {

        MyOrderModel model = item.get(i);
        viewHolder.date.setText(model.getDate());
        viewHolder.orderId.setText(model.getOrderid());
        color = model.getColor();
        viewHolder.orderStatus.setText(model.getOrderstatus());
        viewHolder.orderStatus.setTextColor(ContextCompat.getColor(context,color));

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView date,orderId,orderStatus;
        ImageView imageView;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = (TextView)itemView.findViewById(R.id.tv_date);
            orderId = (TextView)itemView.findViewById(R.id.order_id);
            orderStatus = (TextView)itemView.findViewById(R.id.tv_orderstatus);



        }
    }
}
