package com.example.studentapp.search;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studentapp.MainActivity;
import com.example.studentapp.R;
import com.example.studentapp.adapter.SearchPostAdapter;
import com.example.studentapp.api.APIService;
import com.example.studentapp.api.ResultAPI;
import com.example.studentapp.api.ResultObjectAPI;
import com.example.studentapp.app_interface.IClickPostObjectListener;
import com.example.studentapp.model.Post;
import com.google.gson.JsonObject;

import java.util.ArrayList;


public class SearchPostFragment extends Fragment {
    private View mView;
    private MainActivity mMainActivity;

    private RecyclerView rcvSearchPost;
    private ArrayList<Post> searchPostArrayList;
    private SearchPostAdapter searchPostAdapter;
    private SearchView svSearchPost;
    private ArrayList<Post> filteredList;
    private ArrayList<String> nameList;

    public SearchPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_search_post, container, false);
        mMainActivity = (MainActivity) getActivity();
        rcvSearchPost = mView.findViewById(R.id.rcvSearchPost);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvSearchPost.setLayoutManager(linearLayoutManager);
        searchPostArrayList = initPost();
        searchPostAdapter = new SearchPostAdapter(searchPostArrayList, new IClickPostObjectListener() {
            @Override
            public void onClickPostObject(Post post) {
                mMainActivity.goToPostDetailFragment(post, SearchPostFragment.class.getSimpleName());
            }

            @Override
            public void onClickBtnHidePost(Post post) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Bạn có muốn ẩn bài post này không?")
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                searchPostAdapter.remove(post);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                builder.show();
            }
        });
        rcvSearchPost.setAdapter(searchPostAdapter);
        svSearchPost = mView.findViewById(R.id.svSearchPost);
        svSearchPost.clearFocus();

        svSearchPost.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        return mView;
    }

    public ArrayList<Post> initPost() {
        ArrayList<Post> arrayList = new ArrayList<>();

        return arrayList;
    }



    public void filterList(String text){
        filteredList = new ArrayList<>();
        nameList = new ArrayList<>();
        APIService.apiService.getSearchPost(text).enqueue(new retrofit2.Callback<ResultObjectAPI>() {
            @Override
            public void onResponse(retrofit2.Call<ResultObjectAPI> call, retrofit2.Response<ResultObjectAPI> response) {
                ResultObjectAPI resultAPI = response.body();
                if(response.isSuccessful() && resultAPI != null){
                    if (resultAPI.getCode() == 0){
                        for (int i = 0; i < resultAPI.getData().get("post").getAsJsonArray().size(); i++){
                            JsonObject jsonObject = resultAPI.getData().get("post").getAsJsonArray().get(i).getAsJsonObject();
                            String name = resultAPI.getData().get("name").getAsJsonArray().get(i).getAsString();
                            Post post = new Post(jsonObject.get("id").getAsString(),
                                    jsonObject.get("title").getAsString(),
                                    jsonObject.get("status").getAsInt(),
                                    jsonObject.get("idUser").getAsString(),
                                    jsonObject.get("subject").getAsString(),
                                    jsonObject.get("field").getAsString(),
                                    jsonObject.get("dateTimesLearning").getAsString(),
                                    jsonObject.get("learningPlaces").getAsString(),
                                    jsonObject.get("method").getAsString(),
                                    jsonObject.get("tuition").getAsInt(),
                                    jsonObject.get("description").getAsString(),
                                    jsonObject.get("postDate").getAsString(),
                                    jsonObject.get("hideFrom").getAsString());
                            filteredList.add(post);
                            nameList.add(name);
                        }
                        searchPostAdapter.setData(filteredList);
                        searchPostAdapter.setNames(nameList);
                    }
                }

            }

            @Override
            public void onFailure(retrofit2.Call<ResultObjectAPI> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                Log.d("onFailure", "onFailure: " + t.getMessage());
            }
        });
    }


}