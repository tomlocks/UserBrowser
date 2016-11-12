package com.tomlockapps.userbrowser.interactor;

import com.tomlockapps.userbrowser.sources.dailymotion.DailyMotionResponse;
import com.tomlockapps.userbrowser.sources.dailymotion.DailyMotionService;
import com.tomlockapps.userbrowser.sources.dailymotion.DailyMotionSource;
import com.tomlockapps.userbrowser.sources.dailymotion.DailyMotionUserModel;
import com.tomlockapps.userbrowser.sources.github.GithubService;
import com.tomlockapps.userbrowser.sources.github.GithubSource;
import com.tomlockapps.userbrowser.sources.github.GithubUserModel;
import com.tomlockapps.userbrowser.viewmodel.IUserModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Special interactor that merges all the data from multiple source before passing it to presenter. NOTE all the sources must return result otherwise the interactor will pass onFail to presenter.
 *
 * Created by tomlo on 26.10.2016.
 */

public class MultiSourceUsersInteractor extends BaseInteractor<IUsersInteractor.OnFinishedListener> implements IUsersInteractor {

    private Observable<List<IUserModel>> observable;
    private Subscription subscribe;

    public MultiSourceUsersInteractor() {
        observable = Observable.zip(GithubSource.getService().getUsers(),
                DailyMotionSource.getService().getUsers(Arrays.asList("avatar_360_url", "username")),
                (githubUserModels, dailyMotionResponse) -> {
                    List<IUserModel> iUserModels = new ArrayList<IUserModel>(githubUserModels.size() + dailyMotionResponse.list.size());

                    for (GithubUserModel githubUserModel : githubUserModels) {
                        iUserModels.add(githubUserModel);
                    }

                    for (DailyMotionUserModel dailyMotionUserModel : dailyMotionResponse.list) {
                        iUserModels.add(dailyMotionUserModel);
                    }

                    return iUserModels;
                });
    }

    @Override
    public boolean fetchUsers() {
        boolean result = true;

        if(subscribe != null)
            subscribe.unsubscribe();

        subscribe = observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(iUserModels -> {
                    listener.onSuccess(iUserModels);
                }, throwable -> listener.onFail());


        return result;
    }

    @Override
    public void uninit() {
        super.uninit();

        if(subscribe != null) {
            subscribe.unsubscribe();
        }
    }
}
