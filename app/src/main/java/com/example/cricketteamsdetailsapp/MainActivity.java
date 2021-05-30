package com.example.cricketteamsdetailsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.cricketteamsdetailsapp.view.FragmentAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity
{
    TabLayout tabTeamsName;
    ViewPager2 vpTeamsDetails;
    FragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setListeners();
    }

    private void init()
    {
        tabTeamsName = (TabLayout) findViewById(R.id.tabTeamsName);
        vpTeamsDetails = (ViewPager2) findViewById(R.id.vpTeamsDetails);
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), getLifecycle());
        vpTeamsDetails.setAdapter(fragmentAdapter);
        tabTeamsName.addTab(tabTeamsName.newTab().setText("INDIA"));
        tabTeamsName.addTab(tabTeamsName.newTab().setText("NEWZEALAND"));
    }

    private void setListeners()
    {
        tabTeamsName.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                vpTeamsDetails.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });

        vpTeamsDetails.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position)
            {
                tabTeamsName.selectTab(tabTeamsName.getTabAt(position));
            }
        });
    }
}