package com.clov3rlabs.dependencyinjection.utilities;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by jhon on 29/7/15.
 */
@Singleton
public class SettingsUtil {

    private Context context;

    // Simple, using injection we don't have to create an instance of this class or even pass the context through the parameters
    @Inject
    public SettingsUtil(Context context) {
        this.context = context;
    }

    public void setUsername(String value) {
        PersistentStore.set(PersistentStore.SIGNED_USERNAME, value);
    }

    public void signIn(boolean value) {
        PersistentStore.set(PersistentStore.SIGNED_IN, value);
    }

    public boolean isSignedIn() {
        return PersistentStore.get(PersistentStore.SIGNED_IN, false);
    }


}
