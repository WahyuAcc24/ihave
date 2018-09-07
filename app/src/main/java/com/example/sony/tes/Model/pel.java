package com.example.sony.tes.Model;

import com.google.gson.annotations.SerializedName;

import java.sql.Array;

/**
 * Created by SONY on 28/8/2018.
 */
public class pel {

    @SerializedName("id")
    public int id;

    @SerializedName("category_id")
    public int category_id;

    @SerializedName("name")
    public String name;

    @SerializedName("lesson")
    public Array lesson;


    public int getCategory_Id() {
        return category_id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString(){
        return name;
    }

    public String getName() {
        return name;
    }


    public Array getLesson() {
        return lesson;
    }

    public void setLesson(Array lesson) {
        this.lesson = lesson;
    }

    public String setName(String name) {
        return name;
    }

    public int getCategory_id() {
        return category_id;
    }

}
