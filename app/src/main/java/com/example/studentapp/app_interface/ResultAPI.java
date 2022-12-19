package com.example.studentapp.app_interface;

import com.google.gson.JsonArray;


public class ResultAPI {
    private int code;
    private String message;
    private JsonArray data;

    public int getCode() {
        return code;
    }

    public JsonArray getData() {
        return data;
    }

}
