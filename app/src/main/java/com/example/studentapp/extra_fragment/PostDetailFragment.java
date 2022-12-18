package com.example.studentapp.extra_fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.studentapp.R;
import com.example.studentapp.fragment.MyPostFragment;
import com.example.studentapp.model.Post;
import com.google.android.material.button.MaterialButton;

public class PostDetailFragment extends Fragment {
    // Resources
    private View mView;
    private ImageButton ibBack, ibPostOption;
    private MaterialButton mbContact;
    // Object Class
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
        ibPostOption = mView.findViewById(R.id.ibPostOption);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });

        // Get Bundle
        // Kiểm tra xem chuyển từ Fragment nào đến đây
        // Nếu là từ Fragment MyPost: Ẩn nút "ẩn bài đăng", hiện nút option menu cho phép sửa/xóa/đâng lại
        // Nếu là từ Fragment khác: MẶC ĐỊNH ngược lại với cái ở trên
        Bundle bundle = getArguments();
        if (bundle != null){
            post = (Post) bundle.getSerializable("post");
            previousFragment = bundle.getString("previous", "");
            if (previousFragment.equals(MyPostFragment.class.getSimpleName())) {
                mbContact.setVisibility(View.GONE);
                ibPostOption.setVisibility(View.VISIBLE);
            }
        }

        ibPostOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu pm = new PopupMenu(view.getContext(), view);
                pm.getMenuInflater().inflate(R.menu.mypost_popup_menu, pm.getMenu());
                pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.edit_post:
                                Toast.makeText(view.getContext(), "Edited", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.repost:
                                Toast.makeText(view.getContext(), "Repost", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.delete_post:
                                Toast.makeText(view.getContext(), "Delete", Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return true;
                        }
                    }
                });
                pm.show();
            }
        });

        return mView;
    }
}