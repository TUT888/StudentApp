package com.example.studentapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.studentapp.MainActivity;
import com.example.studentapp.R;
import com.example.studentapp.adapter.ClassAdapter;
import com.example.studentapp.app_interface.IClickBtnRating;
import com.example.studentapp.model.ClassObject;
import com.example.studentapp.model.Rate;

import java.util.ArrayList;
import java.util.List;

public class ClassFragment extends Fragment {

    RecyclerView rvClasses;
    ClassAdapter classAdapter;
    List<ClassObject> classObjects = new ArrayList<>();
    MainActivity mainActivity;
    int adapterPosition = -1;
    ImageButton ibBack;

    public ClassFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = (View) inflater.inflate(R.layout.fragment_class, container, false);

        rvClasses = view.findViewById(R.id.rvClasses);
        ibBack = view.findViewById(R.id.ibBack);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        rvClasses.setLayoutManager(linearLayoutManager);

        mainActivity = (MainActivity) getActivity();
        mainActivity.getSupportFragmentManager().setFragmentResultListener("getAdapterPosition", getViewLifecycleOwner(),
                new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        adapterPosition = (int) result.getInt("adapter_position");
                        if (adapterPosition != -1) {
                            rvClasses.scrollToPosition(adapterPosition);
                            classAdapter.changeClassStatus(adapterPosition);
                        }
                    }
                });

        getData();
        classAdapter = new ClassAdapter(classObjects, new IClickBtnRating() {
            @Override
            public void rateClass(ClassObject classObject, int adapterPosition) {
                mainActivity.goToRateFragment(classObject, adapterPosition);
            }

            @Override
            public void seeRateDetail(Rate rate) {
                mainActivity.goToRateDetailFragment(rate);
            }
        });
        rvClasses.setAdapter(classAdapter);

        return view;
    }

    void getData() {
        classObjects.add(new ClassObject("01", "Name1", "012888813", "054683123", "TP.HCM",
                0, 200000, "23h31p 18/12/2022", "18/12/2022", "24/12/2022", "Online",
                "CNTT", "CNTT"));
        classObjects.add(new ClassObject("02", "Name2", "012888813", "054683123", "TP.HCM",
                1, 200000, "23h31p 18/12/2022", "18/12/2022", "24/12/2022", "Online",
                "CNTT", "CNTT"));
        classObjects.add(new ClassObject("02", "Name3", "012888813", "054683123", "TP.HCM",
                2, 200000, "23h31p 18/12/2022", "18/12/2022", "18/12/2022", "Online",
                "CNTT", "CNTT"));
        classObjects.add(new ClassObject("01", "Name4", "012888813", "054683123", "TP.HCM",
                0, 200000, "23h31p 18/12/2022", "18/12/2022", "18/12/2022", "Online",
                "CNTT", "CNTT"));
        classObjects.add(new ClassObject("02", "Name5", "012888813", "054683123", "TP.HCM",
                1, 200000, "23h31p 18/12/2022", "18/12/2022", "18/12/2022", "Online",
                "CNTT", "CNTT"));
        classObjects.add(new ClassObject("02", "Name6", "012888813", "054683123", "TP.HCM",
                2, 200000, "23h31p 18/12/2022", "18/12/2022", "18/12/2022", "Online",
                "CNTT", "CNTT"));
        classObjects.add(new ClassObject("01", "Name7", "012888813", "054683123", "TP.HCM",
                0, 200000, "23h31p 18/12/2022", "18/12/2022", "19/12/2022", "Online",
                "CNTT", "CNTT"));
        classObjects.add(new ClassObject("02", "Name8", "012888813", "054683123", "TP.HCM",
                1, 200000, "23h31p 18/12/2022", "18/12/2022", "18/12/2022", "Online",
                "CNTT", "CNTT"));
        classObjects.add(new ClassObject("02", "Name9", "012888813", "054683123", "TP.HCM",
                2, 200000, "23h31p 18/12/2022", "18/12/2022", "18/12/2022", "Online",
                "CNTT", "CNTT"));
    }
}