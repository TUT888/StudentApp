package com.example.studentapp.extra_fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.studentapp.R;
import com.example.studentapp.fragment.MyPostFragment;
import com.example.studentapp.model.Post;
import com.google.android.material.button.MaterialButton;

public class PostDetailFragment extends Fragment {
    // Resources
    private View mView;
    private ImageButton ibBack;
    private MaterialButton mbContact;
    // Class
    private Post post;
    private String previousFragment; //MyPostFragment, SearchPostFragment,...

    public PostDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_post_detail, container, false);

        // Bind View
        mbContact = mView.findViewById(R.id.mbContact);
        ibBack = mView.findViewById(R.id.ibBack);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });

        // Get Bundle
        Bundle bundle = getArguments();
        if (bundle != null){
            post = (Post) bundle.getSerializable("post");
            previousFragment = bundle.getString("previous", "");
            if (previousFragment.equals(MyPostFragment.class.getSimpleName())) {
                mbContact.setVisibility(View.GONE);
            }
        }

        return mView;
    }
}