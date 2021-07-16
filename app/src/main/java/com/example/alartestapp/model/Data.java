package com.example.alartestapp.model;


import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Data {

    @SerializedName("status")
    private String status;
    @SerializedName("page")
    private Integer page;
    @SerializedName("data")
    private List<Datum> data ;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getPage() {
        return page;
    }
    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Datum> getData() {
        return data;
    }
    public void setData(List<Datum> data) {
        this.data = data;
    }


}
