package com.tomlockapps.userbrowser.data.source.remote.github;

import com.tomlockapps.userbrowser.data.source.remote.github.model.GithubUserModel;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by tomlo on 25.10.2016.
 */

public interface GithubService {
    @GET("users")
    Observable<List<GithubUserModel>> getUsers();
}
