package com.clov3rlabs.dependencyinjection.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.clov3rlabs.dependencyinjection.R;
import com.clov3rlabs.dependencyinjection.injector.Injector;
import com.clov3rlabs.dependencyinjection.utilities.IntentUtil;
import com.clov3rlabs.dependencyinjection.utilities.PersistentStore;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by jhon on 29/7/15.
 * Main purpose of this class is to include all the fragments instead of creating many activities and setting them up on manifest.
 * another util stuff in this class is that we can have the toolbar in just one place.
 */
public class GeneralActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    IntentUtil intentUtil;

    public static final String EXTRA_CLASS_NAME = "GENERAL_ACTIVITY_EXTRA_CLASS_NAME";
    public static final String EXTRA_SHOW_HOME_AS_UP = "GENERAL_ACTIVITY_SHOW_HOME_AS_UP";
    public static final String EXTRA_SHOW_MENU_SETTINGS = "GENERAL_ACTIVITY_SHOW_SETTINGS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.get().inject(this);

        if (getIntent() == null) {
            return;
        }

        setSupportActionBar(toolbar);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final FragmentManager manager = getFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_fragment,
                Fragment.instantiate(this, getIntent().getStringExtra(EXTRA_CLASS_NAME)));
        transaction.commit();

        if (getSupportActionBar() == null) {
            return;
        }


        if (getIntent().hasExtra(EXTRA_SHOW_HOME_AS_UP) &&
                getIntent().getBooleanExtra(EXTRA_SHOW_HOME_AS_UP, false)) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        if (getIntent().hasExtra(EXTRA_SHOW_MENU_SETTINGS) && getIntent().getBooleanExtra(EXTRA_SHOW_MENU_SETTINGS, true))
            menu.findItem(R.id.action_settings).setVisible(true);

        return true;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_general;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            return true;
        } else if (item.getItemId() == R.id.action_log_out) {
            PersistentStore.clearAll();
            intentUtil.goToMainActivity();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}