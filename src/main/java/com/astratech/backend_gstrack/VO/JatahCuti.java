package com.astratech.backend_gstrack.VO;

import jakarta.persistence.*;

@Entity
@Table(name = "gs_track_cuti_jatah")
public class JatahCuti {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Integer id;

    @Column(name = "kry_npk", nullable = false)
    private String kryNpk;

    @Column(name = "tahun", nullable = false)
    private int tahun;

    @Column(name = "hak_cuti", nullable = false)
    private int hakCuti;

    @Column(name = "cuti_dipakai", nullable = false)
    private int cutiDipakai;

    @Column(name = "cuti_sisa", nullable = false)
    private int cutiSisa;

    @Column(name = "tipe_cuti")
    private String tipeCuti;

    @Column(name = "masa_berlaku")
    private String masaBerlaku;

    public JatahCuti() {
    }

    public JatahCuti(Integer id, String kryNpk, int tahun, int hakCuti, int cutiDipakai, int cutiSisa, String tipeCuti, String masaBerlaku) {
        this.id = id;
        this.kryNpk = kryNpk;
        this.tahun = tahun;
        this.hakCuti = hakCuti;
        this.cutiDipakai = cutiDipakai;
        this.cutiSisa = cutiSisa;
        this.tipeCuti = tipeCuti;
        this.masaBerlaku = masaBerlaku;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKryNpk() {
        return kryNpk;
    }

    public void setKryNpk(String kryNpk) {
        this.kryNpk = kryNpk;
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

    public String getTipeCuti() {
        return tipeCuti;
    }

    public void setTipeCuti(String tipeCuti) {
        this.tipeCuti = tipeCuti;
    }

    public String getMasaBerlaku() {
        return masaBerlaku;
    }

    public void setMasaBerlaku(String masaBerlaku) {
        this.masaBerlaku = masaBerlaku;
    }
}
