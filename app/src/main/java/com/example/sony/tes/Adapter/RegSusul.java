package com.example.sony.tes.Adapter;

/**
 * Created by Wahyu ACC on 10/05/2017.
 */
public class RegSusul {
    private String email, nama, password, hp, jk, tempatLLahir, tanggalLahir;

    public RegSusul() {

    }

    public RegSusul(String email, String nama, String password, String hp , String jk, String tempatLLahir, String tanggalLahir) {
        this.email = email;
        this.nama = nama;
        this.password = password;
        this.hp = hp;
        this.jk = jk;
        this.tempatLLahir = tempatLLahir;
        this.tanggalLahir = tanggalLahir;


    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getTempatLLahir() {
        return tempatLLahir;
    }

    public void setTempatLLahir(String tempatLLahir) {
        this.tempatLLahir = tempatLLahir;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }
}
