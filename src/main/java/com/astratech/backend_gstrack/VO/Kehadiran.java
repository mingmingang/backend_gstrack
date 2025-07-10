package com.astratech.backend_gstrack.VO;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "gs_track_absensi")
public class Kehadiran {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "absensi_id")
    private Integer idAbsen;
    @Column(name = "kry_npk")
    private String idKaryawan;
    @Column(name = "tanggal")
    private Date tanggalMasuk;
    @Column(name = "jam_masuk")
    private LocalDateTime masukAbsen;
    @Column(name = "jam_keluar")
    private LocalDateTime keluarAbsen;
    @Column(name = "status_kehairan")
    private Integer indikatorKehadiran;
    public Kehadiran()
    {}

    public Integer getIdAbsen() {
        return idAbsen;
    }

    public void setIdAbsen(Integer idAbsen) {
        this.idAbsen = idAbsen;
    }

    public String getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(String idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public Date getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(Date tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public LocalDateTime getMasukAbsen() {
        return masukAbsen;
    }

    public void setMasukAbsen(LocalDateTime masukAbsen) {
        this.masukAbsen = masukAbsen;
    }

    public LocalDateTime getKeluarAbsen() {
        return keluarAbsen;
    }

    public void setKeluarAbsen(LocalDateTime keluarAbsen) {
        this.keluarAbsen = keluarAbsen;
    }

    public Integer getIndikatorKehadiran() {
        return indikatorKehadiran;
    }

    public void setIndikatorKehadiran(Integer indikatorKehadiran) {
        this.indikatorKehadiran = indikatorKehadiran;
    }

    public Kehadiran(Integer idAbsen, String idKaryawan, Date tanggalMasuk, LocalDateTime masukAbsen, LocalDateTime keluarAbsen, Integer indikatorKehadiran) {
        this.idAbsen = idAbsen;
        this.idKaryawan = idKaryawan;
        this.tanggalMasuk = tanggalMasuk;
        this.masukAbsen = masukAbsen;
        this.keluarAbsen = keluarAbsen;
        this.indikatorKehadiran = indikatorKehadiran;
    }
}
