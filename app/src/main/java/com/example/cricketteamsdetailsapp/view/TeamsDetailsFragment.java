package com.example.cricketteamsdetailsapp.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cricketteamsdetailsapp.R;
import com.example.cricketteamsdetailsapp.respository.localdatastore.Player;
import com.example.cricketteamsdetailsapp.viewmodel.MatchDetailsViewModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamsDetailsFragment extends Fragment
{
    private View view;
    private RecyclerView rvPlayerNamesList;
    private PlayerNameItemsAdapter playerNameItemsAdapter;
    private MatchDetailsViewModel matchDetailsViewModel = null;
    private List<Player> itemsList = new ArrayList<>();
    private String teamName = "";

    public TeamsDetailsFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        matchDetailsViewModel = new ViewModelProvider(getActivity()).get(MatchDetailsViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_teams_details, container, false);
        Bundle bundle = getArguments();

        if (bundle != null)
        {
            teamName = bundle.getString("Team Name", "");
        }

        init(view);
        showPlayersList();
        return view;
    }

    private void init(View view)
    {
        rvPlayerNamesList = (RecyclerView) view.findViewById(R.id.tvPlayerNamesList);
        playerNameItemsAdapter = new PlayerNameItemsAdapter();
        playerNameItemsAdapter.updateItemsList(itemsList);
        rvPlayerNamesList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvPlayerNamesList.setAdapter(playerNameItemsAdapter);
    }

    private void showPlayersList()
    {
        LiveData<List<Player>> ldPlayersList = matchDetailsViewModel.getAllPlayers(teamName);

        ldPlayersList.observe(getActivity(), new Observer<List<Player>>()
        {
            @Override
            public void onChanged(List<Player> playerList)
            {
                playerNameItemsAdapter.updateItemsList(playerList);
            }
        });

        matchDetailsViewModel.fetchMatchDetailsTask(teamName);
    }
}