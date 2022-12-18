package com.example.studentapp.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.studentapp.MainActivity;
import com.example.studentapp.R;
import com.example.studentapp.model.User;


public class ProfileFragment extends Fragment {
    // Resources
    private View mView;
    private MainActivity mMainActivity;
    private LinearLayout loginLayoutProfileHeading, logoutLayoutProfileHeading;
    private LinearLayout loginLayoutProfileContent, logoutLayoutProfileContent;
    private Button btnLogin, btnRegister;
    TextView tvClasses, tvFollowing, tvAccountSetting, tvChangePassword, tvLogout;
    // Object Class
    private User currentUser;

    public ProfileFragment() {
        currentUser = null;
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_profile, container, false);
        mMainActivity = (MainActivity) getActivity();
        // Check login/logout ==> display corresponding view

        loginLayoutProfileHeading = mView.findViewById(R.id.loginLayoutProfileHeading);
        logoutLayoutProfileHeading = mView.findViewById(R.id.logoutLayoutProfileHeading);
        loginLayoutProfileContent = mView.findViewById(R.id.loginLayoutProfileContent);
        logoutLayoutProfileContent = mView.findViewById(R.id.logoutLayoutProfileContent);
        setUserInteractUI(); // Tùy theo đăng nhập hoặc chưa đăng nhập để hiện UI tương ứng

        tvClasses = mView.findViewById(R.id.tvClasses);
        tvFollowing = mView.findViewById(R.id.tvFollowing);
        tvAccountSetting = mView.findViewById(R.id.tvAccountSetting);
        tvChangePassword = mView.findViewById(R.id.tvChangePassword);
        tvLogout = mView.findViewById(R.id.tvLogout);

        btnLogin = mView.findViewById(R.id.btnLogin);
        btnRegister = mView.findViewById(R.id.btnRegister);

        // Set OnClickListener
        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log out
            }
        });
        tvClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainActivity.goToClassFragment();
            }
        });
        tvFollowing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainActivity.goToFollowingFragment();
            }
        });
        tvAccountSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainActivity.goToAccountSettingFragment();
            }
        });
        tvChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainActivity.goToChangePasswordFragment();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainActivity.goToLoginFragment();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainActivity.goToRegisterFragment();
            }
        });

        return mView;
    }

    public void setUserInteractUI() {
        currentUser = mMainActivity.getCurrentLoginUser();
        if (currentUser==null) {
            loginLayoutProfileHeading.setVisibility(View.GONE);
            loginLayoutProfileContent.setVisibility(View.VISIBLE);

            logoutLayoutProfileHeading.setVisibility(View.VISIBLE);
            logoutLayoutProfileContent.setVisibility(View.GONE);
        } else {
            // Hiện layout đã đăng nhập
            loginLayoutProfileHeading.setVisibility(View.VISIBLE);
            loginLayoutProfileContent.setVisibility(View.VISIBLE);
            // Ẩn layout chưa đăng nhập
            logoutLayoutProfileHeading.setVisibility(View.GONE);
            logoutLayoutProfileContent.setVisibility(View.GONE);
        }
    }
}