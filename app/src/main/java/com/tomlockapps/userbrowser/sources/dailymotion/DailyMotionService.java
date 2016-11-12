package com.tomlockapps.userbrowser.sources.dailymotion;

import com.tomlockapps.userbrowser.sources.github.GithubUserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by tomlo on 26.10.2016.
 */

public interface DailyMotionService {
        @GET("users")
        Observable<DailyMotionResponse> getUsers(@Query("fields[]") List<String> keywords);
}
