package com.example.alartestapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
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


    public static class Datum implements Serializable {

        @SerializedName("id")
        private String id;
        @SerializedName("name")
         private String name;
        @SerializedName("country")
        private String country;
        @SerializedName("lat")
        private Double lat;
        @SerializedName("lon")
        private Double lon;

        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getCountry() {
            return country;
        }
        public void setCountry(String country) {
            this.country = country;
        }
        public Double getLat() {
            return lat;
        }
        public void setLat(Double lat) {
            this.lat = lat;
        }
        public Double getLon() {
            return lon;
        }
        public void setLon(Double lon) {
            this.lon = lon;
        }

    }


}


