package com.io.choozo.adapter.profileadapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.io.choozo.ApiCaller;
import com.io.choozo.Config;
import com.io.choozo.R;
import com.io.choozo.localStorage.PreferenceManager;
import com.io.choozo.model.dataModel.GetAddressDataModel;
import com.io.choozo.model.responseModel.AddAddressResponseModel;
import com.io.choozo.model.responseModel.LoginResponseModel;
import com.io.choozo.model.responseModel.UpdateAddResponseModel;
import com.io.choozo.util.NewProgressBar;
import com.io.choozo.util.commonDialog;
import com.io.choozo.util.userOnlineInfo;
import com.koushikdutta.async.future.FutureCallback;

import java.util.List;

public class SavedAdressRvAdapter extends RecyclerView.Adapter<SavedAdressRvAdapter.ViewHolder> {

    Context context;
    List<GetAddressDataModel> item;
    Dialog dialog;
    String addressType = "0";
    private PreferenceManager preferenceManager;
    String endPointofUpdateAddress,addressId;
    Long userId;
    NewProgressBar dialogs;
    userOnlineInfo user;
    String strAddress1,strAddress2,strCity,strState,strPincode,token;

    public SavedAdressRvAdapter(Context context, List<GetAddressDataModel> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public SavedAdressRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.saved_address_card_design,viewGroup,false);
    preferenceManager = new PreferenceManager(context);
    user = new userOnlineInfo();
    dialogs = new NewProgressBar(context);
    getDataFromLocalStorage();
    return new ViewHolder(view);
    }

    /* ---------------------------------------------login data get from local storgae(for token get)---------------------------------------*/

    private void getDataFromLocalStorage() {
        Gson gson = new Gson();
        String getJson = preferenceManager.getString(PreferenceManager.loginData);
        LoginResponseModel obj = gson.fromJson(getJson, LoginResponseModel.class);
        token = obj.getData().getToken();
        userId = obj.getData().getUser().getId();



    }

    @Override
    public void onBindViewHolder(@NonNull SavedAdressRvAdapter.ViewHolder viewHolder, final int i) {

        final GetAddressDataModel model = item.get(i);
        viewHolder.name.setText(model.getFirstName());
        viewHolder.address.setText(model.getAddress1() +" "+model.getAddress2() +", "+model.getCity() +" - "+model.getPostcode()
                            +", "+ model.getState() +" "+model.getPhoneNo());
        viewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            updateData(model,i);
            }
        });


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
  /* -----------------------------------------------Update data work Start-------------------------------------------------------------*/

    private void updateData(GetAddressDataModel model, int i) {
        Toast.makeText(context, ""+model.getAddressId() +"id = "+i, Toast.LENGTH_SHORT).show();
        addressId = String.valueOf(model.getAddressId());
        dataAddIntoString(model);
        updateDataDialog();
    }

    private void dataAddIntoString(GetAddressDataModel model) {
        strAddress1 = model.getAddress1();
        strAddress2 = model.getAddress2();
        strCity = model.getCity();
        strState = model.getState();
        strPincode = model.getPostcode();
        addressType = String.valueOf(Long.valueOf(model.getAddressType()));
    }


    /* --------------------------------------------------Update Data Dialog-----------------------------------------------------------*/

    private void updateDataDialog() {

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        dialog.getWindow().setLayout((6 * width) / 7, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.update_address_dialog);
        dialog.setTitle("");
        final TextView Yes = (TextView) dialog.findViewById(R.id.yes);

        final TextView No = (TextView) dialog.findViewById(R.id.no);
        final EditText etAddress1 = (EditText) dialog.findViewById(R.id.et_address1);
        final EditText etAddress2 = (EditText) dialog.findViewById(R.id.et_address2);
        final EditText etCity = (EditText) dialog.findViewById(R.id.et_city);
        final EditText etState= (EditText) dialog.findViewById(R.id.et_state);
        final EditText etPinCode = (EditText) dialog.findViewById(R.id.et_post_code);
        final RadioButton rbHome = (RadioButton) dialog.findViewById(R.id.rb_home);
        final RadioButton rbWork = (RadioButton) dialog.findViewById(R.id.rb_work);
        final ImageView Clear = (ImageView) dialog.findViewById(R.id.clear);

        etAddress1.setText(strAddress1);
        etAddress2.setText(strAddress2);
        etCity.setText(strCity);
        etState.setText(strState);
        etPinCode.setText(strPincode);
        if(addressType == "0"){
            rbWork.setChecked(false);
            rbHome.setChecked(true);
        }else {
            rbHome.setChecked(false);
            rbWork.setChecked(true);
        }

        rbHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressType="0";
                rbHome.setChecked(true);
                rbWork.setChecked(false);
            }
        });
        rbWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressType="1";
                rbHome.setChecked(false);
                rbWork.setChecked(true);
            }
        });


        Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "yes", Toast.LENGTH_SHORT).show();
                strAddress1 = etAddress1.getText().toString();
                strAddress2 = etAddress2.getText().toString();
                strState = etState.getText().toString();
                strCity = etCity.getText().toString();
                strPincode = etPinCode.getText().toString();
                addDataIntoApi(strAddress1,strAddress2,strCity,strState,strPincode,addressType);

            }
        });
        No.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Clear.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }


    private void apiUrl(){
        endPointofUpdateAddress = Config.Url.UpdateAddress+"/"+addressId;

    }

    /*------------------------------------------------------- Api for Update Data---------------------------------------------------*/

    private void addDataIntoApi(String strAddress1, String strAddress2, String strCity,
                                String strState, String strPinCode, String addressType) {
            dialogs.show();
            apiUrl();
            ApiCaller.updateAddress(context, endPointofUpdateAddress, userId, strAddress1, strAddress2, strCity, strState, strPinCode, addressType, token,
                    new FutureCallback<UpdateAddResponseModel>() {
                        @Override
                        public void onCompleted(Exception e, UpdateAddResponseModel result) {
                            if(e!=null){
                                return;
                            }
                            getData(result);
                        }
                    });



    }

    private void getData(UpdateAddResponseModel result) {
        if(result.getStatus() == 1){
            dialogs.dismiss();
            dialog.dismiss();
            Toast.makeText(context, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
        }else {
            dialogs.dismiss();
            dialog.dismiss();
            Toast.makeText(context, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


}
