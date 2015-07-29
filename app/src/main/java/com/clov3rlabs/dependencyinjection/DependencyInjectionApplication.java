package com.clov3rlabs.dependencyinjection;

import android.app.Application;

import com.clov3rlabs.dependencyinjection.injector.Injector;
import com.clov3rlabs.dependencyinjection.interfaces.AppComponent;
import com.clov3rlabs.dependencyinjection.interfaces.DaggerAppComponent;

/**
 * Created by jhon on 29/7/15.
 */
public class DependencyInjectionApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDagger();
    }

    private void initializeDagger() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .androidModules(new AndroidModules(this))
                .build();

        Injector.setComponent(appComponent);
    }
}
