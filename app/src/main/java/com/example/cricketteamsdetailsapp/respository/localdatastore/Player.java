package com.example.cricketteamsdetailsapp.respository.localdatastore;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "player")
public class Player
{
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String teamName;
    private boolean isCaptain, isKeeper;

    public Player(String name, String teamName, boolean isCaptain, boolean isKeeper)
    {
        setName(name);
        setTeamName(teamName);
        setCaptain(isCaptain);
        setKeeper(isKeeper);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getTeamName()
    {
        return teamName;
    }

    public void setTeamName(String teamName)
    {
        this.teamName = teamName;
    }

    public boolean isCaptain()
    {
        return isCaptain;
    }

    public void setCaptain(boolean captain)
    {
        isCaptain = captain;
    }

    public boolean isKeeper()
    {
        return isKeeper;
    }

    public void setKeeper(boolean keeper)
    {
        isKeeper = keeper;
    }
}
