package com.example.sony.tes.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SONY on 23/8/2018.
 */
public class Kelas {


    @SerializedName("id")
    public int id;

    @SerializedName("data")
    public String data;

    @SerializedName("code")
    public String code;

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;


    public int getId() {
        return id;
    }




    @Override
    public String toString(){
        return data;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
