package com.example.sony.tes.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SONY on 14/9/2018.
 */
public class Data {

    @SerializedName("subtotal") private String subtotal;

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }
}
