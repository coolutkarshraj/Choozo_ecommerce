package com.io.choozo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.io.choozo.Config;
import com.io.choozo.R;
import com.io.choozo.UrlLocator;
import com.io.choozo.activity.homeActivity.CategorySubCategory;
import com.io.choozo.model.dataModel.CategoryDataModel;
import com.io.choozo.model.dummydataModel.ShoppingBagModel;
import com.io.choozo.util.CategorySubCatChildCat;

import java.util.List;

public class ShopingCategoryAdapter extends RecyclerView.Adapter<ShopingCategoryAdapter.ViewHolder> {

    Context context;
    List<CategoryDataModel> item;
    CategorySubCatChildCat ad_interface;
    public static Integer cateId;
    String image ,imagePath,endPoint;
    public ShopingCategoryAdapter(Context context, CategorySubCatChildCat ad_interface, List<CategoryDataModel> item) {
        this.context = context;
        this.item = item;
        this.ad_interface = ad_interface;
    }

    @NonNull
    @Override
    public ShopingCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shoping_category_cardview,viewGroup,false);
    return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopingCategoryAdapter.ViewHolder viewHolder, int i) {

        final CategoryDataModel model = item.get(i);
        viewHolder.name.setText(model.getName());
        image = model.getImage();
        imagePath = model.getImagePath();
        imageResizeApi(image,imagePath);
        Glide.with(context).load(UrlLocator.getFinalUrl(endPoint)).into(viewHolder.imageView);
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context, ""+model.getCategoryId(), Toast.LENGTH_SHORT).show();

                Intent i =new Intent(context, CategorySubCategory.class);
                i.putExtra("name",model.getName());
                i.putExtra("categoryId",model.getCategoryId());
                context.startActivity(i);
                cateId = model.getCategoryId();
                ad_interface.catId(cateId);
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView imageView;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.tv_name);
            imageView = (ImageView)itemView.findViewById(R.id.imageview);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.rl_click);

        }
    }

    /*------------------------------------------------------ image Resize api path----------------------------------------------*/

    private void imageResizeApi(String image, String imagePath) {
        endPoint = Config.Url.imageResize +"width=200&height=250&name="+image+"&path="+imagePath+"";
    }



}
