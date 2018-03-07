package com.tomlockapps.userbrowser.data.source;

import android.support.annotation.IntDef;

import com.tomlockapps.userbrowser.data.IUserModel;

import java.lang.annotation.Retention;

import rx.Observable;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by tomlo on 3/4/2018.
 */

public interface UserDataSource {
    Observable<IUserModel> getUsers();
    void refreshUsers();

    @Retention(SOURCE)
    @IntDef({DAILY_MOTION, GITHUB})
    @interface Type {}

    int DAILY_MOTION = 1;
    int GITHUB = 2;
}
