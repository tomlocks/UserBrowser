package com.tomlockapps.userbrowser.view;

import com.tomlockapps.userbrowser.viewmodel.UsersViewModel;

/**
 * Created by tomlo on 25.10.2016.
 */

public interface IUsersListView {
    void showUsers(UsersViewModel viewModel);
    void showProgress(boolean show);

    void showFailMessage();
}
