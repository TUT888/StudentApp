package com.example.studentapp.extra_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.studentapp.R;
import com.example.studentapp.fragment.MyPostFragment;
import com.example.studentapp.model.ClassObject;
import com.example.studentapp.model.Post;
import com.example.studentapp.model.Rate;
import com.google.android.material.button.MaterialButton;

public class RatingDetailFragment extends Fragment {
    // Resources
    private ImageButton ibBack;
    private TextView ratedClassName, ratedTutorName, ratedCmt;
    private RatingBar ratedBar;
    // Class
    private Rate rate;

    public RatingDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rating_detail, container, false);

        // Bind View
        ratedClassName = view.findViewById(R.id.ratedClassName);
        ratedTutorName = view.findViewById(R.id.ratedClassTutor);
        ratedBar = view.findViewById(R.id.ratedBar);
        ratedCmt = view.findViewById(R.id.ratedCmt);
        ibBack = view.findViewById(R.id.ibBack);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });

        // Get Bundle
        Bundle bundle = getArguments();
        if (bundle != null){
            rate = (Rate) bundle.getSerializable("rate");
        }
        ClassObject classObject = new ClassObject("01", "Name1", "012888813", "054683123", "TP.HCM",
                0, 200000, "23h31p 18/12/2022", "18/12/2022", "24/12/2022", "Online",
                "CNTT", "CNTT");
        ratedClassName.setText(classObject.getClassName());
        ratedTutorName.setText(classObject.getTutorPhone());
        ratedBar.setRating(rate.getRate());
        ratedCmt.setText(rate.getComment());
        return view;
    }
}
