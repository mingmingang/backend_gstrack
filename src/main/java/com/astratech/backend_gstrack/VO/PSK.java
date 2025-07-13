package com.astratech.backend_gstrack.VO;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity untuk tabel gs_track_psk.
 */
@Entity
@Table(name = "gs_track_psk")
public class PSK {
    @Id
    @Column(name = "psk_id")
    private String id;
    @Column(name = "kry_npk")
    private String kryNpk;
    @Column(name = "psk_ap")
    private String ap;
    @Column(name = "psk_ket")
    private String keterangan;
    @Column(name = "psk_status")
    private String status;
    @Column(name = "psk_crea_date")
    private LocalDateTime createdDate;
    @Column(name = "psk_crea_by")
    private String createdBy;
    @Column(name = "psk_modi_date")
    private LocalDateTime modifiedDate;
    @Column(name = "psk_modi_by")
    private String modifiedBy;
    public PSK() {
    }
    @Transient
    private String kryNamaKaryawan;
    public String getKryNamaKaryawan() {
        return kryNamaKaryawan;
    }

    public void setKryNamaKaryawan(String kryNamaKaryawan) {
        this.kryNamaKaryawan = kryNamaKaryawan;
    }

    public PSK(String id, String kryNpk, String ap, String keterangan, String status, LocalDateTime createdDate, String createdBy, LocalDateTime modifiedDate, String modifiedBy) {
        this.id = id;
        this.kryNpk = kryNpk;
        this.ap = ap;
        this.keterangan = keterangan;
        this.status = status;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKryNpk() {
        return kryNpk;
    }

    public void setKryNpk(String kryNpk) {
        this.kryNpk = kryNpk;
    }

    public String getAp() {
        return ap;
    }

    public void setAp(String ap) {
        this.ap = ap;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}