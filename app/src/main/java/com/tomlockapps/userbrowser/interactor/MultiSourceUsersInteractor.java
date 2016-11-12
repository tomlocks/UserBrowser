package com.tomlockapps.userbrowser.interactor;

import com.tomlockapps.userbrowser.sources.dailymotion.DailyMotionSource;
import com.tomlockapps.userbrowser.sources.github.GithubSource;
import com.tomlockapps.userbrowser.viewmodel.IUserModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Special interactor that merges all the data from multiple source before passing it to presenter. NOTE all the sources must return result otherwise the interactor will pass onFail to presenter.
 * <p>
 * Created by tomlo on 26.10.2016.
 */

public class MultiSourceUsersInteractor extends BaseInteractor implements IUsersInteractor {

    private Observable<List<IUserModel>> observable;
    private Subscription subscribe;

    public MultiSourceUsersInteractor() {
        observable = Observable.zip(GithubSource.getService().getUsers(),
                DailyMotionSource.getService().getUsers(Arrays.asList("avatar_360_url", "username")),
                (githubUserModels, dailyMotionResponse) -> {
                    List<IUserModel> iUserModels = new ArrayList<IUserModel>(githubUserModels.size() + dailyMotionResponse.list.size());

                    iUserModels.addAll(githubUserModels);
                    iUserModels.addAll(dailyMotionResponse.list);

                    return iUserModels;
                });
    }

    @Override
    public boolean fetch(Observer<List<IUserModel>> observer) {
        if (subscribe != null)
            subscribe.unsubscribe();

        subscribe = observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

        return false;
    }

    @Override
    public void uninit() {
        super.uninit();

        if (subscribe != null) {
            subscribe.unsubscribe();
        }
    }
}
