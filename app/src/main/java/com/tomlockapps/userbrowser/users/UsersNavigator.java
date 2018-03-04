package com.tomlockapps.userbrowser.users;

import com.tomlockapps.userbrowser.data.IUserModel;

/**
 * Created by tomlo on 3/4/2018.
 */

public interface UsersNavigator {
    void showUserDetails(IUserModel userModel, int position);
}