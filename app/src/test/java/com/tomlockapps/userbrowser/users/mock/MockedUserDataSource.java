package com.tomlockapps.userbrowser.users.mock;

import com.tomlockapps.userbrowser.data.IUserModel;
import com.tomlockapps.userbrowser.data.source.UserDataSource;

import java.util.List;

import rx.Observable;

/**
 * Created by tomlo on 07/03/2018.
 */

public class MockedUserDataSource implements UserDataSource {

    private final List<IUserModel> users;

    public MockedUserDataSource(List<IUserModel> users) {
        this.users = users;
    }

    @Override
    public Observable<IUserModel> getUsers() {
        return Observable.from(users);
    }

    @Override
    public void refreshUsers() {

    }

}
