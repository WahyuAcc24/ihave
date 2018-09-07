package com.example.sony.tes.Model;

/**
 * Created by SONY on 28/8/2018.
 */
public class Pelajaran {
    private int id;
    private String name;



    public Pelajaran(int id, String name) {
        this.id = id;
        this.name = name;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
