package com.tomlockapps.userbrowser.base.providers.impl;

import com.tomlockapps.userbrowser.base.providers.ITimeProvider;

/**
 * Created by tomlo on 06/03/2018.
 */

public class SystemTimeProvider implements ITimeProvider {
    @Override
    public long getCurrentTime() {
        return System.currentTimeMillis();
    }
}
