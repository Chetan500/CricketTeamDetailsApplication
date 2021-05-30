package com.example.cricketteamsdetailsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cricketteamsdetailsapp.respository.MatchDetailsRepository;
import com.example.cricketteamsdetailsapp.respository.localdatastore.Player;

import java.util.List;

public class MatchDetailsViewModel extends AndroidViewModel
{
    private MatchDetailsRepository matchDetailsRepository;

    public MatchDetailsViewModel(@NonNull Application application)
    {
        super(application);
        matchDetailsRepository = new MatchDetailsRepository(application);
    }

    public void fetchMatchDetailsTask(String teamName)
    {
        matchDetailsRepository.fetchMatchDetailsTask(teamName);
    }

    public LiveData<List<Player>> getAllPlayers(String teamName)
    {
        return matchDetailsRepository.getPlayersRepository().getAllPlayers(teamName);
    }

    public List<Player> getPlayers(String teamName)
    {
        return matchDetailsRepository.getPlayersRepository().getPlayers(teamName);
    }
}
