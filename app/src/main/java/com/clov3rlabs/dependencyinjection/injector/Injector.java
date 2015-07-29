package com.clov3rlabs.dependencyinjection.injector;


import com.clov3rlabs.dependencyinjection.interfaces.AppComponent;

/**
 * Created by jhon on 28/7/15.
 */
public class Injector {
    private static AppComponent component;

    public static void setComponent(AppComponent appComponent) {
        component = appComponent;
    }

    public static AppComponent get() {
        return component;
    }
}