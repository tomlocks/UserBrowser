package com.tomlockapps.userbrowser.interactor;

/**
 * Base class for all interactors. Defines an interface to communicate between presenter and interactor.
 *  <pre>
 * Created by tomlo on 25.10.2016.
 * </pre>
 */

public abstract class BaseInteractor<K> implements IBaseInteractor<K> {
    protected K listener;

    @Override
    public void setListener(K listener) {
        this.listener = listener;
    }
}
