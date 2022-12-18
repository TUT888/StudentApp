package com.example.studentapp.home;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.studentapp.MainActivity;
import com.example.studentapp.R;
import com.example.studentapp.adapter.FollowerPostAdapter;
import com.example.studentapp.app_interface.IClickPostObjectListener;
import com.example.studentapp.fragment.MyPostFragment;
import com.example.studentapp.model.Post;
import com.example.studentapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class FollowerPostsFragment extends Fragment {

    RecyclerView rvFlPosts;
    FollowerPostAdapter followerPostAdapter;
    List<Post> postList = new ArrayList<>();

    public FollowerPostsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = (View) inflater.inflate(R.layout.fragment_follower_posts, container, false);

        rvFlPosts = view.findViewById(R.id.rvFlPosts);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        rvFlPosts.setLayoutManager(linearLayoutManager);

        MainActivity mainActivity = (MainActivity) getActivity();

//        User currentUser = mainActivity.getCurrentLoginUser();

        getData();
        followerPostAdapter = new FollowerPostAdapter(postList, new IClickPostObjectListener() {
            @Override
            public void onClickPostObject(Post post) {
                Log.d("Fragment", "set on clicked");
                mainActivity.goToPostDetailFragment(post, MyPostFragment.class.getSimpleName());
            }

            @Override
            public void onClickBtnHidePost(Post post) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
                builder.setMessage("Bạn có muốn ẩn bài đăng này không?")
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
//                                post.addHide(currentUser.getPhoneNumber());
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
        rvFlPosts.setAdapter(followerPostAdapter);
        return view;
    }

    private void getData() {
        postList.add(new Post("01", "Tìm gia sư toán", 0,
                "0998776755",
                "Toan lop 12", "Toán",
                "Thứ 2:9h-12h, Thứ 3: 15h-17h", "Q1, Q2",
                "offline", 200000, "Hoc Toan bao dau dai hoc", "12/12/2022", ""));
        postList.add(new Post("02", "Tìm gia sư văn", 0,
                "0998876654",
                "Văn lớp 12", "Văn",
                "Thứ 2:9h-12h, Thứ 3: 15h-17h", "Q1, Q2",
                "offline", 200000, "Hoc Toan bao dau dai hoc", "12/12/2022", ""));
    }
}