package com.example.studentapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.studentapp.R;
import com.example.studentapp.app_interface.IClickPostObjectListener;
import com.example.studentapp.model.Post;

import java.util.List;

public class MyPostAdapter extends RecyclerView.Adapter<MyPostAdapter.PostViewHolder> {
    private List<Post> mPostList;
    private IClickPostObjectListener mIClickPostObjectListener;

    public MyPostAdapter(List<Post> listPosts, IClickPostObjectListener listener) {
        mPostList = listPosts;
        mIClickPostObjectListener = listener;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View bookView = inflater.inflate(R.layout.post_object, parent, false);
        PostViewHolder viewHolder = new PostViewHolder(bookView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = (Post) mPostList.get(position);
        if (post == null) {
            return;
        }
        //holder.imgAvatar.setImageResource(post.getUser().getAvatar());
        holder.tvName.setText(post.getId());

        holder.tvRole.setText("Học viên");
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
    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAvatar;
        TextView tvName, tvRole, tvTitle,
                tvMonHoc, tvKhuVuc, tvThoiGian,
                tvNgayHoc, tvHinhThuc, tvHocPhi, tvNgayDang;

        public PostViewHolder(@NonNull View itemView) {
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
        }
    }
}
