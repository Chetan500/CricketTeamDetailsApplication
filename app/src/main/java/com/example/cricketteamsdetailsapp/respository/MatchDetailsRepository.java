package com.example.cricketteamsdetailsapp.respository;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.cricketteamsdetailsapp.respository.localdatastore.Player;
import com.example.cricketteamsdetailsapp.respository.localdatastore.PlayerDao;
import com.example.cricketteamsdetailsapp.respository.webservice.MatchDetailsApi;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MatchDetailsRepository
{
    public final static String BASE_URL = "https://cricket.yahoo.net/";
    private Retrofit retrofit = null;
    private Application application;
    private PlayersRepository playersRepository;

    public MatchDetailsRepository(Application application)
    {
        this.application = application;
        playersRepository = new PlayersRepository(application);
        initRetrofit();
    }

    private void initRetrofit()
    {
        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL).build();
        }
    }

    public PlayersRepository getPlayersRepository()
    {
        return playersRepository;
    }

    public void fetchMatchDetails()
    {
        MatchDetailsApi matchDetailsApi = retrofit.create(MatchDetailsApi.class);

        Call<ResponseBody> call = matchDetailsApi.getMatchDetails();
        call.enqueue(new Callback<ResponseBody>()
        {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
                try
                {
                    if (response.isSuccessful())
                    {
                        JSONObject matchDetailsJson = new JSONObject(response.body().string());
                        JSONObject teamsJson = null;
                        List<Player> playerList = new ArrayList<>();

                        if (matchDetailsJson != null && matchDetailsJson.has("Teams"))
                        {
                            teamsJson = matchDetailsJson.getJSONObject("Teams");

                            if (teamsJson.has("4"))
                            {
                                String teamName = (teamsJson.getJSONObject("4").has("Name_Full") ? teamsJson.getJSONObject("4").getString("Name_Full") : "");
                                JSONObject indianPlayersJson = (teamsJson.getJSONObject("4").has("Players") ? teamsJson.getJSONObject("4").getJSONObject("Players") : null);
                                Iterator<String> iterator = indianPlayersJson.keys();

                                while (iterator.hasNext())
                                {
                                    String playerId = iterator.next(), playerName = "";
                                    JSONObject playerJson = null;
                                    boolean isCaptain, isKeeper;

                                    if (playerId.equals("3852") || playerId.equals("3722") || playerId.equals("66818") || playerId.equals("4176") ||
                                            playerId.equals("3632") || playerId.equals("4532") || playerId.equals("63751") || playerId.equals("63187") ||
                                            playerId.equals("9844") || playerId.equals("5132") || playerId.equals("65867"))
                                    {
                                        playerJson = indianPlayersJson.getJSONObject(playerId);
                                        playerName = (playerJson.has("Name_Full") ? playerJson.getString("Name_Full") : "Player Name");
                                        isCaptain = (playerJson.has("Iscaptain") ? playerJson.getBoolean("Iscaptain") : false);
                                        isKeeper = (playerJson.has("Iskeeper") ? playerJson.getBoolean("Iskeeper") : false);

                                        if (isCaptain)
                                        {
                                            playerName = playerName.concat(" (c)");
                                        }

                                        if (isKeeper)
                                        {
                                            playerName = playerName.concat(" (wk)");
                                        }

                                        playerList.add(new Player(playerName, teamName, isCaptain, isKeeper));
                                    }
                                }
                            }

                            if (teamsJson.has("5"))
                            {
                                String teamName = (teamsJson.getJSONObject("5").has("Name_Full") ? teamsJson.getJSONObject("5").getString("Name_Full") : "");
                                JSONObject indianPlayersJson = (teamsJson.getJSONObject("5").has("Players") ? teamsJson.getJSONObject("5").getJSONObject("Players") : null);
                                Iterator<String> iterator = indianPlayersJson.keys();

                                while (iterator.hasNext())
                                {
                                    String playerId = iterator.next(), playerName = "";
                                    JSONObject playerJson = null;
                                    boolean isCaptain, isKeeper;

                                    if (playerId.equals("4964") || playerId.equals("57594") || playerId.equals("4330") || playerId.equals("3752") ||
                                            playerId.equals("10167") || playerId.equals("10172") || playerId.equals("57903") || playerId.equals("11703") ||
                                            playerId.equals("11706") || playerId.equals("60544") || playerId.equals("4338"))
                                    {
                                        playerJson = indianPlayersJson.getJSONObject(playerId);
                                        playerName = (playerJson.has("Name_Full") ? playerJson.getString("Name_Full") : "Player Name");
                                        isCaptain = (playerJson.has("Iscaptain") ? playerJson.getBoolean("Iscaptain") : false);
                                        isKeeper = (playerJson.has("Iskeeper") ? playerJson.getBoolean("Iskeeper") : false);

                                        if (isCaptain)
                                        {
                                            playerName = playerName.concat(" (c)");
                                        }

                                        if (isKeeper)
                                        {
                                            playerName = playerName.concat(" (wk)");
                                        }

                                        playerList.add(new Player(playerName, teamName, isCaptain, isKeeper));
                                    }
                                }
                            }

                            playersRepository.insert(playerList);
                        }
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t)
            {
                Toast.makeText(application, "Unable to fetch Match Details, Getting Failure!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void fetchMatchDetailsTask(String teamName)
    {
        new FetchMatchDetailsAsyncTask(playersRepository).execute(teamName);
    }

    private class FetchMatchDetailsAsyncTask extends AsyncTask<String, Void, Void>
    {
        private PlayersRepository playersRepository;

        public FetchMatchDetailsAsyncTask(PlayersRepository playersRepository)
        {
            this.playersRepository = playersRepository;
        }

        @Override
        protected Void doInBackground(String... teamName)
        {
            List<Player> playerList = playersRepository.getPlayers(teamName[0]);
            if (playerList.size() == 0)
            {
                fetchMatchDetails();
            }
            return null;
        }
    }
}
