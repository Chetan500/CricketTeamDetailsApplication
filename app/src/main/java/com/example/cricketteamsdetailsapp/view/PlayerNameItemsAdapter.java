package com.example.cricketteamsdetailsapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cricketteamsdetailsapp.R;
import com.example.cricketteamsdetailsapp.respository.localdatastore.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerNameItemsAdapter extends RecyclerView.Adapter<PlayerNameItemsAdapter.PlayerNameItemsViewHolder>
{
    private List<Player> itemsList = new ArrayList<>();

    public void updateItemsList(List<Player> itemsList)
    {
        this.itemsList = itemsList;
        notifyDataSetChanged();
    }

    //public

    public static class PlayerNameItemsViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tvPlayerName;

        public PlayerNameItemsViewHolder(@NonNull View itemView)
        {
            super(itemView);
            init(itemView);
        }

        private void init(View itemView)
        {
            tvPlayerName = (TextView) itemView.findViewById(R.id.tvPlayerName);
        }

        public TextView getTvPlayerName()
        {
            return tvPlayerName;
        }
    }
    @NonNull
    @Override
    public PlayerNameItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_player_name, parent, false);
        return new PlayerNameItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerNameItemsViewHolder holder, int position)
    {
        Player player = itemsList.get(position);
        holder.getTvPlayerName().setText(player.getName());
    }

    @Override
    public int getItemCount()
    {
        return (itemsList != null ? itemsList.size() : 0);
    }
}
