package com.tomlockapps.userbrowser.presenter.mock;

import com.tomlockapps.userbrowser.interactor.IUsersInteractor;
import com.tomlockapps.userbrowser.viewmodel.IUserModel;
import com.tomlockapps.userbrowser.viewmodel.UserColor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomlo on 27.10.2016.
 */

public class MockedNetworkUsersInteractor implements IUsersInteractor {

    private OnFinishedListener onFinishedListener;

    private boolean success;

    public MockedNetworkUsersInteractor(boolean success) {
        this.success = success;
    }

    @Override
    public boolean fetch() {
        if (success) {
            final List<IUserModel> userModels = new ArrayList<>();

            userModels.add(new MockedUser("Adam", "www.example.org", UserColor.BLUE));
            userModels.add(new MockedUser("Buba", "www.example2.org", UserColor.LIME));

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    onFinishedListener.onSuccess(userModels);
                }
            });

            t.start();
        } else {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    onFinishedListener.onFail();
                }
            });

            t.start();
        }


        return false;
    }

    @Override
    public void setListener(OnFinishedListener listener) {
        onFinishedListener = listener;
    }
}
