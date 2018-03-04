package com.tomlockapps.userbrowser.presenter.mock;

import com.tomlockapps.userbrowser.interactor.IUsersInteractor;
import com.tomlockapps.userbrowser.data.IUserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomlo on 27.10.2016.
 */

public class MockedImmediateUsersInteractor implements IUsersInteractor {

    private OnFinishedListener onFinishedListener;

    @Override
    public boolean fetch() {
        List<IUserModel> userModels = new ArrayList<>();

        userModels.add(new MockedUser("Adam", "www.example.org", UserColor.BLUE));
        userModels.add(new MockedUser("Buba", "www.example2.org", UserColor.LIME));

        onFinishedListener.onSuccess(userModels);

        return true;
    }

    @Override
    public void setListener(OnFinishedListener listener) {
        onFinishedListener = listener;
    }
}
