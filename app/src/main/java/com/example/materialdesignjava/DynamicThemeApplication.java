package com.example.materialdesignjava;

import android.app.Application;

import com.google.android.material.color.DynamicColors;

public class DynamicThemeApplication extends Application {
    @Override
    public void onCreate() {
        DynamicColors.applyToActivitiesIfAvailable(this);
        super.onCreate();
    }
}
