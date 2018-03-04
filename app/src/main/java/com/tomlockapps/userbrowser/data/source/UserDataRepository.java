package com.tomlockapps.userbrowser.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.tomlockapps.userbrowser.data.IUserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by tomlo on 3/4/2018.
 */

public class UserDataRepository implements UserDataSource {

    public static final long CACHE_MAX_AGE = TimeUnit.MINUTES.toMillis(1); // todo cache support

    @Nullable
    private final UserDataSource localDataSource;
    @NonNull
    private final UserDataSource[] remoteDataSources;

    protected UserDataRepository(@Nullable UserDataSource localDataSource, @NonNull UserDataSource... remoteDataSources) {
        this.localDataSource = localDataSource;
        this.remoteDataSources = remoteDataSources;
    }

    @Override
    public Observable<IUserModel> getUsers() {
        List<Observable<IUserModel>> observables = new ArrayList<>();

        for (UserDataSource source : remoteDataSources) {
            observables.add(source.getUsers());
        }

        return Observable.merge(observables);
    }

    public static UserDataRepository createWithoutCacheSupport(UserDataSource... remoteDataSources) {
        if(remoteDataSources == null)
            throw new IllegalStateException("You must provide at least one data source.");

        return new UserDataRepository(null, remoteDataSources);
    }

    public static UserDataRepository createWithCacheSupport(UserDataSource localDataSource, UserDataSource... remoteDataSources) {
        if(localDataSource == null || remoteDataSources == null)
            throw new IllegalStateException("You must provide at least one remote data source and one local data source");

        return new UserDataRepository(localDataSource, remoteDataSources);
    }
}
