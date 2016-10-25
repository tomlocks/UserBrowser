package com.tomlockapps.userbrowser.presenter;

import com.tomlockapps.userbrowser.view.IUsersListView;
import com.tomlockapps.userbrowser.viewmodel.IUserModel;

/**
 * Created by tomlo on 25.10.2016.
 */

public interface IUsersListPresenter extends IPresenter<IUsersListView> {
    void fetchUsers();
    void onUserClick(IUserModel userModel);
}
