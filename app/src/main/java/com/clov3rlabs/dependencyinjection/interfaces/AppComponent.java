package com.clov3rlabs.dependencyinjection.interfaces;

import android.content.Context;
import android.content.SharedPreferences;

import com.clov3rlabs.dependencyinjection.AndroidModules;
import com.clov3rlabs.dependencyinjection.DependencyInjectionApplication;
import com.clov3rlabs.dependencyinjection.activities.GeneralActivity;
import com.clov3rlabs.dependencyinjection.fragments.LoginFragment;
import com.clov3rlabs.dependencyinjection.fragments.SecondFragment;
import com.clov3rlabs.dependencyinjection.fragments.ThirdFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jhon on 28/7/15.
 * Important class: here we defined which fragments or activities can be injected
 */
@Singleton
@Component(modules = AndroidModules.class)
public interface AppComponent {

    Context getContext();

    void inject(DependencyInjectionApplication app);

    void inject(LoginFragment LoginFragment);

    void inject(SecondFragment secondFragment);

    void inject(ThirdFragment thirdFragment);

    void inject(GeneralActivity generalActivity);

    SharedPreferences getSharedPreferences();
}
