package com.tomlockapps.userbrowser.presenter;

import com.tomlockapps.userbrowser.view.IUserView;
import com.tomlockapps.userbrowser.viewmodel.IUserModel;

/**
 * Created by tomlo on 25.10.2016.
 */

public interface IUserPresenter extends IPresenter<IUserView> {
    void initWithModel(IUserModel userModel);
}
