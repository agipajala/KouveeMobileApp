package com.kouvee.DAO;

import com.google.gson.annotations.SerializedName;

public class jenisDAO {

    @SerializedName("idJenis")
    private int  idJenis;

    @SerializedName("namaJenis")
    private String  namaJenis;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("updated_at")
    private String updated_at;

    @SerializedName("deleted_at")
    private String deleted_at;

    @SerializedName("idPegawaiLog")
    private String  idPegawaiLog;

    public jenisDAO(int idJenis, String namaJenis, String createdAt, String updatedAt, String deletedAt, String idPegawaiLog)
    {
        this.idJenis = idJenis;
        this.namaJenis = namaJenis;
        this.created_at = createdAt;
        this.updated_at = updatedAt;
        this.deleted_at = deletedAt;
        this.idPegawaiLog = idPegawaiLog;
    }

    public int getIdJenis() {
        return idJenis;
    }

    public String getNamaJenis() {
        return namaJenis;
    }

    public String getCreatedAt() {
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
