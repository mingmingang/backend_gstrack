package com.astratech.backend_gstrack.DTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class IMPResponseDto {
    private String impNoRequest;
    private String impNpk;
    private String karyawanNama;
    private LocalDate impTanggalBerangkat;
    private LocalTime impWaktuBerangkat;
    private LocalDate impTanggalKembali;
    private LocalTime impWaktuKembali;
    private String impLokasi;
    private String impKeterangan;
    private String impStatus;
    private LocalDateTime impCreatedDate;

    public String getImpNoRequest() {
        return impNoRequest;
    }

    public void setImpNoRequest(String impNoRequest) {
        this.impNoRequest = impNoRequest;
    }

    public String getImpNpk() {
        return impNpk;
    }

    public void setImpNpk(String impNpk) {
        this.impNpk = impNpk;
    }

    public String getKaryawanNama() {
        return karyawanNama;
    }

    public void setKaryawanNama(String karyawanNama) {
        this.karyawanNama = karyawanNama;
    }

    public LocalDate getImpTanggalBerangkat() {
        return impTanggalBerangkat;
    }

    public void setImpTanggalBerangkat(LocalDate impTanggalBerangkat) {
        this.impTanggalBerangkat = impTanggalBerangkat;
    }

    public LocalTime getImpWaktuBerangkat() {
        return impWaktuBerangkat;
    }

    public void setImpWaktuBerangkat(LocalTime impWaktuBerangkat) {
        this.impWaktuBerangkat = impWaktuBerangkat;
    }

    public LocalDate getImpTanggalKembali() {
        return impTanggalKembali;
    }

    public void setImpTanggalKembali(LocalDate impTanggalKembali) {
        this.impTanggalKembali = impTanggalKembali;
    }

    public LocalTime getImpWaktuKembali() {
        return impWaktuKembali;
    }

    public void setImpWaktuKembali(LocalTime impWaktuKembali) {
        this.impWaktuKembali = impWaktuKembali;
    }

    public String getImpLokasi() {
        return impLokasi;
    }

    public void setImpLokasi(String impLokasi) {
        this.impLokasi = impLokasi;
    }

    public String getImpKeterangan() {
        return impKeterangan;
    }

    public void setImpKeterangan(String impKeterangan) {
        this.impKeterangan = impKeterangan;
    }

    public String getImpStatus() {
        return impStatus;
    }

    public void setImpStatus(String impStatus) {
        this.impStatus = impStatus;
    }

    public LocalDateTime getImpCreatedDate() {
        return impCreatedDate;
    }

    public void setImpCreatedDate(LocalDateTime impCreatedDate) {
        this.impCreatedDate = impCreatedDate;
    }
}
