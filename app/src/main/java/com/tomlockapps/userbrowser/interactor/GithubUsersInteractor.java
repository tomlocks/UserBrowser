package com.tomlockapps.userbrowser.interactor;

import com.tomlockapps.userbrowser.viewmodel.IUserModel;
import com.tomlockapps.userbrowser.sources.github.GithubService;
import com.tomlockapps.userbrowser.sources.github.GithubUserModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Interactor responsible for fetching user datas from Github.
 *
 * Created by tomlo on 25.10.2016.
 */

public class GithubUsersInteractor extends BaseInteractor<IUsersInteractor.OnFinishedListener> implements IUsersInteractor {

    private GithubService githubService;

    public GithubUsersInteractor(GithubService githubService) {
        this.githubService = githubService;
    }

    @Override
    public boolean fetchUsers() {
        fetchGithubUsers();

        return false;
    }


    private void fetchGithubUsers() {
        Call<List<GithubUserModel>> call = githubService.getUsers();

        call.enqueue(new Callback<List<GithubUserModel>>() {
            @Override
            public void onResponse(Call<List<GithubUserModel>> call, Response<List<GithubUserModel>> response) {
                List<GithubUserModel> githubUserModels = response.body();

                List<IUserModel> users = new ArrayList<IUserModel>(githubUserModels.size());
                users.addAll(githubUserModels);

                listener.onSuccess(users);
            }

            @Override
            public void onFailure(Call<List<GithubUserModel>> call, Throwable t) {
                listener.onFail();
            }
        });


    }
}
