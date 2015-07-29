package com.clov3rlabs.dependencyinjection.utilities;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by jhon on 29/7/15.
 */
@Singleton
public class DeviceUtil {

    private Context context;

    // Simple, using injection we don't have to create an instance of this class or even pass the context through the parameters
    @Inject
    public DeviceUtil(Context context) {
        this.context = context;
    }

    // You see these two methods ? in these case we usually need to add context as parameter , but using injection we avoid this.

    public int getAppVersion() {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    public boolean isOnline() {
        final ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnectedOrConnecting());
    }
}
