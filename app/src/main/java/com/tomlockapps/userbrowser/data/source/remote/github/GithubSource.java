package com.tomlockapps.userbrowser.data.source.remote.github;

import com.tomlockapps.userbrowser.data.IUserModel;
import com.tomlockapps.userbrowser.data.source.UserDataSource;
import com.tomlockapps.userbrowser.data.source.remote.RetrofitServiceCreator;

import rx.Observable;

/**
 * Created by tomlo on 25.10.2016.
 */

public class GithubSource implements UserDataSource {
    private static final String API = "https://api.github.com";

    private GithubService service;

    public GithubSource() {
        service = RetrofitServiceCreator.createRetrofitService(GithubService.class, API);
    }

    @Override
    public Observable<IUserModel> getUsers() {
        return service.getUsers().flatMap(Observable::from);
    }

    @Override
    public void refreshUsers() {
        // Not required because the {@link UserDataRepository} handles the logic of refreshing the
        // tasks from all the available data sources.
    }
}
