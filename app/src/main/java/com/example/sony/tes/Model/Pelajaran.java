package com.example.sony.tes.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SONY on 28/8/2018.
 */
public class Pelajaran{

    @SerializedName("pelajaran") private String pelajaran;

    public String getPelajaran() {
        return pelajaran;
    }

    public void setPelajaran(String pelajaran) {
        this.pelajaran = pelajaran;
    }
}