package com.tomlockapps.userbrowser.users;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.os.Bundle;

import com.tomlockapps.userbrowser.R;
import com.tomlockapps.userbrowser.base.viewmodel.SavableViewModel;
import com.tomlockapps.userbrowser.base.providers.IResourceProvider;
import com.tomlockapps.userbrowser.data.IUserModel;
import com.tomlockapps.userbrowser.data.source.UserDataRepository;
import com.tomlockapps.userbrowser.base.schedulers.BaseSchedulerProvider;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import rx.Subscription;

/**
 * Created by tomlo on 3/4/2018.
 */

public class UsersViewModel extends SavableViewModel {

    public static final String EXTRA_USER_MODELS = "UsersViewModel.Extra.USERS";

    public final ObservableList<IUserModel> models = new ObservableArrayList<>();
    public final ObservableBoolean dataLoading = new ObservableBoolean();
    public final ObservableField<String> messageString = new ObservableField<>();

    private Subscription subscription;

    private final UserDataRepository repository;
    private final BaseSchedulerProvider baseSchedulerProvider;
    private final IResourceProvider resourceProvider;
    private WeakReference<UsersNavigator> usersNavigator;


    public UsersViewModel(UserDataRepository repository, BaseSchedulerProvider baseSchedulerProvider, IResourceProvider resourceProvider) {
        this.repository = repository;
        this.baseSchedulerProvider = baseSchedulerProvider;
        this.resourceProvider = resourceProvider;
    }

    public void setUsersNavigator(UsersNavigator usersNavigator) {
        this.usersNavigator = new WeakReference<>(usersNavigator);
    }

    public void fetchTasks(final boolean useCache) {
        unsubscribe();

        dataLoading.set(true);
        messageString.set(null);

        if(!useCache)
            repository.refreshUsers();

        subscription = repository.getUsers().toList()
                .subscribeOn(baseSchedulerProvider.computation())
                .observeOn(baseSchedulerProvider.ui())
                .subscribe(iUserModels -> {
                    models.clear();
                    models.addAll(iUserModels);
                    if(!useCache)
                        messageString.set(resourceProvider.getString(R.string.fetching_success));
                }, throwable -> {
                    messageString.set(resourceProvider.getString(R.string.fetching_error));
                    dataLoading.set(false);
                    } , () -> dataLoading.set(false));

    }

    private void unsubscribe() {
        if(subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }

    public void onItemClick(IUserModel model) {
        int position = models.indexOf(model);

        usersNavigator.get().showUserDetails(model, position);
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        unsubscribe(); //todo is it a right place to do it? Double check that when destroy activity option is selected on device.
    }

    @Override
    public void onSaveInstanceState(Bundle b) {
        super.onSaveInstanceState(b);

        if(models.size() > 0)
            b.putParcelableArrayList(EXTRA_USER_MODELS, new ArrayList<>(models));
    }

    @Override
    public void onRestoreInstanceState(Bundle b) {
        super.onRestoreInstanceState(b);

        if(models.isEmpty()) {
            if(b.containsKey(EXTRA_USER_MODELS)) {
                ArrayList<IUserModel> extraUserModels = b.getParcelableArrayList(EXTRA_USER_MODELS);
                models.addAll(extraUserModels);
            } else {
                fetchTasks(true);
            }
        }
    }
}
