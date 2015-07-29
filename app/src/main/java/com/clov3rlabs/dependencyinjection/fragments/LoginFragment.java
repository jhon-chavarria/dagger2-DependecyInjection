package com.clov3rlabs.dependencyinjection.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.clov3rlabs.dependencyinjection.R;
import com.clov3rlabs.dependencyinjection.activities.GeneralActivity;
import com.clov3rlabs.dependencyinjection.injector.Injector;
import com.clov3rlabs.dependencyinjection.utilities.IntentUtil;
import com.clov3rlabs.dependencyinjection.utilities.SettingsUtil;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jhon on 29/7/15.
 */
public class LoginFragment extends BaseFragment {

    @Inject
    SettingsUtil settingsUtil;

    private final String USER = "admin";
    private final String PASSWORD = "admin";

    @Bind(R.id.username)
    EditText username;

    @Bind(R.id.password)
    EditText password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        // Let's inject the fragment in order to use inject methods
        Injector.get().inject(this);

        if (settingsUtil.isSignedIn()) {
            goToSecondFragment();
        }

        return view;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_login;
    }

    @Override
    protected int getTitleResourceId() {
        return R.string.app_name;
    }

    @OnClick(R.id.sign_in)
    public void signIn() {

        if (username.getText().toString().equals("admin") &&  password.getText().toString().equals("admin")) {
            // keep the user
            settingsUtil.signIn(true);
            settingsUtil.setUsername(username.getText().toString());
            goToSecondFragment();

        } else {

            Toast.makeText(getActivity(), "Something wrong happened.",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void goToSecondFragment() {
        Intent intent = IntentUtil.getGeneralActivityIntent(getActivity(), SecondFragment.class, true);
        intent.putExtra(GeneralActivity.EXTRA_SHOW_HOME_AS_UP, false);
        IntentUtil.startActivity(getActivity(), intent);
        getActivity().finish();
    }

}
