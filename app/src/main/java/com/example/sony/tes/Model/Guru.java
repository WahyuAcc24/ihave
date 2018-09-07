package com.example.sony.tes.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SONY on 5/9/2018.
 */
public class Guru {
    @SerializedName("id") private String id;
    @SerializedName("fullname") private String fullname;
    @SerializedName("pelajaran") private String pelajaran;
    @SerializedName("hobby") private String hobby;
    @SerializedName("lulusan") private String lulusan;

    public String getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPelajaran() {
        return pelajaran;
    }

    public String getHobby() {
        return hobby;
    }

    public String getLulusan() {
        return lulusan;
    }
}
