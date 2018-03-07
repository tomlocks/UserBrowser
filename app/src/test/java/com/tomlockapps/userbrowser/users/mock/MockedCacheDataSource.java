package com.tomlockapps.userbrowser.users.mock;

import com.tomlockapps.userbrowser.data.IUserModel;
import com.tomlockapps.userbrowser.data.source.CacheDataSource;
import com.tomlockapps.userbrowser.data.source.UserDataSource;

import java.util.List;

import rx.Observable;

/**
 * Created by tomlo on 07/03/2018.
 */

public class MockedCacheDataSource extends MockedUserDataSource implements CacheDataSource {

    public MockedCacheDataSource(List<IUserModel> users) {
        super(users);
    }

    @Override
    public void insertData(List<IUserModel> modelList) {

    }

    @Override
    public void insert(IUserModel modelList) {

    }
}
