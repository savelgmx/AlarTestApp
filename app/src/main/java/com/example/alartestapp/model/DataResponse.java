package com.example.alartestapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DataResponse implements Serializable {

    @SerializedName("data")
    private List<Data> mData;

    public List<Data> getData(){
        return mData;
    }
    public void setData(List<Data> data){
        mData =data;
    }

}
