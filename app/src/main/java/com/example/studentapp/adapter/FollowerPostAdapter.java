package com.example.studentapp.adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentapp.extra_fragment.PostDetailFragment;
import com.example.studentapp.R;
import com.example.studentapp.app_interface.IClickPostObjectListener;
import com.example.studentapp.model.Post;
import com.example.studentapp.model.User;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class FollowerPostAdapter extends RecyclerView.Adapter<FollowerPostAdapter.FollowerPostViewHolder>{

    private List<Post> posts;
    private IClickPostObjectListener mIClickPostObjectListener;

    public FollowerPostAdapter(List<Post> postsList, IClickPostObjectListener iClickPostObjectListener) {
        posts = postsList;
        mIClickPostObjectListener = iClickPostObjectListener;
    }

    @NonNull
    @Override
    public FollowerPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_object, parent, false);
        return new FollowerPostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowerPostViewHolder holder, int position) {
        Post post = posts.get(position);
        if (post == null) {
            return;
        }
        //holder.imgAvatar.setImageResource(post.getUser().getAvatar());
        holder.tvName.setText(post.getUser().getName());

        holder.tvRole.setText("Gia sư");
        holder.tvTitle.setText(post.getTitle());
        holder.tvMonHoc.setText(post.getSubject());
        holder.tvKhuVuc.setText(String.join(", ", post.getLearningPlaces()));
        holder.tvThoiGian.setText(String.join(", ", post.getLearningTimes()));
        holder.tvNgayHoc.setText(String.join(", ", post.getLearningDates()));
        holder.tvHinhThuc.setText(post.getMethod());
        holder.tvHocPhi.setText(String.valueOf(post.getTuition()));
        holder.tvNgayDang.setText(post.getPostDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Adapter", "clicked");
                mIClickPostObjectListener.onClickPostObject(post);
            }
        });
        holder.btnAnBaiDang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIClickPostObjectListener.onClickBtnHidePost(post);
                posts.remove(post);
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (posts != null) {
            return posts.size();
        }
        return 0;
    }

    public class FollowerPostViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAvatar;
        TextView tvName, tvRole, tvTitle,
                tvMonHoc, tvKhuVuc, tvThoiGian,
                tvNgayHoc, tvHinhThuc, tvHocPhi, tvNgayDang;
        Button btnAnBaiDang;

        public FollowerPostViewHolder(@NonNull View itemView) {
            super(itemView);

            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            tvName = itemView.findViewById(R.id.tvName);
            tvRole = itemView.findViewById(R.id.tvRole);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvMonHoc = itemView.findViewById(R.id.tvMonHoc);
            tvKhuVuc = itemView.findViewById(R.id.tvKhuVuc);
            tvThoiGian = itemView.findViewById(R.id.tvThoiGian);
            tvNgayHoc = itemView.findViewById(R.id.tvNgayHoc);
            tvHinhThuc = itemView.findViewById(R.id.tvHinhThuc);
            tvHocPhi = itemView.findViewById(R.id.tvHocPhi);
            tvNgayDang = itemView.findViewById(R.id.tvNgayDang);
            btnAnBaiDang = itemView.findViewById(R.id.btnAnBaiDang);
        }
    }
}
