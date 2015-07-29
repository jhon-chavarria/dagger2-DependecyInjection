package com.clov3rlabs.dependencyinjection.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.clov3rlabs.dependencyinjection.R;
import com.clov3rlabs.dependencyinjection.activities.GeneralActivity;
import com.clov3rlabs.dependencyinjection.activities.MainActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by jhon on 29/7/15.
 */
@Singleton
public class IntentUtil {

    private Context context;

    @Inject
    public IntentUtil(Context context) {
        this.context = context;
    }

    public static void goToGeneralActivity(Activity activity, Class cls, boolean useTransition) {
        goToGeneralActivity(activity, cls, false, useTransition);
    }

    public static void goToGeneralActivity(Activity activity, Class cls, boolean showSettings, boolean useTransition) {
        final Intent intent = getGeneralActivityIntent(activity, cls, showSettings);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);

        if (useTransition) {
            activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }

    public static Intent getGeneralActivityIntent(Activity activity, Class cls, boolean showSettings) {
        final Intent intent = new Intent(activity, GeneralActivity.class);
        intent.putExtra(GeneralActivity.EXTRA_CLASS_NAME, cls.getName());

        if (showSettings) {
            intent.putExtra(GeneralActivity.EXTRA_SHOW_MENU_SETTINGS, true);
        }

        return intent;
    }
    public static void startActivity(Activity activity, Intent intent) {
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public static void startActivityForResult(Activity activity, Intent intent, int requestCode) {
        activity.startActivityForResult(intent, requestCode);
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void goToMainActivity() {
        final Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
