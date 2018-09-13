package com.example.sony.tes.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SONY on 14/9/2018.
 */
public class TopUp {

    @SerializedName("murid_id") private String murid_id;
    @SerializedName("total") private String total;
    @SerializedName("subtotal") private String subtotal;

    public String getMurid_id() {
        return murid_id;
    }

    public void setMurid_id(String murid_id) {
        this.murid_id = murid_id;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }
}
