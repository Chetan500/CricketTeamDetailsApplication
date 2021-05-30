package com.example.cricketteamsdetailsapp.respository.localdatastore;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Player.class}, version = 1)
public abstract class TeamsDatabase extends RoomDatabase
{
    private static final String DATABASE_NAME = "TeamsDatabase";

    public abstract PlayerDao playerDao();

    private static volatile TeamsDatabase INSTANCE;

    public static TeamsDatabase getInstance(Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (TeamsDatabase.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context, TeamsDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
