package com.tomlockapps.userbrowser.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.tomlockapps.userbrowser.data.IUserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;

/**
 * Created by tomlo on 3/4/2018.
 */

public class UserDataRepository implements UserDataSource {

    @Nullable
    private final CacheDataSource cacheDataSource;
    @NonNull
    private final UserDataSource[] remoteDataSources;

    private boolean cacheIsDirty; // from Google's Todos app

    protected UserDataRepository(@Nullable CacheDataSource cacheDataSource, @NonNull UserDataSource... remoteDataSources) {
        this.cacheDataSource = cacheDataSource;
        this.remoteDataSources = remoteDataSources;
    }

    @Override
    public Observable<IUserModel> getUsers() {
        List<Observable<IUserModel>> observables = new ArrayList<>();

        for (UserDataSource source : remoteDataSources) {
            observables.add(source.getUsers());
        }

        Observable<IUserModel> remoteObservable = Observable.merge(observables).map(userModel -> {
            if (cacheDataSource != null)
                cacheDataSource.insert(userModel);

            return userModel;
        });

        if(cacheIsDirty && cacheDataSource != null) {
            return Observable.fromCallable(() -> {
                cacheDataSource.refreshUsers();
                cacheIsDirty = false;

                return null;
            }).flatMap(o -> remoteObservable);
        } else if(cacheDataSource != null) {
            return cacheDataSource.getUsers().switchIfEmpty(remoteObservable);
        } else {
            return remoteObservable;
        }
    }

    @Override
    public void refreshUsers() {
        cacheIsDirty = true;
    }

    public static UserDataRepository createWithoutCacheSupport(UserDataSource... remoteDataSources) {
        if(remoteDataSources == null)
            throw new IllegalStateException("You must provide at least one data source.");

        return new UserDataRepository(null, remoteDataSources);
    }

    public static UserDataRepository createWithCacheSupport(CacheDataSource localDataSource, UserDataSource... remoteDataSources) {
        if(localDataSource == null || remoteDataSources == null)
            throw new IllegalStateException("You must provide at least one remote data source and one local data source");

        return new UserDataRepository(localDataSource, remoteDataSources);
    }
}
