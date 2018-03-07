package com.tomlockapps.userbrowser.data.source.local.room;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

import com.tomlockapps.userbrowser.base.providers.ITimeProvider;
import com.tomlockapps.userbrowser.data.IUserModel;
import com.tomlockapps.userbrowser.data.source.CacheDataSource;
import com.tomlockapps.userbrowser.data.source.local.room.model.RoomUserModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Very simple cache implementation.
 *
 * Created by tomlo on 06/03/2018.
 */

public class RoomUsersRepository implements CacheDataSource {

    public static final long CACHE_MAX_AGE = TimeUnit.MINUTES.toMillis(1);

    //todo auto cache removing

    private static UsersDatabase usersDatabase;

    private final Context context;
    private final ITimeProvider timeProvider;

    public RoomUsersRepository(Context context, ITimeProvider timeProvider) {
        this.context = context;
        this.timeProvider = timeProvider;
    }

    /**
     * Note: You should follow the singleton design pattern when instantiating an AppDatabase object, as each RoomDatabase instance is fairly expensive, and you rarely need access to multiple instances.
     *
     * @param context
     * @return
     */
    public static UsersDatabase getInstance(Context context) {
        if(usersDatabase == null) {
            usersDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    UsersDatabase.class, "users-database").build();
        }

        return usersDatabase;
    }

    @Override
    public Observable<IUserModel> getUsers() {
        return Observable.fromCallable(() -> getInstance(context).userDao().getAll()).flatMap(Observable::from).filter(roomUserModel -> roomUserModel.getTimestamp() > timeProvider.getCurrentTime() - CACHE_MAX_AGE).map(userModel -> (IUserModel)userModel);
    }

    @Override
    public void refreshUsers() {
        getInstance(context).userDao().deleteAll();
    }

    @Override
    public void insertData(List<IUserModel> modelList) {
        Observable.from(modelList).map(this::getRoomUserModel)
                .toList().forEach(roomUserModels -> getInstance(context).userDao().insertAll(roomUserModels));
    }

    @Override
    public void insert(IUserModel model) {
        getInstance(context).userDao().insert(getRoomUserModel(model));
    }

    @NonNull
    private RoomUserModel getRoomUserModel(IUserModel userModel) {
        return new RoomUserModel(userModel.getAvatarUrl(), userModel.getName(), userModel.getSourceType(), timeProvider.getCurrentTime());
    }
}
