package com.clov3rlabs.dependencyinjection.utilities;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.clov3rlabs.dependencyinjection.injector.Injector;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;

import javax.inject.Singleton;


/**
 * Created by jhon on 28/7/15.
 */
@Singleton
public class PersistentStore {


    public static final String SHARED_PREFERENCES = "PersistentStore";

    public static final String SIGNED_IN = "SIGNED_IN";
    public static final String SIGNED_USERNAME = "SIGNED_USERNAME";

    private static final String synchronizer = "synchronizer";


    private static Editor getSharedPreferencesEditor() {
        return Injector.get().getSharedPreferences().edit();
    }

    public void registerSharedPreferencesListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        Injector.get().getSharedPreferences().registerOnSharedPreferenceChangeListener(listener);
    }

    public void unregisterSharedPreferencesListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        Injector.get().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(listener);
    }

    public void set(String key, ArrayList<String> value) {
        JSONArray json = new JSONArray(value);

        synchronized (synchronizer) {
            getSharedPreferencesEditor().putString(key, json.toString()).commit();
        }
    }

    public ArrayList<String> get(String key, ArrayList<String> defaultValue) {
        String jsonString = Injector.get().getSharedPreferences().getString(key, "");
        try {
            JSONArray json = new JSONArray(jsonString);

            ArrayList<String> result = new ArrayList<String>();
            for (int i = 0; i < json.length(); i++) {
                result.add(json.getString(i));
            }

            return result;
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    /*
     * Basic Get/Set Methods
     */
    public static void set(String key, String value) {
        synchronized (synchronizer) {
            getSharedPreferencesEditor().putString(key, value).commit();
        }
    }

    public static String get(String key, String defaultValue) {
        SharedPreferences prefs = Injector.get().getSharedPreferences();
        return prefs.getString(key, defaultValue);
    }

    public static void set(String key, int value) {
        synchronized (synchronizer) {
            getSharedPreferencesEditor().putInt(key, value).commit();
        }
    }

    public static int get(String key, int defaultValue) {
        SharedPreferences prefs = Injector.get().getSharedPreferences();
        return prefs.getInt(key, defaultValue);
    }

    public static void set(String key, boolean value) {
        synchronized (synchronizer) {
            getSharedPreferencesEditor().putBoolean(key, value).commit();
        }
    }

    public static boolean get(String key, boolean defaultValue) {
        return Injector.get().getSharedPreferences().getBoolean(key, defaultValue);
    }

    public static void set(String key, float value) {
        synchronized (synchronizer) {
            getSharedPreferencesEditor().putFloat(key, value).commit();
        }
    }

    public static float get(String key, float defaultValue) {
        return Injector.get().getSharedPreferences().getFloat(key, defaultValue);
    }

    public static void set(String key, long value) {
        synchronized (synchronizer) {
            getSharedPreferencesEditor().putLong(key, value).commit();
        }
    }

    public static long get(String key, long defaultValue) {
        return Injector.get().getSharedPreferences().getLong(key, defaultValue);
    }

    public static void set(String key, Date value) {
        synchronized (synchronizer) {
            getSharedPreferencesEditor().putLong(key, value.getTime()).commit();
        }
    }

    public static Date get(String key, Date defaultValue) {
        return new Date(Injector.get().getSharedPreferences().getLong(key, defaultValue.getTime()));
    }

    public static void unset(String key){
        synchronized (synchronizer) {
            getSharedPreferencesEditor().remove(key).commit();
        }
    }

    public static void clearAll() {
        synchronized (synchronizer) {
            PreferenceManager.getDefaultSharedPreferences(Injector.get().getContext()).edit().clear().commit();
            getSharedPreferencesEditor().clear().commit();
        }
    }

}