package com.example.sony.tes.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SONY on 5/9/2018.
 */
public class Matpel {
    @SerializedName("id") private int id;
    @SerializedName("name") private String name;
    @SerializedName("description") private String description;
    @SerializedName("guru") private List<Guru> guru;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Guru> getGuru() {
        return guru;
    }
}
