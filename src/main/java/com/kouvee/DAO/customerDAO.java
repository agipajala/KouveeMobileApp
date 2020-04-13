package com.kouvee.DAO;

import com.google.gson.annotations.SerializedName;

public class customerDAO {

    @SerializedName("idCustomer")
    private int idCustomer;

    @SerializedName("namaCustomer")
    private String namaCustomer;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("tglLahir")
    private String tglLahir;

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

    public customerDAO(int idCustomer, String namaCustomer, String alamat, String tglLahir, String noHp, String idPegawaiLog)
    {
        this.idCustomer = idCustomer;
        this.namaCustomer = namaCustomer;
        this.alamat = alamat;
        this.tglLahir = tglLahir;
        this.noHp = noHp;
        this.idPegawaiLog = idPegawaiLog;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public String getNamaCustomer() {
        return namaCustomer;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getTglLahir() {
        return tglLahir;
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
