package br.trindade.androidbasics.util;

import android.content.Context;

/**
 * @author maiko.trindade
 */
public class BasicsApplication extends android.app.Application {

    public static final String API_KEY_ROTTEN_TOMATOES = "nc6hgu9erb379bew5qjvf2ts";
    private static BasicsApplication sInstance;

    public static BasicsApplication getInstance() {
        return sInstance;
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

}
