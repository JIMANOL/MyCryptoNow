package com.example.mycryptonow.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Quote implements Serializable
{

    private final static long serialVersionUID = -5780538494495942860L;
    @SerializedName("MXN")
    @Expose
    private MXN MXN;

    public MXN getMXN() {
        return MXN;
    }

    public void setMXN(MXN MXN) {
        this.MXN = MXN;
    }
}
