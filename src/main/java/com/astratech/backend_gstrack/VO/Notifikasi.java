package com.astratech.backend_gstrack.VO;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "gs_track_notifikasi")
public class Notifikasi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notif")
    private Integer idNotif;
    @Column(name = "kry_npk")
    private String idKaryawan;
    @Column(name = "judul")
    private String judulNotifikasi;
    @Column(name = "pesan")
    private String pesanNotifikasi;
    @Column(name = "tipe_notif")
    private Integer tipeNotif;
    @Column(name = "status_dibaca")
    private Integer statusDibaca;
    @Column(name = "tanggal_notif")
    private LocalDateTime tanggalNotif;

    public Notifikasi()
    {}

    public Notifikasi(Integer idNotif, String idKaryawan, String judulNotifikasi, String pesanNotifikasi, Integer tipeNotif, Integer statusDibaca, LocalDateTime tanggalNotif) {
        this.idNotif = idNotif;
        this.idKaryawan = idKaryawan;
        this.judulNotifikasi = judulNotifikasi;
        this.pesanNotifikasi = pesanNotifikasi;
        this.tipeNotif = tipeNotif;
        this.statusDibaca = statusDibaca;
        this.tanggalNotif = tanggalNotif;
    }

    public Integer getIdNotif() {
        return idNotif;
    }

    public void setIdNotif(Integer idNotif) {
        this.idNotif = idNotif;
    }

    public String getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(String idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public String getJudulNotifikasi() {
        return judulNotifikasi;
    }

    public void setJudulNotifikasi(String judulNotifikasi) {
        this.judulNotifikasi = judulNotifikasi;
    }

    public String getPesanNotifikasi() {
        return pesanNotifikasi;
    }

    public void setPesanNotifikasi(String pesanNotifikasi) {
        this.pesanNotifikasi = pesanNotifikasi;
    }

    public Integer getTipeNotif() {
        return tipeNotif;
    }

    public void setTipeNotif(Integer tipeNotif) {
        this.tipeNotif = tipeNotif;
    }

    public Integer getStatusDibaca() {
        return statusDibaca;
    }

    public void setStatusDibaca(Integer statusDibaca) {
        this.statusDibaca = statusDibaca;
    }

    public LocalDateTime getTanggalNotif() {
        return tanggalNotif;
    }

    public void setTanggalNotif(LocalDateTime tanggalNotif) {
        this.tanggalNotif = tanggalNotif;
    }
}
