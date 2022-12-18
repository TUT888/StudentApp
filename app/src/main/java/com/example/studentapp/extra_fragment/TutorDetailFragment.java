package com.example.studentapp.extra_fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.studentapp.R;
import com.example.studentapp.fragment.MyPostFragment;
import com.example.studentapp.model.Post;
import com.example.studentapp.model.Tutor;
import com.google.android.material.button.MaterialButton;

import de.hdodenhof.circleimageview.CircleImageView;


public class TutorDetailFragment extends Fragment {

    private View mView;
    private CircleImageView civAvatar;
    private TextView tvName, tvRole, tvEmail, tvSDT, tvGioiTinh, tvLinhVuc, tvKhuVuc, tvHocVan, tvTruong;
    private MaterialButton mbFollow;
    private ImageButton ibBack;
    private Tutor tutor;
    private String previousFragment;
    public TutorDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_tutor_detail, container, false);
        civAvatar = mView.findViewById(R.id.civAvatar);
        tvName = mView.findViewById(R.id.tvName);
        tvRole = mView.findViewById(R.id.tvRole);
        tvEmail = mView.findViewById(R.id.tvEmail);
        tvSDT = mView.findViewById(R.id.tvSDT);
        tvGioiTinh = mView.findViewById(R.id.tvGioiTinh);
        tvLinhVuc = mView.findViewById(R.id.tvLinhVuc);
        tvKhuVuc = mView.findViewById(R.id.tvKhuVuc);
        tvHocVan = mView.findViewById(R.id.tvHocVan);
        tvTruong = mView.findViewById(R.id.tvTruong);
        mbFollow = mView.findViewById(R.id.mbFollow);
        ibBack = mView.findViewById(R.id.ibBack);

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });

        Bundle bundle = getArguments();
        if (bundle != null){
            tutor = (Tutor) bundle.getSerializable("tutor");
            previousFragment = bundle.getString("previous", "");
        }

        if (tutor != null){
            tvName.setText(tutor.getName());
            tvRole.setText("Gia sư");
            tvEmail.setText(tutor.getEmail());
            tvSDT.setText(tutor.getPhoneNumber());
            if (tutor.getGender() == 0){
                tvGioiTinh.setText("Nam");
            }else {
                tvGioiTinh.setText("Nữ");
            }
            tvLinhVuc.setText(tutor.getFields());
            tvKhuVuc.setText(tutor.getAreas());
            tvHocVan.setText(tutor.getAcademicLevel());
            tvTruong.setText(tutor.getSchool());
        }

        return mView;
    }
}