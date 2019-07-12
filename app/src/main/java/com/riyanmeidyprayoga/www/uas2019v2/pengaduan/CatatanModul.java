package com.riyanmeidyprayoga.www.uas2019v2.pengaduan;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CatatanModul extends RealmObject {
    @PrimaryKey
    private int id;
    private String nama;
    private String nik;
    private String isi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik= nik;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }
}
