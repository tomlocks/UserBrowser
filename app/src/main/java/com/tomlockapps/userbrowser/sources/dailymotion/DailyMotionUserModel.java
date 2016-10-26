package com.tomlockapps.userbrowser.sources.dailymotion;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tomlockapps.userbrowser.viewmodel.IUserModel;
import com.tomlockapps.userbrowser.viewmodel.UserColor;

/**
 * Created by tomlo on 26.10.2016.
 */

public class DailyMotionUserModel implements IUserModel{
    @SerializedName("avatar_360_url")
    @Expose
    public String avatar360Url;
    @SerializedName("username")
    @Expose
    public String username;

    @Override
    public String getName() {
        return username;
    }

    @Override
    public String getAvatarUrl() {
        return avatar360Url;
    }

    @Override
    public UserColor getBackgroundColor() {
        return UserColor.LIME;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.avatar360Url);
        dest.writeString(this.username);
    }

    public DailyMotionUserModel() {
    }

    protected DailyMotionUserModel(Parcel in) {
        this.avatar360Url = in.readString();
        this.username = in.readString();
    }

    public static final Creator<DailyMotionUserModel> CREATOR = new Creator<DailyMotionUserModel>() {
        @Override
        public DailyMotionUserModel createFromParcel(Parcel source) {
            return new DailyMotionUserModel(source);
        }

        @Override
        public DailyMotionUserModel[] newArray(int size) {
            return new DailyMotionUserModel[size];
        }
    };
}
