package com.kouvee.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kouvee.DAO.customerDAO;
import com.kouvee.DAO.hewanDAO;
import com.kouvee.DAO.jenisDAO;
import com.kouvee.DAO.layananDAO;
import com.kouvee.DAO.pegawaiDAO;
import com.kouvee.DAO.produkDAO;
import com.kouvee.DAO.supplierDAO;
import com.kouvee.DAO.ukuranDAO;

import java.util.List;

public class Response {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("customer")
    @Expose
    private List<customerDAO> customer;

    @SerializedName("hewan")
    @Expose
    private List<hewanDAO> hewan;

    @SerializedName("jenis")
    @Expose
    private List<jenisDAO> jenis;

    @SerializedName("ukuran")
    @Expose
    private List<ukuranDAO> ukuran;

    @SerializedName("layanan")
    @Expose
    private List<layananDAO> layanan;

    @SerializedName("produk")
    @Expose
    private List<produkDAO> produk;

    @SerializedName("pegawai")
    @Expose
    private List<pegawaiDAO> pegawai;

    @SerializedName("supplier")
    @Expose
    private List<supplierDAO> supplier;

    //Get Data
    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<customerDAO> getCustomer() {
        return customer;
    }

    public List<hewanDAO> getHewan() {
        return hewan;
    }

    public List<jenisDAO> getJenis() {
        return jenis;
    }

    public List<layananDAO> getLayanan() {
        return layanan;
    }

    public List<pegawaiDAO> getPegawai() {
        return pegawai;
    }

    public List<produkDAO> getProduk() {
        return produk;
    }

    public List<supplierDAO> getSupplier() {
        return supplier;
    }

    public List<ukuranDAO> getUkuran() {
        return ukuran;
    }
}
