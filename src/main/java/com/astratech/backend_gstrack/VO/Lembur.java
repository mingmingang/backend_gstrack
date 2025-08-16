package com.astratech.backend_gstrack.VO;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "gs_track_lembur")
public class Lembur {

    @Id
    @Column(name = "lbr_id", updatable = false)
    private String lbrId;

    @Column(name = "kry_npk")
    private String krynpk;

    @Column(name = "lbr_tanggal")
    private LocalDate lbrTanggal;

    @Column(name = "lbr_kategori_hari")
    private String lbrKategoriHari;

    @Column(name = "lbr_jam_mulai")
    private Time lbrJamMulai;

    @Column(name = "lbr_jam_selesai")
    private Time lbrJamSelesai;

    @Column(name = "lbr_shift")
    private int lbrShift;

    @Column(name = "lbr_durasi_jam")
    private double lbrDurasiJam;

    @Column(name = "lbr_status")
    private String lbrStatus;

    @Column(name = "lbr_created_by")
    private String lbrCreatedBy;

    @Column(name = "lbr_created_date")
    private LocalDateTime lbrCreatedDate;

    @Column(name = "lbr_modif_by")
    private String lbrModifBy;

    @Column(name = "lbr_modif_date")
    private LocalDateTime lbrModifDate;

    // --- Constructor kosong ---
    public Lembur() {
    }

    // --- Constructor lengkap (optional) ---
    public Lembur(String lbrId, String kryNpk, LocalDate lbrTanggal, String lbrKategoriHari,
                  LocalTime lbrJamMulai, LocalTime lbrJamSelesai, int lbrShift, double lbrDurasiJam,
                  String lbrStatus, String lbrCreatedBy, LocalDateTime lbrCreatedDate,
                  String lbrModifBy, LocalDateTime lbrModifDate) {
        this.lbrId = lbrId;
        this.krynpk = kryNpk;
        this.lbrTanggal = lbrTanggal;
        this.lbrKategoriHari = lbrKategoriHari;
        this.lbrJamMulai = Time.valueOf(lbrJamMulai);
        this.lbrJamSelesai = Time.valueOf(lbrJamSelesai);
        this.lbrShift = lbrShift;
        this.lbrDurasiJam = lbrDurasiJam;
        this.lbrStatus = lbrStatus;
        this.lbrCreatedBy = lbrCreatedBy;
        this.lbrCreatedDate = lbrCreatedDate;
        this.lbrModifBy = lbrModifBy;
        this.lbrModifDate = lbrModifDate;
    }

    // --- Getter & Setter ---

    public String getLbrId() {
        return lbrId;
    }

    public void setLbrId(String lbrId) {
        this.lbrId = lbrId;
    }

    public String getKrynpk() {
        return krynpk;
    }

    public void setKrynpk(String krynpk) {
        this.krynpk = krynpk;
    }

    public LocalDate getLbrTanggal() {
        return lbrTanggal;
    }

    public void setLbrTanggal(LocalDate lbrTanggal) {
        this.lbrTanggal = lbrTanggal;
    }

    public String getLbrKategoriHari() {
        return lbrKategoriHari;
    }

    public void setLbrKategoriHari(String lbrKategoriHari) {
        this.lbrKategoriHari = lbrKategoriHari;
    }

    public Time getLbrJamMulai() {
        return lbrJamMulai;
    }

    public void setLbrJamMulai(Time lbrJamMulai) {
        this.lbrJamMulai = lbrJamMulai;
    }

    public Time getLbrJamSelesai() {
        return lbrJamSelesai;
    }

    public void setLbrJamSelesai(Time lbrJamSelesai) {
        this.lbrJamSelesai = lbrJamSelesai;
    }

    public int getLbrShift() {
        return lbrShift;
    }

    public void setLbrShift(int lbrShift) {
        this.lbrShift = lbrShift;
    }

    public double getLbrDurasiJam() {
        return lbrDurasiJam;
    }

    public void setLbrDurasiJam(double lbrDurasiJam) {
        this.lbrDurasiJam = lbrDurasiJam;
    }

    public String getLbrStatus() {
        return lbrStatus;
    }

    public void setLbrStatus(String lbrStatus) {
        this.lbrStatus = lbrStatus;
    }

    public String getLbrCreatedBy() {
        return lbrCreatedBy;
    }

    public void setLbrCreatedBy(String lbrCreatedBy) {
        this.lbrCreatedBy = lbrCreatedBy;
    }

    public LocalDateTime getLbrCreatedDate() {
        return lbrCreatedDate;
    }

    public void setLbrCreatedDate(LocalDateTime lbrCreatedDate) {
        this.lbrCreatedDate = lbrCreatedDate;
    }

    public String getLbrModifBy() {
        return lbrModifBy;
    }

    public void setLbrModifBy(String lbrModifBy) {
        this.lbrModifBy = lbrModifBy;
    }

    public LocalDateTime getLbrModifDate() {
        return lbrModifDate;
    }

    public void setLbrModifDate(LocalDateTime lbrModifDate) {
        this.lbrModifDate = lbrModifDate;
    }
}
