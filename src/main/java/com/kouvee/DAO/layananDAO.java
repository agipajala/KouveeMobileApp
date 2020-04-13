package com.kouvee.DAO;

import com.google.gson.annotations.SerializedName;

public class layananDAO {

    @SerializedName("idLayanan")
    private String idLayanan;

    @SerializedName("namaLayanan")
    private String namaLayanan;

    @SerializedName("harga")
    private Double harga;

    @SerializedName("idUkuran")
    private int idUkuran;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("updated_at")
    private String updated_at;

    @SerializedName("deleted_at")
    private String deleted_at;

    @SerializedName("idPegawaiLog")
    private String  idPegawaiLog;

    public layananDAO(String idLayanan, String namaLayanan, Double harga, int idUkuran, String idPegawaiLog)
    {
        this.idLayanan = idLayanan;
        this.namaLayanan = namaLayanan;
        this.harga = harga;
        this.idUkuran = idUkuran;
        this.idPegawaiLog = idPegawaiLog;
    }

    public String getIdLayanan() {
        return idLayanan;
    }

    public String getNamaLayanan() {
        return namaLayanan;
    }

    public Double getHarga() {
        return harga;
    }

    public int getIdUkuran() {
        return idUkuran;
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
