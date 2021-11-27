package com.example.mycryptonow.models;

import com.google.firebase.database.DataSnapshot;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Quote implements Serializable
{

    private final static long serialVersionUID = -5780538494495942860L;
    @SerializedName("MXN")
    @Expose
    private MXN mxn;

    public MXN getMxn() {
        return mxn;
    }

    public void setMxn(MXN mxn) {
        this.mxn = mxn;
    }

    public void fromSnapShot(DataSnapshot quote) {
        mxn = new MXN();
        mxn.fromSnapshot(quote.child("mxn"));
    }
}
