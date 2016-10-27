package com.tomlockapps.userbrowser.presenter;

import com.tomlockapps.userbrowser.view.IUserView;
import com.tomlockapps.userbrowser.viewmodel.IUserModel;
import com.tomlockapps.userbrowser.viewmodel.UserViewModel;

/**
 * Created by tomlo on 25.10.2016.
 */

public class UserPresenter extends BasePresenter<IUserView> implements IUserPresenter {

    @Override
    public void initWithModel(IUserModel userModel) {
        UserViewModel userViewModel = new UserViewModel(userModel.getAvatarUrl(), userModel.getName(), userModel.getBackgroundColor().getColorResId());

        if(isViewAttached())
            getView().showUserDetail(userViewModel);
    }

}
