package com.example.studentapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    int adapterPosition;

    public ClassFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = (View) inflater.inflate(R.layout.fragment_class, container, false);

        rvClasses = view.findViewById(R.id.rvClasses);

        Bundle bundle = getArguments();
        if (bundle != null){
            adapterPosition = (int) bundle.getInt("adapter_position");
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        rvClasses.setLayoutManager(linearLayoutManager);

        mainActivity = (MainActivity) getActivity();

        getData();
        classAdapter = new ClassAdapter(classObjects, new IClickBtnRating() {
            @Override
            public void rateClass(ClassObject classObject, int adapterPosition) {
                mainActivity.goToRateFragment(classObject, adapterPosition);
            }

            @Override
            public void seeRateDetail(Rate rate) {
                mainActivity.goToRateDetailFragment(rate, ClassFragment.class.getSimpleName());
            }
        });
        rvClasses.setAdapter(classAdapter);
        if (adapterPosition != -1) {
            rvClasses.scrollToPosition(adapterPosition);
            classAdapter.changeClassStatus(adapterPosition);
        }

        return view;
    }

    void getData() {
        classObjects.add(new ClassObject("01", "Name1", "012888813", "054683123", "TP.HCM",
                0, 200000, "23h31p 18/12/2022", "18/12/2022", "24/12/2022", "Online",
                "CNTT", "CNTT"));
        classObjects.add(new ClassObject("02", "Name1", "012888813", "054683123", "TP.HCM",
                1, 200000, "23h31p 18/12/2022", "18/12/2022", "24/12/2022", "Online",
                "CNTT", "CNTT"));
        classObjects.add(new ClassObject("02", "Name1", "012888813", "054683123", "TP.HCM",
                2, 200000, "23h31p 18/12/2022", "18/12/2022", "18/12/2022", "Online",
                "CNTT", "CNTT"));
        classObjects.add(new ClassObject("01", "Name1", "012888813", "054683123", "TP.HCM",
                0, 200000, "23h31p 18/12/2022", "18/12/2022", "18/12/2022", "Online",
                "CNTT", "CNTT"));
        classObjects.add(new ClassObject("02", "Name1", "012888813", "054683123", "TP.HCM",
                1, 200000, "23h31p 18/12/2022", "18/12/2022", "18/12/2022", "Online",
                "CNTT", "CNTT"));
        classObjects.add(new ClassObject("02", "Name1", "012888813", "054683123", "TP.HCM",
                2, 200000, "23h31p 18/12/2022", "18/12/2022", "18/12/2022", "Online",
                "CNTT", "CNTT"));
        classObjects.add(new ClassObject("01", "Name1", "012888813", "054683123", "TP.HCM",
                0, 200000, "23h31p 18/12/2022", "18/12/2022", "19/12/2022", "Online",
                "CNTT", "CNTT"));
        classObjects.add(new ClassObject("02", "Name1", "012888813", "054683123", "TP.HCM",
                1, 200000, "23h31p 18/12/2022", "18/12/2022", "18/12/2022", "Online",
                "CNTT", "CNTT"));
        classObjects.add(new ClassObject("02", "Name1", "012888813", "054683123", "TP.HCM",
                2, 200000, "23h31p 18/12/2022", "18/12/2022", "18/12/2022", "Online",
                "CNTT", "CNTT"));
    }
}