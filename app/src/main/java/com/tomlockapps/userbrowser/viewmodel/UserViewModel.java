package com.tomlockapps.userbrowser.viewmodel;

/**
 * Created by tomlo on 25.10.2016.
 */

public class UserViewModel {
    private String avatarUrl;
    private String name;

    public UserViewModel(String avatarUrl, String name) {
        this.avatarUrl = avatarUrl;
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getName() {
        return name;
    }
}
