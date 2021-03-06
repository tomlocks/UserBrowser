package com.tomlockapps.userbrowser.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Interface that defines User Model.
 *
 * Created by tomlo on 25.10.2016.
 */

public interface IUserModel extends Parcelable{
    String getName();
    String getAvatarUrl();
    UserColor getBackgroundColor();
}
