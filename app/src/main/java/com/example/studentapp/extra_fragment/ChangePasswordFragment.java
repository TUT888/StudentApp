package com.example.studentapp.extra_fragment;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.studentapp.MainActivity;
import com.example.studentapp.R;
import com.google.android.material.button.MaterialButton;

public class ChangePasswordFragment extends Fragment {
    private MainActivity mMainActivity;
    private View mView;
    private ImageButton ibBack;
    private MaterialButton btnChangePass;
    private EditText etOldPassword, etNewPassword, etConfirmPassword;

    public ChangePasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_change_password, container, false);
        mMainActivity = (MainActivity) getActivity();

        ibBack = mView.findViewById(R.id.ibBack);
        btnChangePass = mView.findViewById(R.id.btnChangePass);
        etOldPassword = mView.findViewById(R.id.etOldPassword);
        etNewPassword = mView.findViewById(R.id.etNewPassword);
        etConfirmPassword = mView.findViewById(R.id.etConfirmPassword);

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPass = etOldPassword.getText().toString();
                String newPass = etNewPassword.getText().toString();
                String confirmPass = etConfirmPassword.getText().toString();
                if (oldPass.equals("")) {
                    Toast.makeText(mMainActivity, "Hãy nhập mật khẩu", Toast.LENGTH_SHORT).show();
                } else if (newPass.equals("")) {
                    Toast.makeText(mMainActivity, "Hãy nhập mật khẩu mới", Toast.LENGTH_SHORT).show();
                } else if (confirmPass.equals("")) {
                    Toast.makeText(mMainActivity, "Hãy xác nhận mật khẩu mới", Toast.LENGTH_SHORT).show();
                } else if (!confirmPass.equals(newPass)) {
                    Toast.makeText(mMainActivity, "Xác nhận mật khẩu mới không đúng", Toast.LENGTH_SHORT).show();
                } else {
                    changePassword();
                }
            }
        });
        return mView;
    }

    private void changePassword() {
        //Call API

        getParentFragmentManager().popBackStack();
        mMainActivity.resetViewPagerUI(4);
        Toast.makeText(mMainActivity, "Password changed successfully!", Toast.LENGTH_SHORT).show();
    }
}