package com.astratech.backend_gstrack.VO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "nama")
    private String nama;

    @Column(name = "jabatan")
    private String jabatan;

    @Column(name = "lokasi_kerja")
    private String lokasiKerja;

    @Column(name = "atasan_id")
    private String atasanId;

    @Column(name = "tipe_akun")
    private String tipeAkun;

    @Column(name = "email")
    private String email;


    @Column(name = "password")
    private String password;


    public User() {
    }

    public User(String userId, String nama, String jabatan, String lokasiKerja, String atasanId, String tipeAkun, String email, String password) {
        this.userId = userId;
        this.nama = nama;
        this.jabatan = jabatan;
        this.lokasiKerja = lokasiKerja;
        this.atasanId = atasanId;
        this.tipeAkun = tipeAkun;
        this.email = email;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getLokasiKerja() {
        return lokasiKerja;
    }

    public void setLokasiKerja(String lokasiKerja) {
        this.lokasiKerja = lokasiKerja;
    }

    public String getAtasanId() {
        return atasanId;
    }

    public void setAtasanId(String atasanId) {
        this.atasanId = atasanId;
    }

    public String getTipeAkun() {
        return tipeAkun;
    }

    public void setTipeAkun(String tipeAkun) {
        this.tipeAkun = tipeAkun;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
