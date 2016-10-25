package com.tomlockapps.userbrowser.presenter;

import com.tomlockapps.userbrowser.action.IUsersListActions;
import com.tomlockapps.userbrowser.interactor.IUsersInteractor;
import com.tomlockapps.userbrowser.view.IUsersListView;
import com.tomlockapps.userbrowser.viewmodel.IUserModel;
import com.tomlockapps.userbrowser.viewmodel.UsersViewModel;

import java.util.List;

/**
 * Created by tomlo on 25.10.2016.
 */

public class UsersListPresenter implements IUsersListPresenter {

    private IUsersListView view;
    private IUsersInteractor interactor;
    private IUsersListActions actions;

    public UsersListPresenter(IUsersListView view, IUsersListActions actions, IUsersInteractor interactor) {
        this.interactor = interactor;
        this.view = view;
        this.actions = actions;

        interactor.setListener(onFinishedListener);
    }

    @Override
    public void fetchUsers() {
        if(!interactor.fetchUsers()) {
            view.showProgress(true);
        }
    }

    @Override
    public void onUserClick(IUserModel userModel) {
        actions.showUserDetails(userModel);
    }

    @Override
    public void init() {

    }

    @Override
    public void uninit() {

    }

    @Override
    public void setView(IUsersListView view) {
        this.view = view;
    }

    private IUsersInteractor.OnFinishedListener onFinishedListener = new IUsersInteractor.OnFinishedListener() {
        @Override
        public void onSuccess(List<IUserModel> userModels) {
            UsersViewModel usersViewModel = new UsersViewModel(userModels);
            view.showUsers(usersViewModel);

            view.showProgress(false);
        }

        @Override
        public void onFail() {
            view.showFailMessage();
            view.showProgress(false);
        }
    };
}
