package com.tomlockapps.userbrowser.data;

import com.tomlockapps.userbrowser.data.source.UserDataRepository;

/**
 * Created by tomlo on 3/4/2018.
 */

public class RepositoryInjection {
    private static UserDataRepository userDataRepository;

    public static void setUserDataRepository(UserDataRepository userDataRepository) {
        RepositoryInjection.userDataRepository = userDataRepository;
    }

    public static UserDataRepository getUserDataRepository() {
        if(userDataRepository == null)
            throw new IllegalStateException("It seems that you forgot to set the repository. Do it in App object.");

        return userDataRepository;
    }
}
