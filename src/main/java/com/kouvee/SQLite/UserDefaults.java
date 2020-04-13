package com.kouvee.SQLite;

public class UserDefaults {
    private  int id;
    private String NIP, namaPegawai, jabatan, status;

    public UserDefaults(){}

    public UserDefaults(int id, String nip, String namaP, String jabatan, String status)
    {
        this.id = id;
        this.NIP = nip;
        this.namaPegawai = namaP;
        this.jabatan = jabatan;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getNIP() {
        return NIP;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public String getJabatan() {
        return jabatan;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
