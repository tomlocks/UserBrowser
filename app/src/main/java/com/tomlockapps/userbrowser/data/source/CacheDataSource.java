package com.tomlockapps.userbrowser.data.source;

import com.tomlockapps.userbrowser.data.IUserModel;

import java.util.List;

import rx.Observable;

/**
 * Created by tomlo on 06/03/2018.
 */

public interface CacheDataSource extends UserDataSource {
    void insertData(List<IUserModel> modelList);
    void insert(IUserModel modelList);
}
