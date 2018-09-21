package com.example.sony.tes.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SONY on 12/9/2018.
 */
public class JadwalHistory {

    @SerializedName("date") private String date;
    @SerializedName("day_name") private String day_name;
    @SerializedName("hours") private String hours;

    public String getDate() {
        return date;
    }

    public String getDay_name() {
        return day_name;
    }

    public String getHours() {
        return hours;
    }
}
