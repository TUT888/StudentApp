package com.example.studentapp.extra_fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentapp.MainActivity;
import com.example.studentapp.R;
import com.example.studentapp.app_interface.IClickBtnSaveRating;
import com.example.studentapp.fragment.MyPostFragment;
import com.example.studentapp.model.ClassObject;
import com.example.studentapp.model.Post;
import com.google.android.material.button.MaterialButton;

public class RateFragment extends Fragment {

    ClassObject classObject;
    TextView rtClassName, rtTutor;
    RatingBar ratingBar;
    EditText rtCmt;
    MaterialButton btnSaveRate;
    MainActivity mainActivity;
    IClickBtnSaveRating iClickBtnSaveRating;
    ImageButton ibBack;

    public RateFragment(IClickBtnSaveRating iClickBtnSaveRating) {
        this.iClickBtnSaveRating = iClickBtnSaveRating;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rate, container, false);
        rtClassName = view.findViewById(R.id.rtClass);
        rtTutor = view.findViewById(R.id.rtTutor);
        ratingBar = view.findViewById(R.id.ratingBar);
        rtCmt = view.findViewById(R.id.rtCmt);
        btnSaveRate = view.findViewById(R.id.btnSaveRate);
        ibBack = view.findViewById(R.id.ibBack);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });

        mainActivity = (MainActivity) getActivity();

        Bundle bundle = getArguments();
        if (bundle != null){
            classObject = (ClassObject) bundle.getSerializable("class_object");
        }
        rtClassName.setText(classObject.getClassName());
        rtTutor.setText(classObject.getTutorPhone());

        btnSaveRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = validate();
                if (check == true) {
                    iClickBtnSaveRating.saveAndReturnToClassFragment();
                }
            }
        });
        return view;
    }

    private boolean validate() {
        if (ratingBar.getRating() < 0.5) {
            Toast.makeText(mainActivity, "Please rate at least one star", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}