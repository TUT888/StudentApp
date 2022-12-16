package com.example.studentapp.model;

public class Tutor extends User {
    private String school;
    private String academicLevel;

    public Tutor() {

    }

    public Tutor(String phoneNumber, String name, int status, String address, int gender, String birthday, String email, int avatar, String password) {
        super(phoneNumber, name, status, address, gender, birthday, email, avatar, password);
        this.school = "";
        this.academicLevel = "";
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getAcademicLevel() {
        return academicLevel;
    }

    public void setAcademicLevel(String academicLevel) {
        this.academicLevel = academicLevel;
    }
}
