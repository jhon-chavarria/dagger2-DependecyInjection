package com.clov3rlabs.dependencyinjection;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.LayoutInflater;

import com.clov3rlabs.dependencyinjection.utilities.PersistentStore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jhon on 28/7/15.
 * Here we defined all the services we want to make available for injection
 */
@Module
public class AndroidModules {
    private final Context context;

    public AndroidModules(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public Context provideApplicationContext() {
        return context;
    }

    @Provides
    SharedPreferences provideSharedPrefs() {
        return context.getSharedPreferences(PersistentStore.SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Provides
    public LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(context);
    }

    @Provides
    public Resources provideResources() {
        return context.getResources();
    }

}