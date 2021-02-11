package com.anadolstudio.nasalibrary.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

import com.anadolstudio.nasalibrary.R;


public class InternetConnection {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) return false;
        boolean isConnect = false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network nw = connectivityManager.getActiveNetwork();
            if (nw != null) {
                NetworkCapabilities actNw = connectivityManager.getNetworkCapabilities(nw);
                if (actNw != null) {
                    if (actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        isConnect = true;
                    } else if (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        isConnect = true;
                    } else if (actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                        isConnect = true;
                    }
                }
            }

        } else {
            NetworkInfo nInfo = connectivityManager.getActiveNetworkInfo();
            if (nInfo != null && nInfo.isConnected()) {
                isConnect = true;
            }
        }

        if (!isConnect) {
            Toast.makeText(context, context.getString(R.string.check_your_internet_connection), Toast.LENGTH_SHORT).show();
        }
        return isConnect;
    }
}
