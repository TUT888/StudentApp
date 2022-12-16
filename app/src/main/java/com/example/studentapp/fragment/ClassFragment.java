package com.example.studentapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studentapp.R;
import com.example.studentapp.adapter.ClassAdapter;
import com.example.studentapp.adapter.FollowerPostAdapter;
import com.example.studentapp.model.ClassObject;
import com.example.studentapp.model.Post;

import java.util.ArrayList;
import java.util.List;

public class ClassFragment extends Fragment {

    RecyclerView rvClasses;
    ClassAdapter classAdapter;
    List<ClassObject> classObjects = new ArrayList<>();

    public ClassFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = (View) inflater.inflate(R.layout.fragment_class, container, false);

        rvClasses = view.findViewById(R.id.rvFlPosts);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        rvClasses.setLayoutManager(linearLayoutManager);

        getData();
        classAdapter = new ClassAdapter(classObjects);
        rvClasses.setAdapter(classAdapter);

        return view;
    }

    void getData() {

    }
}