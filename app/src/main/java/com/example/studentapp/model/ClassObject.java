package com.example.studentapp.model;

public class ClassObject {
    String id;
    String className;
    String tutorPhone;
    String studentPhone;
    String place;
    int status; ///0: available, 1: archived
    int fee;
    String dateTime;
    String startDate;
    String endDate;

    public ClassObject(String id, String className, String tutorPhone, String studentPhone, String place, int status, int fee, String dateTime, String startDate, String endDate) {
        this.id = id;
        this.className = className;
        this.tutorPhone = tutorPhone;
        this.studentPhone = studentPhone;
        this.place = place;
        this.status = status;
        this.fee = fee;
        this.dateTime = dateTime;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTutorPhone() {
        return tutorPhone;
    }

    public void setTutorPhone(String tutorPhone) {
        this.tutorPhone = tutorPhone;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
