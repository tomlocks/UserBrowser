package com.tomlockapps.userbrowser.data.source.remote.dailymotion;

import com.tomlockapps.userbrowser.data.IUserModel;
import com.tomlockapps.userbrowser.data.source.UserDataSource;
import com.tomlockapps.userbrowser.data.source.remote.RetrofitServiceCreator;

import java.util.Arrays;

import rx.Observable;

/**
 * Source of all dailymotion data.
 *
 * Created by tomlo on 25.10.2016.
 */

public class DailyMotionSource implements UserDataSource {
    private static final String API = "https://api.dailymotion.com";

    private DailyMotionService service;

    public DailyMotionSource() {
        service = RetrofitServiceCreator.createRetrofitService(DailyMotionService.class, API);
    }

    @Override
    public Observable<IUserModel> getUsers() {
        return service.getUsers(Arrays.asList("avatar_360_url", "username")).flatMap(dailyMotionResponse -> Observable.from(dailyMotionResponse.list));
    }

    @Override
    public void refreshUsers() {
        // Not required because the {@link UserDataRepository} handles the logic of refreshing the
        // tasks from all the available data sources.
    }
}
