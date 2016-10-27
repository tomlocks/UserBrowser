package com.tomlockapps.userbrowser.viewmodel;

/**
 * This method contains all the data that needs to be shown on Users Detail view.
 *
 * Created by tomlo on 25.10.2016.
 */

public class UserViewModel {
    private String avatarUrl;
    private String name;
    private int backgroundColorResId;

    public UserViewModel(String avatarUrl, String name, int backgroundColorResId) {
        this.avatarUrl = avatarUrl;
        this.name = name;
        this.backgroundColorResId = backgroundColorResId;
    }

    public int getBackgroundColorResId() {
        return backgroundColorResId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getName() {
        return name;
    }
}
