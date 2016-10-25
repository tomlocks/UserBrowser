package com.tomlockapps.userbrowser.sources.github;

import com.tomlockapps.userbrowser.sources.model.GithubUserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by tomlo on 25.10.2016.
 */

public interface GithubService {
    @GET("users")
    Call<List<GithubUserModel>> getUsers();
}
