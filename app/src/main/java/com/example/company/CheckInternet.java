package com.example.company;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

public class CheckInternet {
    public boolean isconnected(Context context)
    {
        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileconnection=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if((networkInfo!=null&&networkInfo.isConnected())||(mobileconnection!=null&&mobileconnection.isConnected()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
