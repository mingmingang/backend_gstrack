package com.astratech.backend_gstrack.VO;

import jakarta.persistence.*;

@Entity
@Table(name = "cuti_jatah")
public class JatahCuti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "tahun")
    private int tahun;

    @Column(name = "hak_cuti")
    private int hakCuti;

    @Column(name = "cuti_dipakai")
    private int cutiDipakai;

    @Column(name = "cuti_sisa")
    private int cutiSisa;

    public JatahCuti() {
    }

    public JatahCuti(String id, String userId, int tahun, int hakCuti, int cutiDipakai, int cutiSisa) {
        this.id = id;
        this.userId = userId;
        this.tahun = tahun;
        this.hakCuti = hakCuti;
        this.cutiDipakai = cutiDipakai;
        this.cutiSisa = cutiSisa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public int getHakCuti() {
        return hakCuti;
    }

    public void setHakCuti(int hakCuti) {
        this.hakCuti = hakCuti;
    }

    public int getCutiDipakai() {
        return cutiDipakai;
    }

    public void setCutiDipakai(int cutiDipakai) {
        this.cutiDipakai = cutiDipakai;
    }

    public int getCutiSisa() {
        return cutiSisa;
    }

    public void setCutiSisa(int cutiSisa) {
        this.cutiSisa = cutiSisa;
    }
}
