package com.example.sony.tes.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SONY on 6/9/2018.
 */
public class DetailOrder {

    @SerializedName("fullname")
    public String fullname;

    @SerializedName("birthdate")
    public String birthdate;

    @SerializedName("gender")
    public String gender;

    @SerializedName("address")
    public String address;

    @SerializedName("profile")
    public String profile;

    @SerializedName("lulusan")
    public String lulusan;

    @SerializedName("hobby")
    public String hobby;

    @SerializedName("pelajaran")
    public String pelajaran;

    @SerializedName("price")
    public int price;

    @SerializedName("jadwal") private List<Jadwal> jadwal;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getLulusan() {
        return lulusan;
    }

    public void setLulusan(String lulusan) {
        this.lulusan = lulusan;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getPelajaran() {
        return pelajaran;
    }

    public void setPelajaran(String pelajaran) {
        this.pelajaran = pelajaran;
    }

    public List<Jadwal> getJadwal() {
        return jadwal;
    }

    public void setJadwal(List<Jadwal> jadwal) {
        this.jadwal = jadwal;
    }

}
