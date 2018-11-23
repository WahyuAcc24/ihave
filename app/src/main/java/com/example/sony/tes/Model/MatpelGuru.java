package com.example.sony.tes.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SONY on 15/11/2018.
 */
public class MatpelGuru {

    @SerializedName("status") private String status;
    @SerializedName("data") private List<Data> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
