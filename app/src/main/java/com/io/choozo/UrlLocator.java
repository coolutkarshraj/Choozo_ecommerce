package com.io.choozo;

/* Created By Inderjot Singh 3 may - 2019*/

import android.util.Log;

public class UrlLocator {
    private static String tempIP = "";

    public static String getBaseIP() {
        return Config.Base_url;
    }

    public static String getFinalUrl(String url) {
        String ip = getBaseIP();
        return "http://" + ip + ":8000/api/" + url;
    }

}
