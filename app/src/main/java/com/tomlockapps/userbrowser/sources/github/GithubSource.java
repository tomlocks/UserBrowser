package com.tomlockapps.userbrowser.sources.github;

import com.tomlockapps.userbrowser.sources.RetrofitServiceCreator;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tomlo on 25.10.2016.
 */

public class GithubSource {
    private static final String API = "https://api.github.com";
    private static GithubSource INSTANCE;

    private GithubService service;

    public static GithubService getService() {
        if(INSTANCE == null)
            INSTANCE = new GithubSource();

        return INSTANCE.service;
    }

    private GithubSource() {
        service = RetrofitServiceCreator.createRetrofitService(GithubService.class, API);
    }
}
