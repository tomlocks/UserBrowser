package com.tomlockapps.userbrowser.presenter;

import java.lang.ref.WeakReference;
import java.sql.Wrapper;

/**
 * Created by tomlo on 27.10.2016.
 */

public abstract class BasePresenter<K> implements IPresenter<K> {

    private WeakReference<K> view;

    protected final K getView() {
        return view.get();
    }

    @Override
    public void init() {

    }

    @Override
    public void uninit() {

    }

    protected final boolean isViewAttached() {
        return view.get() != null;
    }

    @Override
    public void attach(K view) {
        this.view = new WeakReference<K>(view);
    }

    @Override
    public void detachView() {
        this.view = null;
    }


}
