package com.example.studentapp.search;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studentapp.MainActivity;
import com.example.studentapp.R;
import com.example.studentapp.adapter.SearchTutorAdapter;
import com.example.studentapp.app_interface.IClickTutorObjectListener;
import com.example.studentapp.fragment.MyPostFragment;
import com.example.studentapp.model.Tutor;

import java.util.ArrayList;


public class SearchTutorFragment extends Fragment {

    private View mView;
    private MainActivity mMainActivity;
    private RecyclerView rcvSearchTutor;
    private SearchView svSearchTutor;
    private SearchTutorAdapter searchTutorAdapter;
    private ArrayList<Tutor> searchTutorArrayList;

    public SearchTutorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_search_tutor, container, false);
        mMainActivity = (MainActivity) getActivity();
        rcvSearchTutor = mView.findViewById(R.id.rcvSearchTutor);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvSearchTutor.setLayoutManager(linearLayoutManager);
        svSearchTutor = mView.findViewById(R.id.svSearchTutor);
        searchTutorArrayList = initTutor();
        searchTutorAdapter = new SearchTutorAdapter(new IClickTutorObjectListener() {
            @Override
            public void onClickTutorObject(Tutor tutor) {
                mMainActivity.goToTutorDetailFragment(tutor, SearchTutorFragment.class.getSimpleName());
            }

            @Override
            public void onClickBtnHideTutor(Tutor tutor) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Bạn có muốn người dùng này không?")
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                searchTutorAdapter.remove(tutor);
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
        searchTutorAdapter.setData(searchTutorArrayList);
        rcvSearchTutor.setAdapter(searchTutorAdapter);

        svSearchTutor.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
        return mView;
    }

    private ArrayList<Tutor> initTutor() {
        ArrayList <Tutor> tutors = new ArrayList<>();
        tutors.add(new Tutor("0916876678", "Nguyễn Trần Trung Quân", 1, "Quận 1", 0, "12/09/1990", "nttq@gmail.com", "nttq1998", "Đại học Tôn Đức Thắng", "Thạc Sĩ", "Công nghệ Thông Tin, Toán", "Quận 1, Quận 2, Quận 3"));
        tutors.add(new Tutor("0887665431", "Lan Hương", 1, "Quận Tân Bình", 1, "08/09/2001", "lh@gmail.com", "ln@@@", "Đại học KHHH và Nhân Văn", "Sinh Viên", "Ngoại ngữ", "Quận Tân Bình, Quận Thủ Đức"));
        return tutors;
    }

    private void filterList(String newText) {
        ArrayList<Tutor> filteredList = new ArrayList<>();
        for (Tutor tutor : searchTutorArrayList) {
            if (tutor.getName().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(tutor);
            }
            else if (tutor.getFields().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(tutor);
            }

            else if (tutor.getAreas().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(tutor);
            }
        }
        searchTutorAdapter.setData(filteredList);
    }
}