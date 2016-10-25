package com.tomlockapps.userbrowser.interactor;

import com.tomlockapps.userbrowser.viewmodel.IUserModel;

import java.util.List;

/**
 * Created by tomlo on 25.10.2016.
 */

public interface IUsersInteractor extends IBaseInteractor<IUsersInteractor.OnFinishedListener>{
    /**
     * Fetches the users from any source.
     *
     * @return true if users are fetched immediately, false otherwise
     */
    boolean fetchUsers();

    interface OnFinishedListener {
        void onSuccess(List<IUserModel> userModels);
        void onFail();
    }
}
