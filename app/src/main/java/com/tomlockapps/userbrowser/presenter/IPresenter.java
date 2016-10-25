package com.tomlockapps.userbrowser.presenter;

/**
 * Created by tomlo on 25.10.2016.
 */

public interface IPresenter<K> {
    void init();
    void uninit();

    void setView(K view);
}
