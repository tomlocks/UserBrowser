package com.tomlockapps.userbrowser.data.source.local.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.tomlockapps.userbrowser.data.source.local.room.model.RoomUserModel;

/**
 * Created by tomlo on 06/03/2018.
 */

@Database(entities = {RoomUserModel.class}, version = 1)
public abstract class UsersDatabase extends RoomDatabase {
    public abstract UsersDao userDao();
}
