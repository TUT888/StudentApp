package com.example.studentapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studentapp.MainActivity;
import com.example.studentapp.R;
import com.example.studentapp.adapter.MyPostAdapter;
import com.example.studentapp.app_interface.IClickPostObjectListener;
import com.example.studentapp.model.Field;
import com.example.studentapp.model.Post;
import com.example.studentapp.model.User;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MyPostFragment extends Fragment {
    private View mView;
    private MainActivity mMainActivity;

    private RecyclerView rcvMyPosts;
    private ArrayList<Post> myPostArrayList;
    private MyPostAdapter myPostAdapter;

    private MaterialButton btnAddNewPost;

    public MyPostFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_post, container, false);
        mMainActivity = (MainActivity) getActivity();

        rcvMyPosts = mView.findViewById(R.id.rcvMyPosts);
        rcvMyPosts.setLayoutManager(new LinearLayoutManager(getContext()));

        //Create empty ArrayList & setup adapter
        //myPostArrayList = new ArrayList<>();
        myPostArrayList = initPost();
        myPostAdapter = new MyPostAdapter(myPostArrayList, new IClickPostObjectListener() {
            @Override
            public void onClickPostObject(Post post) {
                Log.d("Fragment", "set on clicked");
                mMainActivity.goToPostDetailFragment(post, MyPostFragment.class.getSimpleName());
            }

            @Override
            public void onClickBtnHidePost(Post post) {

            }
        });
        rcvMyPosts.setAdapter(myPostAdapter);

        //Get data from database
        //getBooksFromDatabase(getBooksUrl);

        btnAddNewPost = mView.findViewById(R.id.btnAddNewPost);
        btnAddNewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainActivity.goToAddNewPostFragment();
            }
        });

        return mView;
    }

    public ArrayList<Post> initPost() {
        ArrayList<Post> arrayList = new ArrayList<>();
        arrayList.add(new Post("01", "Tìm gia sư toán", 0,
                "0998776755",
                "Toan lop 12", "Toán",
                "9h-12h, 15h-17h", "Thứ 2, Thứ 3, Thứ 5", "Q1, Q2",
                "offline", 200000, "Hoc Toan bao dau dai hoc", "12/12/2022", ""));
        arrayList.add(new Post("02", "Tìm gia sư văn", 0,
                "0998876654",
                "Văn lớp 12", "Văn",
                "9h-12h, 15h-17h", "Thứ 2, Thứ 3, Thứ 5", "Q1, Q2",
                "offline", 200000, "Hoc Toan bao dau dai hoc", "12/12/2022", ""));
        return arrayList;
    }
}