package com.example.studentapp.extra_fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentapp.MainActivity;
import com.example.studentapp.R;
import com.example.studentapp.adapter.AllRatingAdapter;
import com.example.studentapp.adapter.ClassAdapter;
import com.example.studentapp.api.APIService;
import com.example.studentapp.api.ResultAPI;
import com.example.studentapp.api.ResultObjectAPI;
import com.example.studentapp.app_interface.IClickBtnRating;
import com.example.studentapp.model.ClassObject;
import com.example.studentapp.model.Rate;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllRatingsFragment extends Fragment {

    RecyclerView rvAllRatings;
    AllRatingAdapter adapter;
    MainActivity mainActivity;
    ImageButton ibBack;
    float avg_rating = (float)0.0;

    public AllRatingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_ratings, container, false);
        rvAllRatings = view.findViewById(R.id.rvAllRatings);
        ibBack = view.findViewById(R.id.ibBack);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        rvAllRatings.setLayoutManager(linearLayoutManager);
        mainActivity = (MainActivity) getActivity();
        adapter = new AllRatingAdapter();
        Bundle bundle = getArguments();
        if (bundle != null) {
            getClassFromTutorId(bundle.getString("tutorPhone"));
        }
        rvAllRatings.setAdapter(adapter);

        return view;
    }

    void getClassFromTutorId (String tutorId) {
        List<String> classIds = new ArrayList<>();
        APIService.apiService.getClassRatedFromTutor(tutorId).enqueue(new Callback<ResultAPI>() {
            @Override
            public void onResponse(Call<ResultAPI> call, Response<ResultAPI> response) {
                ResultAPI resultAPI = response.body();
                if(response.isSuccessful() && resultAPI != null){
                    if (resultAPI.getCode() == 0){
                        JsonArray jsonArray = resultAPI.getData().getAsJsonArray();
                        for (int i = 0; i < jsonArray.size(); i++){
                            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                            String classId = jsonObject.get("id").getAsString();
                            classIds.add(classId);
                        }
                    }
                    getRatingFromClassId (classIds);
                }
            }

            @Override
            public void onFailure(Call<ResultAPI> call, Throwable t) {
                Toast.makeText(mainActivity, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                Log.d("onFailure", "onFailure: " + t.getMessage());
            }
        });
    }

    void getRatingFromClassId (List<String> classIds) {
        for (String classId : classIds) {
            APIService.apiService.getRatingByClassID(classId).enqueue(new Callback<ResultObjectAPI>() {
                @Override
                public void onResponse(Call<ResultObjectAPI> call, Response<ResultObjectAPI> response) {
                    ResultObjectAPI resultAPI = response.body();
                    if (response.isSuccessful() && resultAPI != null) {
                        if (resultAPI.getCode() == 0) {
                            JsonObject jsonObject = resultAPI.getData().getAsJsonObject();
                            Rate rate = new Rate(jsonObject.get("classID").getAsString(), jsonObject.get("rate").getAsFloat(),
                                    jsonObject.get("comment").getAsString(), jsonObject.get("date").getAsString());
                            adapter.addData(rate);
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResultObjectAPI> call, Throwable t) {
                    Toast.makeText(mainActivity, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                    Log.d("onFailure", "onFailure: " + t.getMessage());
                }
            });
        }
    }
}