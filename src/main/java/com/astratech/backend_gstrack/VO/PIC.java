package com.astratech.backend_gstrack.VO;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "gs_track_pic")
public class PIC {
    public enum JenisAh {
        Mutasi,
        Rusak,
        Hilang
    }
    @Id
    @Column(name = "pic_id")
    private String id;
    @Column(name = "kry_npk")
    private String kryNpk;
    @Enumerated(EnumType.STRING)
    @Column(name = "pic_ah")
    private JenisAh ah;
    @Column(name = "pic_status")
    private String status;
    @Column(name = "pic_crea_date")
    private LocalDateTime createdDate;
    @Column(name = "pic_crea_by")
    private String createdBy;
    @Column(name = "pic_modi_date")
    private LocalDateTime modifiedDate;
    @Column(name = "pic_modi_by")
    private String modifiedBy;
    public PIC() { }
    @Transient
    private String kryNamaKaryawan;
    public String getKryNamaKaryawan() {
        return kryNamaKaryawan;
    }

    public void setKryNamaKaryawan(String kryNamaKaryawan) {
        this.kryNamaKaryawan = kryNamaKaryawan;
    }

    public PIC(String id, String kryNpk, JenisAh ah, String status, LocalDateTime createdDate, String createdBy, LocalDateTime modifiedDate, String modifiedBy) {
        this.id = id;
        this.kryNpk = kryNpk;
        this.ah = ah;
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

    public JenisAh getAh() {
        return ah;
    }

    public void setAh(JenisAh ah) {
        this.ah = ah;
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

