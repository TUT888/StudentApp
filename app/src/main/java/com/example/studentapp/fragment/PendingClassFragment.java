package com.example.studentapp.fragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.studentapp.R;
import com.example.studentapp.adapter.PendingClassAdapter;
import com.example.studentapp.adapter.SearchViewPagerAdapter;
import com.example.studentapp.app_interface.IClickPendingClassListener;
import com.example.studentapp.model.ClassObject;

import java.util.ArrayList;


public class PendingClassFragment extends Fragment {

    private RecyclerView rcvPendingClass;
    private ArrayList<ClassObject> pendingClassArrayList;
    private PendingClassAdapter pendingClassAdapter;

    public PendingClassFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_pending_class, container, false);
        rcvPendingClass = mView.findViewById(R.id.rcvPendingClass);
        pendingClassArrayList = initClass();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvPendingClass.setLayoutManager(linearLayoutManager);

        pendingClassAdapter = new PendingClassAdapter(new IClickPendingClassListener() {
            @Override
            public void onClickAcceptPendingClass(ClassObject classObject) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Bạn chắc chắn muốn tham gia lớp học này không?")
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getContext(), "Đã tham gia lớp học", Toast.LENGTH_SHORT).show();
                                pendingClassAdapter.remove(classObject);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                builder.show();
            }

            @Override
            public void onClickCancelPendingClass(ClassObject classObject) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Bạn chắc chắn muốn từ chối tham gia lớp học này không?")
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getContext(), "Đã từ chối tham gia lớp học", Toast.LENGTH_SHORT).show();
                                pendingClassAdapter.remove(classObject);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                builder.show();
            }
        });
        pendingClassAdapter.setData(pendingClassArrayList);
        rcvPendingClass.setAdapter(pendingClassAdapter);
        return mView;

    }

    private ArrayList<ClassObject> initClass() {
        ArrayList<ClassObject> classObjects = new ArrayList<>();
        classObjects.add(new ClassObject("P21112022115612", "Lớp Hóa học Cơ bản lớp 8","099886770908", "0998878878", "15, Trần Thánh Tông, Quận 2", -1, 350000, "Thứ 2: 7h30-9h30, Thứ 5: 7h30-9h30", "15/12/2022", "15/2/2022", "offline", "Hóa học lớp 8", "Hóa học"));
        classObjects.add(new ClassObject("P22122022115612", "Lớp Ngữ Văn cấp tốc lớp 12","0998888888", "0998878878", "15, Trần Thánh Tông, Quận 2", -1, 900000, "Thứ 3: 7h30-9h30, Chủ nhật: 7h30-9h30", "15/12/2022", "15/2/2022", "offline", "Ngữ văn lớp 8", "Ngữ văn"));
        return classObjects;

    }
}