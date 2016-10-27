package com.tomlockapps.userbrowser.view;

import com.tomlockapps.userbrowser.viewmodel.UserViewModel;

/**
 * Interface that defines all methods that User Detail view must contain.
 *
 * Created by tomlo on 25.10.2016.
 */

public interface IUserView {
    /**
     * Shows user details.
     *
     * @param userViewModel
     */
    void showUserDetail(UserViewModel userViewModel);
}
