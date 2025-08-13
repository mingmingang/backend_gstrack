package com.astratech.backend_gstrack.VO;

import com.astratech.backend_gstrack.VO.DataBantuan.Orang;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "gs_track_psj")
public class Jaminan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "psj_id")
    private Integer psjId;

    @Column(name = "psj_no_request_record")
    private String psjNoRequestRecord;

    @Column(name = "kry_npk")
    private String kryNpk;

    @Column(name = "psj_plant")
    private String psjPlant;

    @Column(name = "psj_departemen")
    private String psjDepartemen;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "psj_tanggal_periksa")
    private LocalDate psjTanggalPeriksa;

    @Column(name = "rs_id")
    private Integer rsId;

    @Column(name = "psj_tipe_jaminan")
    private String psjTipeJaminan;

    @Column(name = "org_id")
    private BigInteger orgId;

    @Column(name = "psj_keterangan")
    private String psjKeterangan;

    @Column(name = "psj_status")
    private String psjStatus;

    @Column(name = "psj_created_by")
    private String psjCreatedBy;

    @CreationTimestamp
    @Column(name = "psj_created_date")
    private LocalDateTime psjCreatedDate;

    @Column(name = "psj_modif_by")
    private String psjModifBy;

    @Column(name = "psj_modif_date")
    private LocalDateTime psjModifDate;

    @Column(name = "psj_alasan_penolakan")
    private String psjAlasanPenolakan;

    @Transient
    private String orgNama;

    @Transient
    private String orgHubungan;

    @Transient
    private String namaKaryawan;

    @Transient
    private Integer jumlahPlafon;

    @Transient
    private Integer penggunaanPlafon;


    @Transient
    private String rsNama;


    public Jaminan() {
    }

    public Integer getPsjId() {
        return psjId;
    }

    public void setPsjId(Integer psjId) {
        this.psjId = psjId;
    }

    public String getPsjNoRequestRecord() {
        return psjNoRequestRecord;
    }

    public void setPsjNoRequestRecord(String psjNoRequestRecord) {
        this.psjNoRequestRecord = psjNoRequestRecord;
    }

    public String getKryNpk() {
        return kryNpk;
    }

    public void setKryNpk(String kryNpk) {
        this.kryNpk = kryNpk;
    }

    public String getPsjPlant() {
        return psjPlant;
    }

    public void setPsjPlant(String psjPlant) {
        this.psjPlant = psjPlant;
    }

    public String getPsjDepartemen() {
        return psjDepartemen;
    }

    public void setPsjDepartemen(String psjDepartemen) {
        this.psjDepartemen = psjDepartemen;
    }

    public LocalDate getPsjTanggalPeriksa() {
        return psjTanggalPeriksa;
    }

    public void setPsjTanggalPeriksa(LocalDate psjTanggalPeriksa) {
        this.psjTanggalPeriksa = psjTanggalPeriksa;
    }

    public Integer getRsId() {
        return rsId;
    }

    public void setRsId(Integer rsId) {
        this.rsId = rsId;
    }

    public String getPsjTipeJaminan() {
        return psjTipeJaminan;
    }

    public void setPsjTipeJaminan(String psjTipeJaminan) {
        this.psjTipeJaminan = psjTipeJaminan;
    }

    public BigInteger getOrgId() {
        return orgId;
    }

    public void setOrgId(BigInteger orgId) {
        this.orgId = orgId;
    }

    public String getPsjKeterangan() {
        return psjKeterangan;
    }

    public void setPsjKeterangan(String psjKeterangan) {
        this.psjKeterangan = psjKeterangan;
    }

    public String getPsjStatus() {
        return psjStatus;
    }

    public void setPsjStatus(String psjStatus) {
        this.psjStatus = psjStatus;
    }

    public String getPsjCreatedBy() {
        return psjCreatedBy;
    }

    public void setPsjCreatedBy(String psjCreatedBy) {
        this.psjCreatedBy = psjCreatedBy;
    }

    public LocalDateTime getPsjCreatedDate() {
        return psjCreatedDate;
    }

    public void setPsjCreatedDate(LocalDateTime psjCreatedDate) {
        this.psjCreatedDate = psjCreatedDate;
    }

    public String getPsjModifBy() {
        return psjModifBy;
    }

    public void setPsjModifBy(String psjModifBy) {
        this.psjModifBy = psjModifBy;
    }

    public LocalDateTime getPsjModifDate() {
        return psjModifDate;
    }

    public void setPsjModifDate(LocalDateTime psjModifDate) {
        this.psjModifDate = psjModifDate;
    }

    public String getPsjAlasanPenolakan() {
        return psjAlasanPenolakan;
    }

    public void setPsjAlasanPenolakan(String psjAlasanPenolakan) {
        this.psjAlasanPenolakan = psjAlasanPenolakan;
    }
}