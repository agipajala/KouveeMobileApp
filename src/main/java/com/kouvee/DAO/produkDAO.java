package com.kouvee.DAO;

import com.google.gson.annotations.SerializedName;

public class produkDAO {

    @SerializedName("idProduk")
    private String  idProduk;

    @SerializedName("namaProduk")
    private String  namaProduk;

    @SerializedName("harga")
    private Double  harga;

    @SerializedName("stok")
    private int  stok;

    @SerializedName("jumlahMinimal")
    private int  jumlahMinimal;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("updated_at")
    private String updated_at;

    @SerializedName("deleted_at")
    private String deleted_at;

    @SerializedName("idPegawaiLog")
    private String  idPegawaiLog;

    public produkDAO(String idProduk, String namaProduk, Double harga, int stok, int jumlahMinimal, String idPegawaiLog)
    {
        this.idProduk = idProduk;
        this.namaProduk = namaProduk;
        this.harga = harga;
        this.stok = stok;
        this.jumlahMinimal = jumlahMinimal;
        this.idPegawaiLog = idPegawaiLog;
    }

    public String getIdProduk() {
        return idProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public Double getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }

    public int getJumlahMinimal() {
        return jumlahMinimal;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public String getIdPegawaiLog() {
        return idPegawaiLog;
    }
}
