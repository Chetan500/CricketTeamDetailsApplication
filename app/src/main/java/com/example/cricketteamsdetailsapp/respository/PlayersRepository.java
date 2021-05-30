package com.example.cricketteamsdetailsapp.respository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.cricketteamsdetailsapp.respository.localdatastore.Player;
import com.example.cricketteamsdetailsapp.respository.localdatastore.PlayerDao;
import com.example.cricketteamsdetailsapp.respository.localdatastore.TeamsDatabase;

import java.util.ArrayList;
import java.util.List;

public class PlayersRepository
{
    private TeamsDatabase teamsDatabase;
    private PlayerDao playerDao;
    protected List<Player> playersList = new ArrayList<>();

    public PlayersRepository(Application application)
    {
        teamsDatabase = TeamsDatabase.getInstance(application);
        playerDao = teamsDatabase.playerDao();
    }

    public void insert(List<Player> playerList)
    {
        new InsertAsyncTask(playerDao).execute(playerList);
    }

    public LiveData<List<Player>> getAllPlayers(String teamName)
    {
        return playerDao.getAllPlayers(teamName);
    }

    public List<Player> getPlayers(String teamName)
    {
        return playerDao.getPlayers(teamName);
    }

    public List<Player> getPlayersList()
    {
        return playersList;
    }

    public void setPlayersList(List<Player> playersList)
    {
        this.playersList = playersList;
    }

    private class InsertAsyncTask extends AsyncTask<List<Player>, Void, Void>
    {
        private PlayerDao playerDao;

        public InsertAsyncTask(PlayerDao playerDao)
        {
            this.playerDao = playerDao;
        }

        @Override
        protected Void doInBackground(List<Player>... playerList)
        {
            playerDao.insert(playerList[0]);
            return null;
        }
    }
}
