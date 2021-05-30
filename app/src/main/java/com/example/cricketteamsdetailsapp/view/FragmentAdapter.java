package com.example.cricketteamsdetailsapp.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter
{
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle)
    {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position)
    {
        TeamsDetailsFragment teamsDetailsFragment = new TeamsDetailsFragment();
        Bundle bundle = new Bundle();

        switch (position)
        {
            case 0:
                bundle.putString("Team Name", "India");
                break;
            case 1:
                bundle.putString("Team Name", "New Zealand");
                break;
            default:
                break;
        }

        teamsDetailsFragment.setArguments(bundle);
        return teamsDetailsFragment;
    }

    @Override
    public int getItemCount()
    {
        return 2;
    }
}
