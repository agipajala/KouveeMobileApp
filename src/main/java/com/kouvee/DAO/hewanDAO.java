package com.kouvee.DAO;

import com.google.gson.annotations.SerializedName;

public class hewanDAO {

    @SerializedName("idHewan")
    private int idHewan;

    @SerializedName("namaHewan")
    private String namaHewan;

    @SerializedName("tglLahir")
    private String tglLahir;

    @SerializedName("idJenis")
    private int idJenis;

    @SerializedName("idCustomer")
    private int idCustomer;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("updated_at")
    private String updated_at;

    @SerializedName("deleted_at")
    private String deleted_at;

    @SerializedName("idPegawaiLog")
    private String  idPegawaiLog;

    public hewanDAO(int idHewan, String namaHewan, String tglLahir, int idJenis, int idCustomer, String idPegawaiLog)
    {
        this.idHewan = idHewan;
        this.namaHewan = namaHewan;
        this.tglLahir = tglLahir;
        this.idJenis = idJenis;
        this.idCustomer = idCustomer;
        this.idPegawaiLog = idPegawaiLog;
    }

    public int getIdHewan() {
        return idHewan;
    }

    public String getNamaHewan() {
        return namaHewan;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public int getIdJenis() {
        return idJenis;
    }

    public int getIdCustomer() {
        return idCustomer;
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
