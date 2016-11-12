package com.tomlockapps.userbrowser.presenter;

import com.tomlockapps.userbrowser.interactor.IUsersInteractor;
import com.tomlockapps.userbrowser.view.IUsersListView;
import com.tomlockapps.userbrowser.viewmodel.IUserModel;
import com.tomlockapps.userbrowser.viewmodel.UsersViewModel;

import java.util.List;
import rx.Observer;

/**
 * Created by tomlo on 25.10.2016.
 */

public class UsersListPresenter extends BasePresenter<IUsersListView> implements IUsersListPresenter {

    private IUsersInteractor interactor;

    public UsersListPresenter(IUsersInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void init() {
        super.init();

        interactor.init();
    }

    @Override
    public void uninit() {
        super.uninit();

        interactor.uninit();
    }

    @Override
    public void fetchUsers() {
        if(!interactor.fetch(observer)) {
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


    private Observer<List<IUserModel>> observer = new Observer<List<IUserModel>>() {
        @Override
        public void onCompleted() {
            if(isViewAttached()) {
                getView().showProgress(false);
            }
        }

        @Override
        public void onError(Throwable e) {
            if(isViewAttached()) {
                getView().showFetchFailMessage();
                getView().showProgress(false);
            }
        }

        @Override
        public void onNext(List<IUserModel> iUserModels) {
            UsersViewModel usersViewModel = new UsersViewModel(iUserModels);

            if(isViewAttached()) {
                getView().showUsers(usersViewModel);
            }
        }
    };

}
