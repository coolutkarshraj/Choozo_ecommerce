package com.io.choozo;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.io.choozo.model.responseModel.CategoryResponseModel;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class ApiCaller {

    /*------------------------------------------------- Category Api-----------------------------------------------------------*/

    public static void getCategoryList(Activity activity, String url,
                                       final FutureCallback<CategoryResponseModel> apiCallBack){
         final Gson gson = new Gson();
         Ion.with(activity)
            .load(UrlLocator.getFinalUrl(url))
            .noCache()
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
        @Override
        public void onCompleted(Exception e, JsonObject result) {
            CategoryResponseModel categoryResponseModel =gson.fromJson(result,CategoryResponseModel.class);
            apiCallBack.onCompleted(e,categoryResponseModel);
        }
    });
}

    /*-------------------------------------------- Banner Image Get-----------------------------------------------------------*/
}
