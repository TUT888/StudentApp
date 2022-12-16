package com.example.studentapp.extra_fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.studentapp.MainActivity;
import com.example.studentapp.R;
import com.example.studentapp.model.Post;
import com.example.studentapp.model.User;
import com.google.android.material.button.MaterialButton;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddNewPostFragment extends Fragment {
    // Resources
    private View mView;
    private ImageButton ibBack;
    private MaterialButton btnPost;

    private EditText etTitle, etField, etSubject, etTime, etPlace, etTuition;
    private RadioGroup rgMethod;
    //private RadioButton rbOnline, rbOffline;
    private CheckBox cbMonday, cbTuesday, cbWednesday, cbThursday, cbFriday, cbSaturday, cbSunday;

    // Data
    private User currentUser;
    private String[] choiceOfPlaces = MainActivity.PLACES_TO_CHOOSE;
    private boolean[] checkedPlaces = new boolean[choiceOfPlaces.length];
    private String[] selectedPlaces = new String[choiceOfPlaces.length];

    private String[] choiceOfFields = MainActivity.FIELDS_TO_CHOOSE;
    private boolean[] checkedFields = new boolean[choiceOfFields.length];
    private String[] selectedFields = new String[choiceOfFields.length];

    public AddNewPostFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_add_new_post, container, false);
        bindView();
        return mView;
    }

    private void bindView() {
        ibBack = mView.findViewById(R.id.ibBack);
        btnPost = mView.findViewById(R.id.btnPost);

        etTitle = mView.findViewById(R.id.etTitle);
        etField = mView.findViewById(R.id.etField);
        etSubject = mView.findViewById(R.id.etSubject);
        etTime = mView.findViewById(R.id.etTime);
        etPlace = mView.findViewById(R.id.etPlace);
        etTuition = mView.findViewById(R.id.etTuition);

        rgMethod = mView.findViewById(R.id.rgMethod);
        cbMonday = mView.findViewById(R.id.cbMonday);
        cbTuesday = mView.findViewById(R.id.cbTuesday);
        cbWednesday = mView.findViewById(R.id.cbWednesday);
        cbThursday = mView.findViewById(R.id.cbThursday);
        cbFriday = mView.findViewById(R.id.cbFriday);
        cbSaturday = mView.findViewById(R.id.cbSaturday);
        cbSunday = mView.findViewById(R.id.cbSunday);

        etField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChoicesOfFields();
            }
        });
        etPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChoicesOfPlaces();
            }
        });


        // Buttons
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewPost(view);
            }
        });
    }

    private void showChoicesOfPlaces() {
        hideSoftKeyboard();
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext());
        alertBuilder.setTitle("Chọn khu vực");

        if (etPlace.getText().toString().isEmpty()) {
            etPlace.setText(choiceOfPlaces[0]);    //default
        }

        alertBuilder.setMultiChoiceItems(choiceOfPlaces, checkedPlaces, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                if (b) {
                    selectedPlaces[i] = choiceOfPlaces[i];
                    checkedPlaces[i] = true;
                } else {
                    selectedPlaces[i] = null;
                    checkedPlaces[i] = false;
                }
            }
        });
        alertBuilder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String placeString = String.join(", ", selectedPlaces);
                placeString = placeString.replaceAll(", null", "");
                placeString = placeString.replaceAll("null, ", "");

                if (placeString.equals("null")) {
                    etPlace.setText("");
                } else {
                    etPlace.setText(placeString);
                }

            }
        });
        alertBuilder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertBuilder.show();
    }

    private void showChoicesOfFields() {
        hideSoftKeyboard();
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext());
        alertBuilder.setTitle("Chọn lĩnh vực dạy");

        if (etField.getText().toString().isEmpty()) {
            etField.setText(choiceOfFields[0]);    //default
        }

        alertBuilder.setMultiChoiceItems(choiceOfFields, checkedFields, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                if (b) {
                    selectedFields[i] = choiceOfFields[i];
                    checkedFields[i] = true;
                } else {
                    selectedFields[i] = null;
                    checkedFields[i] = false;
                }
            }
        });
        alertBuilder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String fieldString = String.join(", ", selectedFields);
                fieldString = fieldString.replaceAll(", null", "");
                fieldString = fieldString.replaceAll("null, ", "");

                if (fieldString.equals("null")) {
                    etPlace.setText("");
                } else {
                    etPlace.setText(fieldString);
                }

            }
        });
        alertBuilder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertBuilder.show();
    }

    private void addNewPost(View view) {
        // Get dates
        String inputTitle = etTitle.getText().toString();
        String inputField = etField.getText().toString();
        String inputSubject = etSubject.getText().toString();
        String inputMethod = getInputMethodString();
        String inputDate = getInputMethodString();
        String inputTime = etTime.getText().toString();
        String inputPlace = etPlace.getText().toString();
        int inputTuition = Integer.parseInt(etTuition.getText().toString());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddmmyy-hhmmss");
        String id = "P"+dtf.format(LocalDateTime.now());
        //Post newPost = new Post(id, );

        Toast.makeText(getContext(), inputMethod + "\n" + inputDate, Toast.LENGTH_LONG).show();
    }

    private String getInputDateString() {
        String[] selectedDates = new String[7];
        if (cbMonday.isChecked())
            selectedDates[0] = "Thứ 2";
        if (cbTuesday.isChecked())
            selectedDates[1] = "Thứ 3";
        if (cbWednesday.isChecked())
            selectedDates[2] = "Thứ 4";
        if (cbThursday.isChecked())
            selectedDates[3] = "Thứ 5";
        if (cbFriday.isChecked())
            selectedDates[4] = "Thứ 6";
        if (cbSaturday.isChecked())
            selectedDates[5] = "Thứ 7";
        if (cbSunday.isChecked())
            selectedDates[6] = "Chủ nhật";
        String dateString = String.join(", ", selectedDates);
        dateString = dateString.replaceAll(", null", "");
        dateString = dateString.replaceAll("null, ", "");
        if (dateString.equals("null")) {
            return "";
        }
        return dateString;
    }

    private String getInputMethodString() {
        int radioId = rgMethod.getCheckedRadioButtonId();
        switch (radioId) {
            case R.id.rbOffline:
                return "online";
            case R.id.rbOnline:
                return "offline";
            default:
                return "";
        }
    }

    // Ẩn bàn phím sau khi nhập dữ liệu
    public void hideSoftKeyboard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    // Fetch API
    private void getUserData() {
        User u = new User("0908888338", "Vương Hải Đăng", 1, "Quận 1", 0, "20/12/1998", "vhd@gmail.com", 7000008, "123456");
        currentUser = u;
    }

}