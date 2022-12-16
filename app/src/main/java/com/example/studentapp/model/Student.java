package com.example.studentapp.model;

public class Student extends User{

    public Student() {
        super();
    }

    public Student(String phoneNumber, String name, int status, String address, int gender, String birthday, String email, int avatar, String password) {
        super(phoneNumber, name, status, address, gender, birthday, email, avatar, password);
    }
}
