/**
 * Copyright (c) 2015 Android ATC.
 *
 * Author: Android ATC Training Team.
 *
 * Source code in this project is provided for trainers of
 * course AND-401 titled "Android Application Development".
 *
 * The is the source code for Lab 8 of the text book.
 *
 */
package com.example.leonardo.clase5;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionDetector {

    private Context context;
    ConnectionDetector(Context context){
        this.context=context;
    }
    public boolean isConnectingToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
