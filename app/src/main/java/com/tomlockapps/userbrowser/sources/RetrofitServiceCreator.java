package com.tomlockapps.userbrowser.sources;

import com.tomlockapps.userbrowser.sources.github.GithubService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tomlo on 06.11.2016.
 */

public class RetrofitServiceCreator {
    public static <T> T createRetrofitService(final Class<T> clazz, String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(clazz);
    }
}
