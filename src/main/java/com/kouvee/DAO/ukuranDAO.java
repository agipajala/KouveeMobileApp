package com.kouvee.DAO;

import com.google.gson.annotations.SerializedName;

public class ukuranDAO {
    @SerializedName("idUkuran")
    private int  idUkuran;

    @SerializedName("namaUkuran")
    private String  namaUkuran;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("updated_at")
    private String updated_at;

    @SerializedName("deleted_at")
    private String deleted_at;

    @SerializedName("idPegawaiLog")
    private String  idPegawaiLog;

    public ukuranDAO(int idUkuran, String namaUkuran, String createdAt, String updatedAt, String deletedAt, String idPegawaiLog)
    {
        this.idUkuran = idUkuran;
        this.namaUkuran = namaUkuran;
        this.created_at = createdAt;
        this.updated_at = updatedAt;
        this.deleted_at = deletedAt;
        this.idPegawaiLog = idPegawaiLog;
    }

    public int getIdUkuran() {
        return idUkuran;
    }

    public String getNamaUkuran() {
        return namaUkuran;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public String getIdPegawaiLog() {
        return idPegawaiLog;
    }
}
