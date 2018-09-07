package com.example.sony.tes.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SONY on 6/9/2018.
 */
public class Jadwal {

    @SerializedName("day_name")
    public String day_name;

    @SerializedName("day_number")
    public int day_number;

    @SerializedName("hours")
    private List<Integer> hours;

    public String getDay_name() {
        return day_name;
    }

    public int getDay_number() {
        return day_number;
    }

    public List<Integer> getHours() {
        return hours;
    }
}
