package com.example.studentapp.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.studentapp.MainActivity;
import com.example.studentapp.R;
import com.example.studentapp.model.User;


public class ProfileFragment extends Fragment {
    // Resources
    private View mView;
    private LinearLayout loginLayoutProfileHeading, logoutLayoutProfileHeading;
    private LinearLayout loginLayoutProfileContent, logoutLayoutProfileContent;
    private MainActivity mMainActivity;
    private Button btnLogin, btnRegister;
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
        setUserInteractUI();

        loginLayoutProfileHeading = mView.findViewById(R.id.loginLayoutProfileHeading);
        logoutLayoutProfileHeading = mView.findViewById(R.id.logoutLayoutProfileHeading);
        loginLayoutProfileContent = mView.findViewById(R.id.loginLayoutProfileContent);
        logoutLayoutProfileContent = mView.findViewById(R.id.logoutLayoutProfileContent);

        btnLogin = mView.findViewById(R.id.btnLogin);
        btnRegister = mView.findViewById(R.id.btnRegister);
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
            loginLayoutProfileContent.setVisibility(View.GONE);
            logoutLayoutProfileHeading.setVisibility(View.VISIBLE);
            logoutLayoutProfileContent.setVisibility(View.VISIBLE);
        } else {
            loginLayoutProfileHeading.setVisibility(View.VISIBLE);
            loginLayoutProfileContent.setVisibility(View.VISIBLE);
            logoutLayoutProfileHeading.setVisibility(View.GONE);
            logoutLayoutProfileContent.setVisibility(View.GONE);
        }
    }
}