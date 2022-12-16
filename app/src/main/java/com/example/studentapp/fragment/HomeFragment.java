package com.example.studentapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studentapp.R;
import com.example.studentapp.adapter.HomeViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {

    TabLayout homeTabLayout;
    ViewPager homeViewPager;
    View mView;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_home, container, false);

        homeTabLayout = mView.findViewById(R.id.homeTabLayout);
        homeViewPager = mView.findViewById(R.id.homeViewPager);
        HomeViewPagerAdapter homeViewPagerAdapter = new HomeViewPagerAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        homeViewPager.setAdapter(homeViewPagerAdapter);
        homeTabLayout.setupWithViewPager(homeViewPager);

        return mView;
    }
}