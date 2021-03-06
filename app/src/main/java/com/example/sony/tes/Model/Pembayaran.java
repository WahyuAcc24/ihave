package com.example.sony.tes.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by isfaaghyth on 9/14/18.
 * github: @isfaaghyth
 */

public class Pembayaran {
    @SerializedName("invoice") private String invoice;
    @SerializedName("due_date") private String due_date;
    @SerializedName("data") private Data data;

    public String getInvoice() {
        return invoice;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public Data getData() {
        return data;
    }

    public static class Data {
        @SerializedName("subtotal") private int subtotal;

        public int getSubtotal() {
            return subtotal;
        }
    }
}
