package com.example.studentapp.adapter;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentapp.extra_fragment.PostDetailFragment;
import com.example.studentapp.R;
import com.example.studentapp.model.ClassObject;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder>{

    private List<ClassObject> classes;

    public ClassAdapter (List<ClassObject> classesList) {
        this.classes = classesList;
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_classes, parent, false);
        return new ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        ClassObject mClass = classes.get(position);
        if (mClass == null) {
            return;
        }

        holder.className.setText(mClass.getClassName());
        holder.classTutor.setText(mClass.getTutorPhone());
        holder.classPlace.setText(mClass.getPlace());
        holder.classFee.setText(mClass.getFee());
        holder.classStatus.setText(mClass.getStatus());
        holder.classTime.setText(mClass.getDateTime());
        if (mClass.getStatus() == "Hoàn thành") {
            holder.classRate.setVisibility(View.VISIBLE);
        }
        else {
            holder.classRate.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        if (classes != null) {
            return classes.size();
        }
        return 0;
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder {
        private TextView className, classTutor, classTime, classPlace, classFee, classStatus;
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
        }
    }
}
