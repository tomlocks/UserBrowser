package com.tomlockapps.userbrowser.presenter;

import com.tomlockapps.userbrowser.interactor.IUsersInteractor;
import com.tomlockapps.userbrowser.view.IUsersListView;
import com.tomlockapps.userbrowser.viewmodel.IUserModel;
import com.tomlockapps.userbrowser.viewmodel.UsersViewModel;

import java.util.List;

/**
 * Created by tomlo on 25.10.2016.
 */

public class UsersListPresenter extends BasePresenter<IUsersListView> implements IUsersListPresenter {

    private IUsersInteractor interactor;

    public UsersListPresenter(IUsersInteractor interactor) {
        this.interactor = interactor;

        interactor.setListener(onFinishedListener);
    }

    @Override
    public void fetchUsers() {
        if(!interactor.fetchUsers()) {
            if(isViewAttached())
                getView().showProgress(true);
        }
    }

    @Override
    public void onUserClick(IUserModel userModel) {
        if(isViewAttached()) {
            getView().showUserDetails(userModel);
        }
    }


    private IUsersInteractor.OnFinishedListener onFinishedListener = new IUsersInteractor.OnFinishedListener() {
        @Override
        public void onSuccess(List<IUserModel> userModels) {
            UsersViewModel usersViewModel = new UsersViewModel(userModels);

            if(isViewAttached()) {
                getView().showUsers(usersViewModel);
                getView().showProgress(false);
            }
        }

        @Override
        public void onFail() {
            if(isViewAttached()) {
                getView().showFetchFailMessage();
                getView().showProgress(false);
            }
        }
    };
}
