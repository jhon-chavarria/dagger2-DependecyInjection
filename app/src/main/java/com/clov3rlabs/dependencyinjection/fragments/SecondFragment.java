package com.clov3rlabs.dependencyinjection.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clov3rlabs.dependencyinjection.R;
import com.clov3rlabs.dependencyinjection.activities.GeneralActivity;
import com.clov3rlabs.dependencyinjection.injector.Injector;
import com.clov3rlabs.dependencyinjection.utilities.DeviceUtil;
import com.clov3rlabs.dependencyinjection.utilities.IntentUtil;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jhon on 29/7/15.
 */
public class SecondFragment extends BaseFragment {

    @Bind(R.id.app_version)
    TextView appVersion;

    @Bind(R.id.internet_status)
    TextView internetStatus;

    @Inject
    DeviceUtil deviceUtil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        // Let's inject the fragment in order to use inject methods
        Injector.get().inject(this);

        internetStatus.setText("Internet Status: " + deviceUtil.isOnline());
        appVersion.setText("App Version: " + deviceUtil.getAppVersion());

        return view;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_second;
    }

    @Override
    protected int getTitleResourceId() {
        return R.string.second_fragment;
    }

    @OnClick(R.id.next)
    public void next() {
        Intent intent = IntentUtil.getGeneralActivityIntent(getActivity(), ThirdFragment.class, true);
        intent.putExtra(GeneralActivity.EXTRA_SHOW_HOME_AS_UP, true);
        intent.putExtra(GeneralActivity.EXTRA_SHOW_MENU_SETTINGS, false);
        IntentUtil.startActivity(getActivity(), intent);
    }
}
