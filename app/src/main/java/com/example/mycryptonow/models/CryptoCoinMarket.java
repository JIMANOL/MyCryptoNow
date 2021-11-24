package com.example.mycryptonow.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CryptoCoinMarket implements Serializable {

    private final static long serialVersionUID = -4369048252305703014L;
    @SerializedName("status")
    @Expose
    private Status status;

    @SerializedName("data")
    @Expose
    private ArrayList<Datum> data;



    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ArrayList<Datum> getData() {
        return data;
    }

    public void setData(ArrayList<Datum> data) {
        this.data = data;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
