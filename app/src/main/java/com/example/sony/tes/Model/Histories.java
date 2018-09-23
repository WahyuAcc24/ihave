package com.example.sony.tes.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by isfaaghyth on 9/23/18.
 * github: @isfaaghyth
 */

public class Histories<M> {
    @SerializedName("status") private boolean status;
    @SerializedName("data") private List<M> data;

    public boolean isStatus() {
        return status;
    }

    public List<M> getData() {
        return data;
    }
}
