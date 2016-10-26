package com.tomlockapps.userbrowser.action;

import android.app.Activity;
import android.view.View;

import com.tomlockapps.userbrowser.UserDetailActivity;
import com.tomlockapps.userbrowser.viewmodel.IUserModel;

/**
 * Created by tomlo on 25.10.2016.
 */

public class AnimatedUsersListActions implements IUsersListActions {
    private Activity activity;
    private View imageView;

    public AnimatedUsersListActions(Activity activity) {
        this.activity = activity;
    }

    public void setImageView(View imageView) {
        this.imageView = imageView;
    }

    @Override
    public void showUserDetails(IUserModel userModel) {
        UserDetailActivity.startActivity(activity, imageView, userModel);
    }
}
