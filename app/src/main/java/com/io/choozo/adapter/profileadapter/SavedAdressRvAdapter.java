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
import com.io.choozo.model.dummydataModel.SavedAdressDataModel;

import java.util.List;

public class SavedAdressRvAdapter extends RecyclerView.Adapter<SavedAdressRvAdapter.ViewHolder> {

    Context context;
    List<SavedAdressDataModel> item;
    int color;

    public SavedAdressRvAdapter(Context context, List<SavedAdressDataModel> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public SavedAdressRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.saved_address_card_design,viewGroup,false);
    return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedAdressRvAdapter.ViewHolder viewHolder, int i) {

        SavedAdressDataModel model = item.get(i);
        viewHolder.name.setText(model.getName());
        viewHolder.address.setText(model.getAddress());

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,address,edit;
        ImageView imageView;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.tv_name);
            address = (TextView)itemView.findViewById(R.id.tv_address);
            edit = (TextView)itemView.findViewById(R.id.tv_edit);



        }
    }
}
