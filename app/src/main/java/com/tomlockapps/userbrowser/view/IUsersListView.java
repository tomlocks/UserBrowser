package com.tomlockapps.userbrowser.view;

import com.tomlockapps.userbrowser.viewmodel.IUserModel;
import com.tomlockapps.userbrowser.viewmodel.UsersViewModel;

/**
 * Interface that defines all methods that Users List view must contain.
 *
 * Created by tomlo on 25.10.2016.
 */

public interface IUsersListView {
    /**
     * Shows {@link UsersViewModel} on the view.
     *
     * @param viewModel
     */
    void showUsers(UsersViewModel viewModel);

    /**
     * Shows progress on the view, i.e. while fetching data.
     *
     * @param show true if progress should be visible, false otherwise.
     */
    void showProgress(boolean show);

    /**
     * Shows users details;
     *
     * @param userModel
     */
    void showUserDetails(IUserModel userModel);

    /**
     * Shows fail message when problem occurred during downloading data.
     */
    void showFetchFailMessage();
}
