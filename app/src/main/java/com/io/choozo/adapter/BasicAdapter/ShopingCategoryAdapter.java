package com.io.choozo.adapter.BasicAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.io.choozo.Config;
import com.io.choozo.R;
import com.io.choozo.UrlLocator;
import com.io.choozo.activity.homeActivity.CategorySubCategory;
import com.io.choozo.model.dataModel.CategoryDataModel;
import com.io.choozo.model.responseModel.CategoryResponseModel;
import com.io.choozo.util.CategorySubCatChildCat;

import java.util.List;

public class ShopingCategoryAdapter extends RecyclerView.Adapter<ShopingCategoryAdapter.ViewHolder> {

    Context context;
    CategoryResponseModel item;
    CategorySubCatChildCat ad_interface;
    public static Integer cateId;
    String image ,imagePath,endPoint,strname;
    public ShopingCategoryAdapter(Context context, CategorySubCatChildCat ad_interface, CategoryResponseModel item) {
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
         viewHolder.name.setText(item.getData().get(i).getName());
         imagePath = item.getData().get(i).getAvatarPath();
         String url = "https://admin.dincharyamart.com/api/media/render?path="+ imagePath;
         Glide.with(context).load(url).into(viewHolder.imageView);



        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent =new Intent(context, CategorySubCategory.class);
                intent.putExtra("name",item.getData().get(i).getName());
                intent.putExtra("categoryId",String.valueOf(item.getData().get(i).getStoreCategoryId()));
                context.startActivity(intent);
               // cateId = model.getCategoryId();
               // ad_interface.catId(cateId);
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return item.getData().size();
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
