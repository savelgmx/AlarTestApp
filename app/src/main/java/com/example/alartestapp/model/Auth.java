package com.example.alartestapp.model;


import com.google.gson.annotations.SerializedName;


public class Auth {

    @SerializedName("status")
    private String status;
    @SerializedName("code")
    private String code;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}