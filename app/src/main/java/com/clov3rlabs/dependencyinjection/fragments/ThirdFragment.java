package com.clov3rlabs.dependencyinjection.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clov3rlabs.dependencyinjection.R;
import com.clov3rlabs.dependencyinjection.injector.Injector;
import com.clov3rlabs.dependencyinjection.utilities.ResourcesUtil;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by jhon on 29/7/15.
 */
public class ThirdFragment extends BaseFragment {

    @Bind(R.id.name)
    TextView name;

    @Bind(R.id.resources)
    TextView resourcestxt;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    ResourcesUtil resources;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        // Let's inject the fragment in order to use inject methods
        Injector.get().inject(this);

        // This is just another way to inject SharedPreferences without using a third class (SettingsUtil)
        sharedPreferences.edit().putString("name", "Jonathan").apply();

        String preferenceValue = sharedPreferences.getString("name", "");

        name.setText("SharePreference Value: " + preferenceValue);

        // you see how easy is ? I don't need to pass the context and defined the method as static
        resourcestxt.setText(resources.getAppName() + " : " + resources.getHellowWord());

        return view;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_third;
    }

    @Override
    protected int getTitleResourceId() {
        return R.string.third_fragment;
    }

}
