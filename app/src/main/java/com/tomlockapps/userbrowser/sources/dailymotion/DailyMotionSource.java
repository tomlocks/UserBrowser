package com.tomlockapps.userbrowser.sources.dailymotion;

import com.tomlockapps.userbrowser.sources.RetrofitServiceCreator;
import com.tomlockapps.userbrowser.sources.github.GithubService;
import com.tomlockapps.userbrowser.sources.github.GithubSource;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Source of all dailymotion data.
 *
 * Created by tomlo on 25.10.2016.
 */

public class DailyMotionSource {
    private static final String API = "https://api.dailymotion.com";
    private static DailyMotionSource INSTANCE;

    private DailyMotionService service;

    public static DailyMotionService getService() {
        if(INSTANCE == null)
            INSTANCE = new DailyMotionSource();

        return INSTANCE.service;
    }

    private DailyMotionSource() {
        service = RetrofitServiceCreator.createRetrofitService(DailyMotionService.class, API);
    }
}
