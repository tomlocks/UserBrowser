package com.tomlockapps.userbrowser.base.providers.impl;

import android.app.Activity;
import android.app.Service;
import android.content.Context;

import com.tomlockapps.userbrowser.base.providers.IResourceProvider;

/**
 * Created by tomlo on 3/4/2018.
 */

public class AndroidResourceProvider implements IResourceProvider {

    private final Context context;

    public AndroidResourceProvider(Context context) {
        if(context instanceof Activity || context instanceof Service)
            throw new IllegalStateException("You must pass an application context to prevent memory leaks.");

        this.context = context;
    }

    @Override
    public String getString(int resId) {
        return context.getString(resId);
    }
}
