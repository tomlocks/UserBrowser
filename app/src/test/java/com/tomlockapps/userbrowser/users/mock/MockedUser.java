package com.tomlockapps.userbrowser.users.mock;

import android.os.Parcel;

import com.tomlockapps.userbrowser.data.IUserModel;

/**
 * Created by tomlo on 07/03/2018.
 */

public class MockedUser implements IUserModel {

    private final String name;
    private final String avatarUrl;
    private final int sourceType;

    public MockedUser(String name, String avatarUrl, int sourceType) {
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.sourceType = sourceType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAvatarUrl() {
        return avatarUrl;
    }

    @Override
    public int getSourceType() {
        return sourceType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
