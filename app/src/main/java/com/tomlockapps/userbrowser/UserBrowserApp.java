package com.tomlockapps.userbrowser;

import android.app.Application;

import com.tomlockapps.userbrowser.base.providers.impl.SystemTimeProvider;
import com.tomlockapps.userbrowser.data.RepositoryInjection;
import com.tomlockapps.userbrowser.data.source.UserDataRepository;
import com.tomlockapps.userbrowser.data.source.local.room.RoomUsersRepository;
import com.tomlockapps.userbrowser.data.source.remote.dailymotion.DailyMotionSource;
import com.tomlockapps.userbrowser.data.source.remote.github.GithubSource;
import com.tomlockapps.userbrowser.util.ProcessUtil;

/**
 * Created by tomlo on 3/4/2018.
 */

public class UserBrowserApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if(!ProcessUtil.isMainProcess(getApplicationContext()))
            return;

        RepositoryInjection.setUserDataRepository(UserDataRepository.createWithCacheSupport(new RoomUsersRepository(getApplicationContext(), new SystemTimeProvider()),
                new GithubSource(), new DailyMotionSource()));
    }
}
