package com.example.studentapp.search;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.studentapp.MainActivity;
import com.example.studentapp.R;
import com.example.studentapp.adapter.FollowerPostAdapter;
import com.example.studentapp.adapter.MyPostAdapter;
import com.example.studentapp.adapter.SearchPostAdapter;
import com.example.studentapp.app_interface.IClickPostObjectListener;
import com.example.studentapp.model.Post;

import java.util.ArrayList;


public class SearchPostFragment extends Fragment {
    private View mView;
    private MainActivity mMainActivity;

    private RecyclerView rcvSearchPost;
    private ArrayList<Post> searchPostArrayList;
    private SearchPostAdapter searchPostAdapter;
    private SearchView svSearchPost;

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
//        svSearchPost.clearFocus();
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
        arrayList.add(new Post("01", "Tìm gia sư toán", 0,
                "0998776755",
                "Toan lop 12", "Toán",
                "Thứ 2:9h-12h, Thứ 3: 15h-17h", "Q1, Q2",
                "offline", 200000, "Hoc Toan bao dau dai hoc", "12/12/2022", ""));
        arrayList.add(new Post("02", "Tìm gia sư văn", 0,
                "0998876654",
                "Văn lớp 12", "Văn",
                "Thứ 2:9h-12h, Thứ 3: 15h-17h", "Q1, Q2",
                "offline", 200000, "Hoc Toan bao dau dai hoc", "12/12/2022", ""));
        return arrayList;
    }

    public void filterList(String text){
        ArrayList<Post> filteredList = new ArrayList<>();
        for(Post post : searchPostArrayList){
            if(post.getSubject().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(post);
            }
            else if(post.getTitle().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(post);
            }
            else if (post.getLearningPlaces().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(post);
            }

        }
        searchPostAdapter.setData(filteredList);
    }
}