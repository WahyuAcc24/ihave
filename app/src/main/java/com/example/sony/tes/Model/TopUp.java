package com.example.sony.tes.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SONY on 14/9/2018.
 */
public class TopUp {

    @SerializedName("murid_id") private String murid_id;
    @SerializedName("total") private String total;
    @SerializedName("subtotal") private String subtotal;
    @SerializedName("invoice") private String invoice;
    @SerializedName("data") private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

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
