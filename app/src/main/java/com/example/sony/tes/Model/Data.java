package com.example.sony.tes.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SONY on 14/9/2018.
 */
public class Data {

    @SerializedName("id") private int id;
    @SerializedName("category_id") private int category_id;
    @SerializedName("name") private String name;
    @SerializedName("subtotal") private String subtotal;

    public int getId() {
        return id;
    }

    public int setId() {
        return id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }


}
