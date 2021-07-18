package com.example.alartestapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DataResponse implements Serializable {

    @SerializedName("data")
    public List<Data.Datum> mData;

    public List<Data.Datum> getData(){
        return mData;
    }
    public void setData(List<Data.Datum> data){
        mData =data;
    }

}
