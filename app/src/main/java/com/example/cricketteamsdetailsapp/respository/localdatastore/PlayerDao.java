package com.example.cricketteamsdetailsapp.respository.localdatastore;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlayerDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Player> playerList);

    @Query("SELECT * FROM player WHERE teamName = :teamName")
    LiveData<List<Player>> getAllPlayers(String teamName);

    @Query("SELECT * FROM player WHERE teamName = :teamName")
    List<Player> getPlayers(String teamName);
}
