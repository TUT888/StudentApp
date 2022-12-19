package com.example.studentapp.extra_fragment;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentapp.R;
import com.example.studentapp.fragment.MyPostFragment;
import com.example.studentapp.model.Post;
import com.google.android.material.button.MaterialButton;

public class PostDetailFragment extends Fragment {
    // Resources
    private LinearLayout layoutPostOption;
    private View mView;
    private ImageButton ibBack, ibPostOption;
    private MaterialButton mbContact;
    private TextView tvStatus;
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
        layoutPostOption = mView.findViewById(R.id.layoutPostOption);
        mbContact = mView.findViewById(R.id.mbContact);
        ibBack = mView.findViewById(R.id.ibBack);
        ibPostOption = mView.findViewById(R.id.ibPostOption);
        tvStatus = mView.findViewById(R.id.tvStatus);
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
            // Từ MyPostFragment
            if (previousFragment.equals(MyPostFragment.class.getSimpleName())) {
                mbContact.setVisibility(View.GONE);
                layoutPostOption.setVisibility(View.VISIBLE);

                switch (post.getStatus()) {
                    case ( Post.POST_STATUS_CREATED_CLASS ):
                        tvStatus.setText("Đã tạo lớp");
                        tvStatus.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.post_created_class));
                        break;
                    case ( Post.POST_STATUS_EDITED ):
                        tvStatus.setText("Đã chỉnh sửa");
                        tvStatus.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.post_edited));
                        break;
                    case ( Post.POST_STATUS_CANCELLED ):
                        tvStatus.setText("Đã hủy");
                        tvStatus.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.post_cancelled));
                        break;
                    default:
                        tvStatus.setText("Đang đợi");
                        tvStatus.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.post_waiting));
                        break;
                }
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
                            case R.id.create_class:
                                createClassFromPost();
                                return true;
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
    
    private void createClassFromPost() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext());
        alertBuilder.setTitle("Xác nhận tạo lớp học");
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.layout_create_class_diaglog, null);
        alertBuilder.setView(dialogView);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText etUserPhone = dialogView.findViewById(R.id.etUserPhone);

        alertBuilder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Tạo lớp
                String phone = etUserPhone.getText().toString();
                if (phone.equals("")) {
                    Toast.makeText(getContext(), "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                } else {
                    /* Call API:
                    - Select * from where exist this user
                    - If exists, change the status of post & class
                     */
                    Toast.makeText(getContext(), "Đã tạo lớp, đợi đối phương chấp nhận", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alertBuilder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();
    }
}