package com.io.choozo.Fragment.profile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.io.choozo.R;
import com.io.choozo.adapter.profileadapter.SavedAdressRvAdapter;
import com.io.choozo.model.dummydataModel.SavedAdressDataModel;

import java.util.ArrayList;
import java.util.List;

public class SavedAddress  extends Fragment implements View.OnClickListener {

    RecyclerView rvSavedAdress;
    SavedAdressRvAdapter adapter;
    List<SavedAdressDataModel> item =new ArrayList<>();
    Activity activity;
    Button btnAddAddress;
    Dialog dialog;
    String strAddress1,strAddress2,strCity,strState,strPinCode,strAddressType;

    public SavedAddress(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.saved_address,container,false);
        intializeViews(v);
        bindListner();
        return v;
    }

    /* --------------------------------intialize all views that are used in this activity-----------------------------------------*/


    private void intializeViews(View v) {
        activity = getActivity();
        rvSavedAdress = (RecyclerView)v.findViewById(R.id.rv_saved_address);
        btnAddAddress = (Button)v.findViewById(R.id.btn_add_address) ;
        dataSetToRecyclerView();
    }

    /* -----------------------------------------bind all views that are used in this activity-----------------------------------*/

    private void bindListner() {
        btnAddAddress.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_address :
                openDialogOfAddAdress();
                return;
        }

    }
    /*------------------------------------------------- add address dialog----------------------------------------------------*/

    public void openDialogOfAddAdress() {
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        dialog.getWindow().setLayout((6 * width) / 7, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.add_address_dialog);
        dialog.setTitle("");
        final TextView Yes = (TextView) dialog.findViewById(R.id.yes);
        final TextView No = (TextView) dialog.findViewById(R.id.no);
        final EditText etAddress1 = (EditText) dialog.findViewById(R.id.et_address1);
        final EditText etAddress2 = (EditText) dialog.findViewById(R.id.et_address2);
        final EditText etCity = (EditText) dialog.findViewById(R.id.et_city);
        final EditText etState= (EditText) dialog.findViewById(R.id.et_state);
        final EditText etPinCode = (EditText) dialog.findViewById(R.id.et_post_code);
        final EditText etAddressType = (EditText) dialog.findViewById(R.id.et_address_type);
        final ImageView Clear = (ImageView) dialog.findViewById(R.id.clear);

        Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addAddressValidateData(etAddress1,etAddress2,etCity,etState,etPinCode,etAddressType);
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

    /*----------------------------------------------- add address check fields empty or not----------------------------------------*/

    private void addAddressValidateData(EditText etAddress1, EditText etAddress2, EditText etCity,
                                        EditText etState, EditText etPinCode, EditText etAddressType) {
        strAddress1 = etAddress1.getText().toString().trim();
        strAddress2 = etAddress2.getText().toString().trim();
        strCity = etCity.getText().toString().trim();
        strState = etState.getText().toString().trim();
        strPinCode = etPinCode.getText().toString().trim();
        strAddressType = etAddressType.getText().toString().trim();
        if(strAddress1.isEmpty() || strAddress2.isEmpty() || strCity.isEmpty() || strState.isEmpty() || strPinCode.isEmpty() || strAddressType.isEmpty()){
            etAddress1.setError("Please Enter Address 1");
            etAddress2.setError("Please Enter Address 2");
            etCity.setError("Please Enter City");
            etState.setError("Please Enter State");
            etPinCode.setError("Please Enter PinCode");
            etAddressType.setError("Please Enter Address Type");
        }else {

        }
    }


    private void dataSetToRecyclerView() {
        rvSavedAdress.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));
        item.clear();
        item.add(new SavedAdressDataModel("John smith","701, Block - B, Siddhi Vinayak Tower,Ahmedabad-380051, Gujarat, INDIA +91 98765 43210"));
        item.add(new SavedAdressDataModel("Vernon Martin","925 Buddy Motorway, New Street, USA 380152 +91 43210 98765 "));
        item.add(new SavedAdressDataModel("Ian Grant","4855 Durgan Wall, Perfect Arcade, USA 380152 +91 98765 43210"));
        adapter = new SavedAdressRvAdapter(activity,item);
        rvSavedAdress.setAdapter(adapter);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }


}
