package com.tomlockapps.userbrowser.data.source.local.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.tomlockapps.userbrowser.data.source.local.room.model.RoomUserModel;

import java.util.List;

/**
 * Created by tomlo on 06/03/2018.
 */

@Dao
public interface UsersDao {
    @Query("SELECT * FROM roomusermodel")
    List<RoomUserModel> getAll();
    @Insert
    void insertAll(List<RoomUserModel> users);
    @Insert
    void insert(RoomUserModel user);
    @Query("DELETE FROM roomusermodel")
    void deleteAll();
}
