package com.example.studentapp.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.example.studentapp.home.FollowerPostsFragment;
import com.example.studentapp.home.TimeTableFragment;

public class HomeViewPagerAdapter extends FragmentStatePagerAdapter {
    public HomeViewPagerAdapter(@NonNull FragmentManager fragmentManager, int behavior) {
        super(fragmentManager, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TimeTableFragment();
            case 1:
                return new FollowerPostsFragment();
            default:
                return new TimeTableFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Thời khóa biểu";
            case 1:
                return "Bài đăng theo dõi";
            default:
                return "Thời khóa biểu";
        }
    }
}
