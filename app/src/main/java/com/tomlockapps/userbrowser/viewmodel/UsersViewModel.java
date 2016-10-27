package com.tomlockapps.userbrowser.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * This method contains all the data that needs to be shown on Users List view.
 *
 * Created by tomlo on 25.10.2016.
 */

public class UsersViewModel  {
    private List<IUserModel> iUserModelList;

    public UsersViewModel(List<IUserModel> iUserModelList) {
        this.iUserModelList = iUserModelList;
    }

    public List<IUserModel> getiUserModelList() {
        return iUserModelList;
    }


}
