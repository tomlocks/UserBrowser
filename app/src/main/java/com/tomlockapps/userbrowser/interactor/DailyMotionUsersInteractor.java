package com.tomlockapps.userbrowser.interactor;

import com.tomlockapps.userbrowser.sources.dailymotion.DailyMotionResponse;
import com.tomlockapps.userbrowser.sources.dailymotion.DailyMotionService;
import com.tomlockapps.userbrowser.sources.dailymotion.DailyMotionSource;
import com.tomlockapps.userbrowser.viewmodel.IUserModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tomlo on 26.10.2016.
 */

public class DailyMotionUsersInteractor extends BaseInteractor<IUsersInteractor.OnFinishedListener> implements IUsersInteractor  {

    private DailyMotionService service;

    public DailyMotionUsersInteractor(DailyMotionService dailyMotionService) {
        this.service = dailyMotionService;
    }

    @Override
    public boolean fetchUsers() {
        Call<DailyMotionResponse> call = service.getUsers(Arrays.asList("avatar_360_url", "username"));

        call.enqueue(new Callback<DailyMotionResponse>() {
            @Override
            public void onResponse(Call<DailyMotionResponse> call, Response<DailyMotionResponse> response) {
                DailyMotionResponse dailyMotionResponse = response.body();

                List<IUserModel> users = new ArrayList<IUserModel>(dailyMotionResponse.list);

                listener.onSuccess(users);
            }

            @Override
            public void onFailure(Call<DailyMotionResponse> call, Throwable t) {
                listener.onFail();
            }
        });

        return false;
    }
}
