package com.example.studentapp.app_interface;

import com.google.gson.JsonObject;


public class ResultObjectAPI {
    private int code;
    private String message;
    private JsonObject data;

    public JsonObject getData() {
        return data;
    }

}
