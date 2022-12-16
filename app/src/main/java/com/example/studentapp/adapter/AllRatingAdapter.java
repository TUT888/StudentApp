package com.example.studentapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentapp.R;
import com.example.studentapp.app_interface.IClickBtnRating;
import com.example.studentapp.model.ClassObject;
import com.example.studentapp.model.Rate;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class AllRatingAdapter extends RecyclerView.Adapter<AllRatingAdapter.RatingViewHolder>{

    private List<Rate> ratings;

    public AllRatingAdapter (List<Rate> ratings) {
        this.ratings = ratings;
    }

    @NonNull
    @Override
    public RatingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.classes_rating_object, parent, false);
        return new RatingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RatingViewHolder holder, int position) {
        Rate rating = ratings.get(position);
        if (ratings == null) {
            return;
        }

        holder.rateClassName.setText("");
        holder.rateTutor.setText("");
        holder.rateCmt.setText("");
        holder.rateDate.setText("");
        holder.rateStarNum.setText("" + rating.getRate());

        int j = 1;
        for (int i = 0; i < holder.rateStars.getChildCount(); i++) {
            if (rating.getRate() % 1 != 0) {
                if (j <= Math.floor(rating.getRate())) {
                    ImageView imageView = (ImageView) holder.rateStars.getChildAt(i);
                    imageView.setImageResource(R.drawable.ic_rating_full_star);
                }
                else if (j == Math.floor(rating.getRate())+1) {
                    ImageView imageView = (ImageView) holder.rateStars.getChildAt(i);
                    imageView.setImageResource(R.drawable.ic_rating_half_star);
                }
                else {
                    ImageView imageView = (ImageView) holder.rateStars.getChildAt(i);
                    imageView.setImageResource(R.drawable.ic_rating_no_star);
                }
                j++;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (ratings != null) {
            return ratings.size();
        }
        return 0;
    }

    public class RatingViewHolder extends RecyclerView.ViewHolder {
        private TextView rateClassName, rateTutor, rateStarNum, rateCmt, rateDate;
        private LinearLayout rateStars;

        public RatingViewHolder(@NonNull View itemView) {
            super(itemView);

            rateClassName = itemView.findViewById(R.id.rateClassName);
            rateTutor = itemView.findViewById(R.id.rateTutor);
            rateStarNum = itemView.findViewById(R.id.rateStarNum);
            rateCmt = itemView.findViewById(R.id.rateCmt);
            rateDate = itemView.findViewById(R.id.rateDate);
            rateStars = itemView.findViewById(R.id.rateStars);
        }
    }
}