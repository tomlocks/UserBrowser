package com.tomlockapps.userbrowser.presenter;

import com.tomlockapps.userbrowser.view.IUserView;
import com.tomlockapps.userbrowser.viewmodel.IUserModel;
import com.tomlockapps.userbrowser.viewmodel.UserViewModel;

/**
 * Created by tomlo on 25.10.2016.
 */

public class UserPresenter implements IUserPresenter {

    private IUserView view;

    @Override
    public void initWithModel(IUserModel userModel) {
        UserViewModel userViewModel = new UserViewModel(userModel.getAvatarUrl(), userModel.getName());

        view.showUserDetail(userViewModel);
    }

    @Override
    public void init() {

    }

    @Override
    public void uninit() {

    }

    @Override
    public void setView(IUserView view) {
        this.view = view;
    }
}
