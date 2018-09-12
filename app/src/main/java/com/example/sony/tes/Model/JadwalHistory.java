package com.example.sony.tes.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SONY on 12/9/2018.
 */
public class JadwalHistory {

    @SerializedName("date") private String date;
    @SerializedName("day") private String day;
    @SerializedName("hours") private String hours;

    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    public String getHours() {
        return hours;
    }
}
