package com.example.studentapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.text.format.DateFormat;
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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentapp.MainActivity;
import com.example.studentapp.api.APIService;
import com.example.studentapp.api.ResultAPI;
import com.example.studentapp.api.ResultObjectAPI;
import com.example.studentapp.app_interface.IClickBtnRating;
import com.example.studentapp.extra_fragment.PostDetailFragment;
import com.example.studentapp.R;
import com.example.studentapp.model.ClassObject;
import com.example.studentapp.model.Rate;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.JsonObject;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.List;

import retrofit2.Retrofit;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder>{

    private List<ClassObject> classes;
    private IClickBtnRating iClickBtnRating;
    private Context mainActivity;

    public ClassAdapter (IClickBtnRating iClickBtnRating) {
        this.iClickBtnRating = iClickBtnRating;
    }

    public void setData(List<ClassObject> classesList) {
        this.classes = classesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_classes, parent, false);
        return new ClassViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        ClassObject mClass = classes.get(position);
        if (mClass == null) {
            return;
        }
        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setMaximumFractionDigits(0);
        format.setCurrency(Currency.getInstance("VND"));

        this.mainActivity = (Context) holder.classField.getContext();
        getTutorInfo(mClass.getTutorPhone(), holder);

        holder.className.setText(mClass.getClassName());
        holder.classPlace.setText(mClass.getPlace());
        holder.classFee.setText(format.format(mClass.getFee()));
        holder.classTime.setText(mClass.getDateTime());
        holder.classStartDate.setText(mClass.getStartDate());
        holder.classEndDate.setText(mClass.getEndDate());
        holder.classMethod.setText(mClass.getMethod());
        holder.classSubject.setText(mClass.getSubject());
        holder.classField.setText(mClass.getField());
        if (mClass.getStatus() == 1) {
            holder.classStatus.setTextColor(ContextCompat.getColor(holder.className.getContext(), R.color.waiting));
            holder.classStatus.setText("Lưu trữ");
            holder.classRate.setText("Đánh giá");
            holder.classRate.setVisibility(View.VISIBLE);
        }
        else if (mClass.getStatus() == 2) {
            holder.classStatus.setTextColor(ContextCompat.getColor(holder.className.getContext(), R.color.close));
            holder.classStatus.setText("Đã đánh giá");
            holder.classRate.setText("Xem đánh giá");
            holder.classRate.setVisibility(View.VISIBLE);
        }
        else {
            holder.classStatus.setTextColor(ContextCompat.getColor(holder.className.getContext(), R.color.active));
            holder.classStatus.setText("Đang học");
            holder.classRate.setVisibility(View.INVISIBLE);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate endDate = LocalDate.parse(mClass.getEndDate(), formatter);
        if (LocalDate.now().isAfter(endDate)) {
            if (mClass.getStatus() == 0) {
                mClass.setStatus(1);
                holder.classStatus.setTextColor(ContextCompat.getColor(holder.className.getContext(), R.color.waiting));
                holder.classStatus.setText("Lưu trữ");
                holder.classRate.setText("Đánh giá");
                holder.classRate.setVisibility(View.VISIBLE);
            }
        }

        holder.classRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mClass.getStatus() == 1) {
                    iClickBtnRating.rateClass(mClass, holder.getBindingAdapterPosition());
                }
                else {
                    getRating(mClass.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (classes != null) {
            return classes.size();
        }
        return 0;
    }

    public void changeClassStatus (int position) {
        classes.get(position).setStatus(2);
        notifyItemChanged(position);
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder {
        private TextView className, classTutor, classTime, classPlace, classFee, classStatus, classStartDate, classEndDate,
        classMethod, classSubject, classField;
        private Button classRate;

        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);

            className = itemView.findViewById(R.id.className);
            classTutor = itemView.findViewById(R.id.classTutor);
            classTime = itemView.findViewById(R.id.classTime);
            classPlace = itemView.findViewById(R.id.classPlace);
            classFee = itemView.findViewById(R.id.classFee);
            classStatus = itemView.findViewById(R.id.classStatus);
            classRate = itemView.findViewById(R.id.classRate);
            classStartDate = itemView.findViewById(R.id.classStartDate);
            classEndDate = itemView.findViewById(R.id.classEndDate);
            classMethod = itemView.findViewById(R.id.classMethod);
            classSubject = itemView.findViewById(R.id.classSubject);
            classField = itemView.findViewById(R.id.classField);
        }
    }

    private void getTutorInfo (String tutorPhone, ClassViewHolder holder) {
        APIService.apiService.getUser(tutorPhone).enqueue(new retrofit2.Callback<ResultObjectAPI>() {
            @Override
            public void onResponse(retrofit2.Call<ResultObjectAPI> call, retrofit2.Response<ResultObjectAPI> response) {
                ResultObjectAPI resultAPI = response.body();
                if(response.isSuccessful() && resultAPI != null){
                    if (resultAPI.getCode() == 0){
                        JsonObject jsonObject = resultAPI.getData().getAsJsonObject();
                        String tutorName = jsonObject.get("name").getAsString();
                        holder.classTutor.setText(tutorName);
                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ResultObjectAPI> call, Throwable t) {
                Toast.makeText(mainActivity, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                Log.d("onFailure", "onFailure: " + t.getMessage());
            }
        });
    }

    private void getRating (String classId) {
        APIService.apiService.getRatingByClassID(classId).enqueue(new retrofit2.Callback<ResultObjectAPI>() {
            @Override
            public void onResponse(retrofit2.Call<ResultObjectAPI> call, retrofit2.Response<ResultObjectAPI> response) {
                ResultObjectAPI resultAPI = response.body();
                if(response.isSuccessful() && resultAPI != null){
                    if (resultAPI.getCode() == 0){
                        JsonObject jsonObject = resultAPI.getData().getAsJsonObject();
                        Rate rate = new Rate(jsonObject.get("classID").getAsString(), jsonObject.get("rate").getAsFloat(),
                                jsonObject.get("comment").getAsString(), jsonObject.get("date").getAsString());
                        iClickBtnRating.seeRateDetail(rate);
                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ResultObjectAPI> call, Throwable t) {
                Toast.makeText(mainActivity, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                Log.d("onFailure", "onFailure: " + t.getMessage());
            }
        });
    }
}
