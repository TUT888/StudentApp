package com.example.studentapp.model;

import java.io.Serializable;

public class Post implements Serializable {
    private String id;
    private String title;
    private int status; /// 0: waiting, 1: class created, -1: cancelled, 2: edited
    private User user;
    //private Subject subject;
    private String subject;
    //private Field field;
    private String field;
    private String learningTimes;
    private String learningDates;
    private String learningPlaces;
    private String method; // online or offline
    private int tuition;
    private String description;
    private String postDate;
    private String hideFrom;

    public Post(String id, String title, int status, User user, String subject, String field, String learningTimes, String learningDates, String learningPlaces, String method, int tuition, String description, String postDate, String hideFrom) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.user = user;
        this.subject = subject;
        this.field = field;
        this.learningTimes = learningTimes;
        this.learningDates = learningDates;
        this.learningPlaces = learningPlaces;
        this.method = method;
        this.tuition = tuition;
        this.description = description;
        this.postDate = postDate;
        this.hideFrom = hideFrom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLearningTimes() {
        return learningTimes;
    }

    public void setLearningTimes(String learningTimes) {
        this.learningTimes = learningTimes;
    }

    public String getLearningDates() {
        return learningDates;
    }

    public void setLearningDates(String learningDates) {
        this.learningDates = learningDates;
    }

    public String getLearningPlaces() {
        return learningPlaces;
    }

    public void setLearningPlaces(String learningPlaces) {
        this.learningPlaces = learningPlaces;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getTuition() {
        return tuition;
    }

    public void setTuition(int tuition) {
        this.tuition = tuition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getHide() {
        return hideFrom;
    }

    public void addHide(String hidePhoneNumber) {
        if (hideFrom != null) {
            this.hideFrom = this.hideFrom + ", " + hidePhoneNumber;
        }
        else {
            this.hideFrom = hidePhoneNumber;
        }
    }
}
