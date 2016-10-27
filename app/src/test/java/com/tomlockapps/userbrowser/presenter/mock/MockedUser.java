package com.tomlockapps.userbrowser.presenter.mock;

import android.os.Parcel;

import com.tomlockapps.userbrowser.viewmodel.IUserModel;
import com.tomlockapps.userbrowser.viewmodel.UserColor;

/**
 * Created by tomlo on 27.10.2016.
 */

public class MockedUser implements IUserModel {

    private String name;
    private String url;
    private UserColor userColor;

    public MockedUser(String name, String url, UserColor userColor) {
        this.name = name;
        this.url = url;
        this.userColor = userColor;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return url;
    }

    public UserColor getBackgroundColor() {
        return userColor;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.url);
        dest.writeInt(this.userColor == null ? -1 : this.userColor.ordinal());
    }

    public MockedUser() {
    }

    protected MockedUser(Parcel in) {
        this.name = in.readString();
        this.url = in.readString();
        int tmpUserColor = in.readInt();
        this.userColor = tmpUserColor == -1 ? null : UserColor.values()[tmpUserColor];
    }

    public static final Creator<MockedUser> CREATOR = new Creator<MockedUser>() {
        @Override
        public MockedUser createFromParcel(Parcel source) {
            return new MockedUser(source);
        }

        @Override
        public MockedUser[] newArray(int size) {
            return new MockedUser[size];
        }
    };
}
