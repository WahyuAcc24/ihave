package com.example.sony.tes.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Wahyu ACC on 12/04/2018.
 */
public class mhs {

    @SerializedName("id")
    public int id;

    @SerializedName("nama_mhs")
    public String nama_mhs;

    @SerializedName("npm")
    public String npm;

    @SerializedName("jurusan")
    public String jurusan;

    @SerializedName("vote")
    public String vote;

    @SerializedName("visi")
    public String visi;

    @SerializedName("misi")
    public String misi;


    @SerializedName("password")
    public String password;

    @SerializedName("images")
    public String images;

    @SerializedName("total_semua")
    public String total_semua;

    @SerializedName("total_belum")
    public String total_belum;

    @SerializedName("total_sudah")
    public String total_sudah;


    public int getId() {
        return id;
    }

    @Override
    public String toString(){
        return nama_mhs;
    }

    public String getNama_mhs() {
        return nama_mhs;
    }

    public void setNama_mhs(String nama_mhs) {
        this.nama_mhs = nama_mhs;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public String getVisi() {
        return visi;
    }

    public void setVisi(String visi) {
        this.visi = visi;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getTotal_semua() {
        return total_semua;
    }

    public void setTotal_semua(String total_semua) {
        this.total_semua = total_semua;
    }

    public String getTotal_belum() {
        return total_belum;
    }

    public void setTotal_belum(String total_belum) {
        this.total_belum = total_belum;
    }

    public String getTotal_sudah() {
        return total_sudah;
    }

    public void setTotal_sudah(String total_sudah) {
        this.total_sudah = total_sudah;
    }

    public String getMisi() {
        return misi;
    }

    public void setMisi(String misi) {
        this.misi = misi;
    }
}
