package com.example.studentapp.extra_fragment;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentapp.MainActivity;
import com.example.studentapp.R;
import com.example.studentapp.model.User;
import com.google.android.material.button.MaterialButton;


public class RegisterFragment extends Fragment {
    // Resources
    private View mView;
    private MainActivity mMainActivity;
    private ImageButton ibBack;
    private EditText etName, etPhoneNumber, etPassword, etEmail;
    private MaterialButton btnRegister;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_register, container, false);

        etName = mView.findViewById(R.id.etName);
        etPhoneNumber = mView.findViewById(R.id.etPhoneNumber);
        etPassword = mView.findViewById(R.id.etName);
        etEmail = mView.findViewById(R.id.etEmail);
        btnRegister = mView.findViewById(R.id.btnRegister);

        ibBack = mView.findViewById(R.id.ibBack);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerAccount();
            }
        });

        return mView;
    }

    private void registerAccount() {
        String name = etName.getText().toString();
        String phoneNumber = etPhoneNumber.getText().toString();
        String pass = etPassword.getText().toString();
        String email = etEmail.getText().toString();

        if (name.equals("")) {
            Toast.makeText(mMainActivity, "Hãy nhập họ và tên", Toast.LENGTH_SHORT).show();
        } else if (phoneNumber.equals("")) {
            Toast.makeText(mMainActivity, "Hãy nhập số điện thoại", Toast.LENGTH_SHORT).show();
        } else if (pass.equals("")) {
            Toast.makeText(mMainActivity, "Hãy nhập mật khẩu", Toast.LENGTH_SHORT).show();
        } else if (email.equals("")) {
            Toast.makeText(mMainActivity, "Hãy nhập email", Toast.LENGTH_SHORT).show();
        } else {
            //Call Register API
            User newUser = new User(phoneNumber, name, 0, "address",
                    0, "birthday", email, R.drawable.ic_default_user, pass);
        }
    }
//    int radioId = rgMethod.getCheckedRadioButtonId();
//        switch (radioId) {
//        case R.id.rbOffline:
//            return "online";
//        case R.id.rbOnline:
//            return "offline";
//        default:
//            return "";
//    }
}