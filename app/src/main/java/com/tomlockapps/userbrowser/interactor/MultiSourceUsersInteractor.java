package com.tomlockapps.userbrowser.interactor;

import com.tomlockapps.userbrowser.viewmodel.IUserModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by tomlo on 26.10.2016.
 */

public class MultiSourceUsersInteractor extends BaseInteractor<IUsersInteractor.OnFinishedListener> implements IUsersInteractor {

    private List<IUsersInteractor> usersInteractorList = new ArrayList<>();
    private List<IUserModel> mergedModels = new ArrayList<>();

    private int successResponseCount;

    public MultiSourceUsersInteractor(IUsersInteractor... inters) {
        Collections.addAll(this.usersInteractorList, inters);

        for (IUsersInteractor usersInteractor : usersInteractorList) {
            usersInteractor.setListener(onFinishedListener);
        }

    }

    @Override
    public boolean fetchUsers() {
        boolean result = true;

        successResponseCount = 0;
        mergedModels.clear();

        for (IUsersInteractor iUsersInteractor : usersInteractorList) {
            result = result & iUsersInteractor.fetchUsers();
        }

        return result;
    }

    private OnFinishedListener onFinishedListener = new OnFinishedListener() {
        @Override
        public void onSuccess(List<IUserModel> userModels) {
            successResponseCount++;

            mergedModels.addAll(userModels);

            if(successResponseCount == usersInteractorList.size()) {
                listener.onSuccess(mergedModels);
            }
        }

        @Override
        public void onFail() {
            successResponseCount = 0;

            listener.onFail();
        }
    };
}
