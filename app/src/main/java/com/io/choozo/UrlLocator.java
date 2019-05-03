package com.io.choozo;

/* Created By Inderjot Singh 3 may - 2019*/

public class UrlLocator {
    private static String tempIP = "";

    public static String getBaseIP() {
        return Config.Base_url;

    }

    public static String getFinalUrl(String url) {
        String ip = getBaseIP();
        return "http://" + ip + ":8090/api/" + url;
    }

}
