package com.tomlockapps.userbrowser.data.source.local.room.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;

import com.tomlockapps.userbrowser.data.IUserModel;
import com.tomlockapps.userbrowser.data.source.UserDataSource;

/**
 * Created by tomlo on 06/03/2018.
 */

@Entity
public class RoomUserModel implements IUserModel {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "avatar_url")
    private String avatarUrl;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "source_type")
    private @UserDataSource.Type int sourceType;
    @ColumnInfo(name = "timestamp")
    private long timestamp;

    public int getUid() {
        return uid;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getName() {
        return name;
    }

    public int getSourceType() {
        return sourceType;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.uid);
        dest.writeString(this.avatarUrl);
        dest.writeString(this.name);
        dest.writeInt(this.sourceType);
    }

    public RoomUserModel() {
    }

    public RoomUserModel(String avatarUrl, String name, int sourceType, long timestamp) {
        this.avatarUrl = avatarUrl;
        this.name = name;
        this.sourceType = sourceType;
        this.timestamp = timestamp;
    }

    protected RoomUserModel(Parcel in) {
        this.uid = in.readInt();
        this.avatarUrl = in.readString();
        this.name = in.readString();
        this.sourceType = in.readInt();
    }

    public static final Creator<RoomUserModel> CREATOR = new Creator<RoomUserModel>() {
        @Override
        public RoomUserModel createFromParcel(Parcel source) {
            return new RoomUserModel(source);
        }

        @Override
        public RoomUserModel[] newArray(int size) {
            return new RoomUserModel[size];
        }
    };
}
