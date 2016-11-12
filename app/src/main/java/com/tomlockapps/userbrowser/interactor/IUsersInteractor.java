package com.tomlockapps.userbrowser.interactor;

import com.tomlockapps.userbrowser.viewmodel.IUserModel;

import java.util.List;

import rx.Observer;

/**
 * Interface which defines methods that every UsersInteractor must include.
 *
 * Created by tomlo on 25.10.2016.
 */

public interface IUsersInteractor extends IBaseInteractor{
    /**
     * Fetches the users from source.
     *
     * @return true if users are fetched immediately, false otherwise
     */
    boolean fetch(Observer<List<IUserModel>> observer);

    interface OnFinishedListener {
        void onSuccess(List<IUserModel> userModels);
        void onFail();
    }
}
