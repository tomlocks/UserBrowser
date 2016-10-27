package com.tomlockapps.userbrowser.presenter;

/**
 * Base interface for all presenter.
 *
 * Created by tomlo on 25.10.2016.
 */

public interface IPresenter<K> {
    void init();
    void uninit();

    void attach(K view);
    void detachView();
}
