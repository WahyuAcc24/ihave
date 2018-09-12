package com.example.sony.tes.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SONY on 12/9/2018.
 */
public class History {

    @SerializedName("invoice")
    private String invoice;

    @SerializedName("status")
    private String status;

    @SerializedName("nama_murid")
    private String nama_murid;

    @SerializedName("nama_guru")
    private String nama_guru;

    @SerializedName("nama_pelajaran")
    private String nama_pelajaran;

    @SerializedName("order_date")
    private String order_date;

    @SerializedName("total")
    private String total;

    @SerializedName("discount")
    private String discount;

    @SerializedName("subtotal")
    private int subtotal;

    @SerializedName("payment_method")
    private String payment_method;

    @SerializedName("jadwal")
    private List<JadwalHistory> jadwal;

    public List<JadwalHistory> getJadwal() {
        return jadwal;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNama_murid() {
        return nama_murid;
    }

    public void setNama_murid(String nama_murid) {
        this.nama_murid = nama_murid;
    }

    public String getNama_guru() {
        return nama_guru;
    }

    public void setNama_guru(String nama_guru) {
        this.nama_guru = nama_guru;
    }

    public String getNama_pelajaran() {
        return nama_pelajaran;
    }

    public void setNama_pelajaran(String nama_pelajaran) {
        this.nama_pelajaran = nama_pelajaran;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }
}
