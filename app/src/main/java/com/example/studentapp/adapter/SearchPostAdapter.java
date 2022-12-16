package com.example.studentapp.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentapp.model.Post;

import java.util.List;

public class SearchPostAdapter extends RecyclerView.Adapter<SearchPostAdapter.SearchPostViewHolder> {
    private List<Post> postList;

    @NonNull
    @Override
    public SearchPostAdapter.SearchPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchPostAdapter.SearchPostViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SearchPostViewHolder extends RecyclerView.ViewHolder {
        public SearchPostViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
