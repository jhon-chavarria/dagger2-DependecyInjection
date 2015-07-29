package com.clov3rlabs.dependencyinjection.utilities;

import android.content.Context;

import com.clov3rlabs.dependencyinjection.R;

import javax.inject.Inject;

/**
 * Created by jhon on 29/7/15.
 */
public class ResourcesUtil {

    private Context context;

    // Simple, using injection we don't have to create an instance of this class or even pass the context through the parameters
    @Inject
    public ResourcesUtil ( final Context context ) {
        this.context = context;
    }


    // You see these two methods ? in these case we usually need to add context as parameter , but using injection we avoid this.

    public String getAppName() {
        return context.getString(R.string.app_name);
    }


    public String getHellowWord() {
        return context.getString(R.string.hello_world);
    }

}
