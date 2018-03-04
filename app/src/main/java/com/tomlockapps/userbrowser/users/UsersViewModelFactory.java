package com.tomlockapps.userbrowser.users;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.tomlockapps.userbrowser.base.providers.IResourceProvider;
import com.tomlockapps.userbrowser.base.schedulers.BaseSchedulerProvider;
import com.tomlockapps.userbrowser.data.source.UserDataRepository;

/**
 * Created by walczewski on 04.03.2018.
 */

public class UsersViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final UserDataRepository repository;
    private final BaseSchedulerProvider baseSchedulerProvider;
    private final IResourceProvider resourceProvider;

    public UsersViewModelFactory(UserDataRepository repository, BaseSchedulerProvider baseSchedulerProvider, IResourceProvider resourceProvider) {
        this.repository = repository;
        this.baseSchedulerProvider = baseSchedulerProvider;
        this.resourceProvider = resourceProvider;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new UsersViewModel(repository, baseSchedulerProvider, resourceProvider);
    }
}
