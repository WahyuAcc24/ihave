package com.example.sony.tes.Model;

/**
 * Created by Wahyu ACC on 28/06/2018.
 */
public class Votes {
    private String nama;
    private String prodi;
    private String kriteria;

    public Votes(String nama, String prodi, String kriteria) {
        this.nama = nama;
        this.prodi = prodi;
        this.kriteria = kriteria;
    }

    public String getNama() {
        return nama;
    }

    public String getProdi() {
        return prodi;
    }

    public String getKriteria() {
        return kriteria;
    }
}
