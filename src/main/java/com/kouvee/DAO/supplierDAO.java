package com.kouvee.DAO;

import com.google.gson.annotations.SerializedName;

public class supplierDAO {

    @SerializedName("idSupplier")
    private int idSupplier;

    @SerializedName("namaSupplier")
    private String namaSupplier;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("noHp")
    private String noHp;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("updated_at")
    private String updated_at;

    @SerializedName("deleted_at")
    private String deleted_at;

    @SerializedName("idPegawaiLog")
    private String  idPegawaiLog;

    public supplierDAO(int idSupplier, String namaSupplier, String alamat, String noHp, String idPegawaiLog)
    {
        this.idSupplier =idSupplier;
        this.namaSupplier = namaSupplier;
        this.alamat = alamat;
        this.noHp = noHp;
        this.idPegawaiLog = idPegawaiLog;
    }

    public int getIdSupplier() {
        return idSupplier;
    }

    public String getNamaSupplier() {
        return namaSupplier;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNoHp() {
        return noHp;
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
