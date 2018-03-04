package com.tomlockapps.userbrowser.data;

import android.os.Parcelable;

import com.tomlockapps.userbrowser.data.source.UserDataSource;

/**
 * Interface that defines User Model.
 *
 * Created by tomlo on 25.10.2016.
 */

public interface IUserModel extends Parcelable {
    String getName();
    String getAvatarUrl();
    @UserDataSource.Type int getSourceType();
}
