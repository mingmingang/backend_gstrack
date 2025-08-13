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
    @Column(name = "status_kehadiran")
    private Integer indikatorKehadiran;
    @Column(name = "longitude_masuk")
    private Double longitudeMasuk;

    @Column(name = "latitude_masuk")
    private Double latitudeMasuk;

    @Column(name = "longitude_keluar")
    private Double longitudeKeluar;

    @Column(name = "latitude_keluar")
    private Double latitudeKeluar;

    @Column(name = "foto_masuk")
    private String fotoMasuk;

    @Column(name = "foto_keluar")
    private String fotoKeluar;

    // 👇 Join ke Karyawan
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kry_npk", referencedColumnName = "kry_npk", insertable = false, updatable = false)
    private Karyawan karyawan;
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

    public Double getLongitudeMasuk() {
        return longitudeMasuk;
    }

    public void setLongitudeMasuk(Double longitudeMasuk) {
        this.longitudeMasuk = longitudeMasuk;
    }

    public Double getLatitudeMasuk() {
        return latitudeMasuk;
    }

    public void setLatitudeMasuk(Double latitudeMasuk) {
        this.latitudeMasuk = latitudeMasuk;
    }

    public Double getLongitudeKeluar() {
        return longitudeKeluar;
    }

    public void setLongitudeKeluar(Double longitudeKeluar) {
        this.longitudeKeluar = longitudeKeluar;
    }

    public Double getLatitudeKeluar() {
        return latitudeKeluar;
    }

    public void setLatitudeKeluar(Double latitudeKeluar) {
        this.latitudeKeluar = latitudeKeluar;
    }

    public String getFotoMasuk() {
        return fotoMasuk;
    }

    public void setFotoMasuk(String fotoMasuk) {
        this.fotoMasuk = fotoMasuk;
    }

    public String getFotoKeluar() {
        return fotoKeluar;
    }

    public void setFotoKeluar(String fotoKeluar) {
        this.fotoKeluar = fotoKeluar;
    }

    public Karyawan getKaryawan() {
        return karyawan;
    }

    public void setKaryawan(Karyawan karyawan) {
        this.karyawan = karyawan;
    }

    public Kehadiran(Integer idAbsen, String idKaryawan, Date tanggalMasuk, LocalDateTime masukAbsen, LocalDateTime keluarAbsen, Integer indikatorKehadiran, Double longitudeMasuk, Double latitudeMasuk, Double longitudeKeluar, Double latitudeKeluar, String fotoMasuk, String fotoKeluar) {
        this.idAbsen = idAbsen;
        this.idKaryawan = idKaryawan;
        this.tanggalMasuk = tanggalMasuk;
        this.masukAbsen = masukAbsen;
        this.keluarAbsen = keluarAbsen;
        this.indikatorKehadiran = indikatorKehadiran;
        this.longitudeMasuk = longitudeMasuk;
        this.latitudeMasuk = latitudeMasuk;
        this.longitudeKeluar = longitudeKeluar;
        this.latitudeKeluar = latitudeKeluar;
        this.fotoMasuk = fotoMasuk;
        this.fotoKeluar = fotoKeluar;
    }
}
