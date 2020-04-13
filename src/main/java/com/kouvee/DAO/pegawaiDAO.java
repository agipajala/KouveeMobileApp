package com.kouvee.DAO;

import android.media.Image;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.util.Date;

public class pegawaiDAO {
    @SerializedName("NIP")
    private String  NIP;

    @SerializedName("namaPegawai")
    private String  namaPegawai;

    @SerializedName("alamat")
    private String  alamat;

    @SerializedName("tglLahir")
    private String tglLahir;

    @SerializedName("noHp")
    private String  noHp;

    @SerializedName("jabatan")
    private String  jabatan;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("updated_at")
    private String updated_at;

    @SerializedName("deleted_at")
    private String deleted_at;

    @SerializedName("idPegawaiLog")
    private String  idPegawaiLog;

    public String getNIP() {
        return NIP;
    }

    public String getNamaPegawai() {
        return namaPegawai;
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

    public String getJabatan() {
        return jabatan;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public String getUpdatedAt() {
        return updated_at;
    }

    public String getDeletedAt() {
        return deleted_at;
    }

    public String getIdPegawaiLog() {
        return idPegawaiLog;
    }
}
