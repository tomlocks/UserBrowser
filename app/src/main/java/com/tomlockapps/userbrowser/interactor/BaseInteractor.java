package com.tomlockapps.userbrowser.interactor;

/**
 * Created by tomlo on 25.10.2016.
 */

public abstract class BaseInteractor<K> implements IBaseInteractor<K> {
    protected K listener;

    @Override
    public void setListener(K listener) {
        this.listener = listener;
    }
}
