package com.example.studentapp.fragment;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentapp.MainActivity;
import com.example.studentapp.R;
import com.example.studentapp.app_interface.IClickTimeTableObject;
import com.example.studentapp.model.ClassObject;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private List<ClassObject> classes = new ArrayList<>();
    MainActivity mainActivity;
    TextView txtViewHome;
    LinearLayout linearLayoutHome;
    TableLayout thu2, thu3, thu4, thu5, thu6, thu7, cn;
    IClickTimeTableObject iClickTimeTableObject;

    public HomeFragment(IClickTimeTableObject iClickTimeTableObject) {
        this.iClickTimeTableObject = iClickTimeTableObject;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mainActivity = (MainActivity) getActivity();
        txtViewHome = view.findViewById(R.id.txtViewHome);
        linearLayoutHome = view.findViewById(R.id.linearLayoutHome);
        thu2 = view.findViewById(R.id.thu2);
        thu3 = view.findViewById(R.id.thu3);
        thu4 = view.findViewById(R.id.thu4);
        thu5 = view.findViewById(R.id.thu5);
        thu6 = view.findViewById(R.id.thu6);
        thu7 = view.findViewById(R.id.thu7);
        cn = view.findViewById(R.id.cn);
        classes.add(new ClassObject("02", "Name2", "012888813", "054683123", "TP.HCM",
                0, 200000, "Thứ 2: 9h - 10h", "18/12/2022", "24/12/2022", "Online",
                "CNTT", "CNTT"));
        if (classes.size() != 0) {
            linearLayoutHome.setVisibility(View.VISIBLE);
            txtViewHome.setVisibility(View.GONE);
            for (ClassObject classObject : classes) {
                String classTime = classObject.getDateTime();
                if (classTime.contains (", ")) {
                    String[] classDate = classTime.split(", ");
                    for (String dateTime : classDate) {
                        addSchedule(dateTime, classObject);
                    }
                }
                else {
                    addSchedule(classTime, classObject);
                }
            }
        }
        return view;
    }

    public void addSchedule (String dateTime, ClassObject classObject) {
        String date = dateTime.split(": ")[0];
        String time = dateTime.split(": ")[1];
        if (date.contains("Thứ 2")) {
            int childNum = thu2.getChildCount();
            TableRow tbr = new TableRow(mainActivity);
            TextView tg = new TextView(mainActivity);
            tg.setLayoutParams(new TableRow.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 3.0f));
            tg.setText(time);
            tg.setTextSize(20);
            tg.setGravity(Gravity.CENTER);
            tg.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tg.setTextColor(ContextCompat.getColor(mainActivity, R.color.text_color));
            tg.setBackgroundDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.bg_time_table));
            tbr.addView(tg);
            TextView tg2 = new TextView(mainActivity);
            tg2.setLayoutParams(new TableRow.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 8.0f));
            tg2.setTextSize(20);
            tg2.setGravity(Gravity.CENTER);
            tg2.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
            tg2.setText(classObject.getClassName());
            tg2.setTextColor(ContextCompat.getColor(mainActivity, R.color.text_color));
            tg2.setBackgroundDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.bg_time_table));
            tbr.addView(tg2);
            tbr.setVisibility(View.VISIBLE);
            tbr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iClickTimeTableObject.switchToClassFragment(classObject);
                }
            });
            thu2.addView(tbr, childNum);
        }
        if (date.contains("Thứ 3")) {
            int childNum = thu3.getChildCount();
            TableRow tbr = new TableRow(mainActivity);
            TextView tg = new TextView(mainActivity);
            tg.setLayoutParams(new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 4.0f));
            tg.setText(time);
            tg.setTextSize(20);
            tg.setGravity(Gravity.CENTER);
            tg.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tg.setTextColor(ContextCompat.getColor(mainActivity, R.color.text_color));
            tg.setBackgroundDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.bg_time_table));
            tbr.addView(tg);
            TextView tg2 = new TextView(mainActivity);
            tg2.setLayoutParams(new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 8.0f));
            tg2.setTextSize(20);
            tg2.setGravity(Gravity.CENTER_VERTICAL);
            tg2.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
            tg2.setText(classObject.getClassName());
            tg2.setTextColor(ContextCompat.getColor(mainActivity, R.color.text_color));
            tg2.setBackgroundDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.bg_time_table));
            tbr.addView(tg2);
            tbr.setVisibility(View.VISIBLE);
            tbr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iClickTimeTableObject.switchToClassFragment(classObject);
                }
            });
            thu3.addView(tbr, childNum);
        }
        if (date.contains("Thứ 4")) {
            int childNum = thu4.getChildCount();
            TableRow tbr = new TableRow(mainActivity);
            TextView tg = new TextView(mainActivity);
            tg.setLayoutParams(new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 3.0f));
            tg.setText(time);
            tg.setTextSize(20);
            tg.setGravity(Gravity.CENTER);
            tg.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tg.setTextColor(ContextCompat.getColor(mainActivity, R.color.text_color));
            tg.setBackgroundDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.bg_time_table));
            tbr.addView(tg);
            TextView tg2 = new TextView(mainActivity);
            tg2.setLayoutParams(new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 8.0f));
            tg2.setTextSize(20);
            tg2.setGravity(Gravity.CENTER_VERTICAL);
            tg2.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
            tg2.setText(classObject.getClassName());
            tg2.setTextColor(ContextCompat.getColor(mainActivity, R.color.text_color));
            tg2.setBackgroundDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.bg_time_table));
            tbr.addView(tg2);
            tbr.setVisibility(View.VISIBLE);
            tbr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iClickTimeTableObject.switchToClassFragment(classObject);
                }
            });
            thu4.addView(tbr, childNum);
        }
        if (date.contains("Thứ 5")) {
            int childNum = thu5.getChildCount();
            TableRow tbr = new TableRow(mainActivity);
            TextView tg = new TextView(mainActivity);
            tg.setLayoutParams(new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 3.0f));
            tg.setText(time);
            tg.setTextSize(20);
            tg.setGravity(Gravity.CENTER);
            tg.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tg.setTextColor(ContextCompat.getColor(mainActivity, R.color.text_color));
            tg.setBackgroundDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.bg_time_table));
            tbr.addView(tg);
            TextView tg2 = new TextView(mainActivity);
            tg2.setLayoutParams(new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 8.0f));
            tg2.setTextSize(20);
            tg2.setGravity(Gravity.CENTER_VERTICAL);
            tg2.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
            tg2.setText(classObject.getClassName());
            tg2.setTextColor(ContextCompat.getColor(mainActivity, R.color.text_color));
            tg2.setBackgroundDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.bg_time_table));
            tbr.addView(tg2);
            tbr.setVisibility(View.VISIBLE);
            tbr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iClickTimeTableObject.switchToClassFragment(classObject);
                }
            });
            thu5.addView(tbr, childNum);
        }
        if (date.contains("Thứ 6")) {
            int childNum = thu6.getChildCount();
            TableRow tbr = new TableRow(mainActivity);
            TextView tg = new TextView(mainActivity);
            tg.setLayoutParams(new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 3.0f));
            tg.setText(time);
            tg.setTextSize(20);
            tg.setGravity(Gravity.CENTER);
            tg.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tg.setTextColor(ContextCompat.getColor(mainActivity, R.color.text_color));
            tg.setBackgroundDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.bg_time_table));
            tbr.addView(tg);
            TextView tg2 = new TextView(mainActivity);
            tg2.setLayoutParams(new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 8.0f));
            tg2.setTextSize(20);
            tg2.setGravity(Gravity.CENTER_VERTICAL);
            tg2.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
            tg2.setText(classObject.getClassName());
            tg2.setTextColor(ContextCompat.getColor(mainActivity, R.color.text_color));
            tg2.setBackgroundDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.bg_time_table));
            tbr.addView(tg2);
            tbr.setVisibility(View.VISIBLE);
            tbr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iClickTimeTableObject.switchToClassFragment(classObject);
                }
            });
            thu6.addView(tbr, childNum);
        }
        if (date.contains("Thứ 7")) {
            int childNum = thu7.getChildCount();
            TableRow tbr = new TableRow(mainActivity);
            TextView tg = new TextView(mainActivity);
            tg.setLayoutParams(new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 3.0f));
            tg.setText(time);
            tg.setTextSize(20);
            tg.setGravity(Gravity.CENTER);
            tg.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tg.setTextColor(ContextCompat.getColor(mainActivity, R.color.text_color));
            tg.setBackgroundDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.bg_time_table));
            tbr.addView(tg);
            TextView tg2 = new TextView(mainActivity);
            tg2.setLayoutParams(new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 8.0f));
            tg2.setTextSize(20);
            tg2.setGravity(Gravity.CENTER_VERTICAL);
            tg2.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
            tg2.setText(classObject.getClassName());
            tg2.setTextColor(ContextCompat.getColor(mainActivity, R.color.text_color));
            tg2.setBackgroundDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.bg_time_table));
            tbr.addView(tg2);
            tbr.setVisibility(View.VISIBLE);
            tbr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iClickTimeTableObject.switchToClassFragment(classObject);
                }
            });
            thu7.addView(tbr, childNum);
        }
        if (date.contains("Chủ nhật")) {
            int childNum = cn.getChildCount();
            TableRow tbr = new TableRow(mainActivity);
            TextView tg = new TextView(mainActivity);
            tg.setLayoutParams(new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 3.0f));
            tg.setText(time);
            tg.setTextSize(20);
            tg.setGravity(Gravity.CENTER);
            tg.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tg.setTextColor(ContextCompat.getColor(mainActivity, R.color.text_color));
            tg.setBackgroundDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.bg_time_table));
            tbr.addView(tg);
            TextView tg2 = new TextView(mainActivity);
            tg2.setLayoutParams(new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 8.0f));
            tg2.setTextSize(20);
            tg2.setGravity(Gravity.CENTER_VERTICAL);
            tg2.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
            tg2.setText(classObject.getClassName());
            tg2.setTextColor(ContextCompat.getColor(mainActivity, R.color.text_color));
            tg2.setBackgroundDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.bg_time_table));
            tbr.addView(tg2);
            tbr.setVisibility(View.VISIBLE);
            tbr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iClickTimeTableObject.switchToClassFragment(classObject);
                }
            });
            cn.addView(tbr, childNum);
        }
    }
}