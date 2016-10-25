package com.tomlockapps.userbrowser.action;

import android.app.Activity;

import com.tomlockapps.userbrowser.UserDetailActivity;
import com.tomlockapps.userbrowser.viewmodel.IUserModel;

/**
 * Created by tomlo on 25.10.2016.
 */

public class FragmentUsersListActions implements IUsersListActions {
    private Activity activity;

    public FragmentUsersListActions(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void showUserDetails(IUserModel userModel) {
        UserDetailActivity.startActivity(activity, userModel);
    }
}
