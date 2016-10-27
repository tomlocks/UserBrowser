package com.tomlockapps.userbrowser.presenter;

import java.lang.ref.WeakReference;
import java.sql.Wrapper;

/**
 * Base class for all presenter.
 *
 * Created by tomlo on 27.10.2016.
 */

public abstract class BasePresenter<K> implements IPresenter<K> {

    private WeakReference<K> view; // to avoid memory leaks

    protected final K getView() {
        return view.get();
    }

    /**
     * Initializes presenter. Usually called from onStart/onResume method.
     */

    @Override
    public void init() {

    }

    /**
     * Uninitializes presenter. Usually called from onStop/onPause method.
     */
    @Override
    public void uninit() {

    }

    /**
     * Method checks if view is already attached to this presenter.
     *
     * @return true if view is attached, false otherwise
     */
    protected final boolean isViewAttached() {
        return view.get() != null;
    }

    /**
     * Attaches the view to presenter. Must be called after view is created;
     *
     * @param view
     */
    @Override
    public void attach(K view) {
        this.view = new WeakReference<K>(view);
    }

    /**
     * Detaches the view from presenter. Must be called when view no more exists. It's important to call this method to destroy the reference to view and avoid memory leaks.
     *
     */
    @Override
    public void detachView() {
        this.view = null;
    }


}
