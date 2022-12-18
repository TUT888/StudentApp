package com.example.studentapp.extra_fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.studentapp.MainActivity;
import com.example.studentapp.R;
import com.example.studentapp.model.User;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;


public class LoginFragment extends Fragment {
    // Resources
    private View mView;
    private MainActivity mMainActivity;
    private ImageButton ibBack;
    private EditText etPhoneNumber, etPassword;
    private MaterialButton btnLogin;
    // Data
    private User userData;

    public LoginFragment() {
        userData = null;
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_login, container, false);
        mMainActivity = (MainActivity) getActivity();

        etPhoneNumber = mView.findViewById(R.id.etPhoneNumber);
        etPassword = mView.findViewById(R.id.etPassword);
        btnLogin = mView.findViewById(R.id.btnLogin);
        ibBack = mView.findViewById(R.id.ibBack);

        // Set OnClickListener
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginAccount();
            }
        });
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });

        return mView;
    }

    private void loginAccount() {
        String phone = etPhoneNumber.getText().toString();
        String pass = etPassword.getText().toString();

        if (phone.equals("")) {
            Toast.makeText(mMainActivity, "Hãy nhập số điện thoại", Toast.LENGTH_SHORT).show();
        } else if (pass.equals("")) {
            Toast.makeText(mMainActivity, "Hãy nhận mật khẩu", Toast.LENGTH_SHORT).show();
        } else {
            // Call API get account has same username & pass
            requestLogin(phone, pass);
            if (userData!=null) {
                // Save SharedPreference
                mMainActivity.savedLoginUser(userData);
                getFragmentManager().popBackStack();
            }
        }
    }

    // Call API
    private void requestLogin(String phone, String pass) {
        // Giả sử gọi API, query ra user có phone & pass trùng khớp
        User u = new User("0908888338", "Vương Hải Đăng", 1, "Quận 1", 0, "20/12/1998", "vhd@gmail.com", 7000008, "123456");
        userData = u;
    }

}