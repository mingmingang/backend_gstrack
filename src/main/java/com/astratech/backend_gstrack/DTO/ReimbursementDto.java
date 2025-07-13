package com.astratech.backend_gstrack.DTO;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

// DTO untuk mengirim data ke frontend
public class ReimbursementDto {

    // Kolom-kolom yang ada di gs_track_reimbursement
    private BigInteger rbmId;
    private String kryNpk;
    private BigInteger orgId;
    private String rbmTipe;
    private Date rbmTanggalMulai;
    private Date rbmTanggalSelesai;
    private BigDecimal rbmCost;
    private String dgsId;
    private String rbmDiagnosaOther;
    private Integer rsId;
    private String rbmDokter;
    private String rbmFilePathKwitansi;
    private String rbmFilePathRincianObat;
    private String rbmFilePathHasilLab;
    private String rbmFilePathResumeMedis;
    private String rbmStatusSubmit;
    private String rbmCreatedBy;
    private Date rbmCreatedDate;
    private String rbmModifyBy;
    private Date rbmModifyDate;
    private String rbmAlasanPembatalan;

    // Kolom tambahan hasil JOIN untuk kemudahan di frontend
    private String orgNama;
    private String orgHubungan;
    private String kryNama;
    private String rsTipe;
    private String rsNama;
    private String dgsNama;

    // Getter dan Setter
    public BigInteger getRbmId() {
        return rbmId;
    }

    public void setRbmId(BigInteger rbmId) {
        this.rbmId = rbmId;
    }

    public String getKryNpk() {
        return kryNpk;
    }

    public void setKryNpk(String kryNpk) {
        this.kryNpk = kryNpk;
    }

    public BigInteger getOrgId() {
        return orgId;
    }

    public void setOrgId(BigInteger orgId) {
        this.orgId = orgId;
    }

    public String getRbmTipe() {
        return rbmTipe;
    }

    public void setRbmTipe(String rbmTipe) {
        this.rbmTipe = rbmTipe;
    }

    public Date getRbmTanggalMulai() {
        return rbmTanggalMulai;
    }

    public void setRbmTanggalMulai(Date rbmTanggalMulai) {
        this.rbmTanggalMulai = rbmTanggalMulai;
    }

    public Date getRbmTanggalSelesai() {
        return rbmTanggalSelesai;
    }

    public void setRbmTanggalSelesai(Date rbmTanggalSelesai) {
        this.rbmTanggalSelesai = rbmTanggalSelesai;
    }

    public BigDecimal getRbmCost() {
        return rbmCost;
    }

    public void setRbmCost(BigDecimal rbmCost) {
        this.rbmCost = rbmCost;
    }

    public String getDgsId() {
        return dgsId;
    }

    public void setDgsId(String dgsId) {
        this.dgsId = dgsId;
    }

    public String getRbmDiagnosaOther() {
        return rbmDiagnosaOther;
    }

    public void setRbmDiagnosaOther(String rbmDiagnosaOther) {
        this.rbmDiagnosaOther = rbmDiagnosaOther;
    }

    public Integer getRsId() {
        return rsId;
    }

    public void setRsId(Integer rsId) {
        this.rsId = rsId;
    }

    public String getRbmDokter() {
        return rbmDokter;
    }

    public void setRbmDokter(String rbmDokter) {
        this.rbmDokter = rbmDokter;
    }

    public String getRbmFilePathKwitansi() {
        return rbmFilePathKwitansi;
    }

    public void setRbmFilePathKwitansi(String rbmFilePathKwitansi) {
        this.rbmFilePathKwitansi = rbmFilePathKwitansi;
    }

    public String getRbmFilePathRincianObat() {
        return rbmFilePathRincianObat;
    }

    public void setRbmFilePathRincianObat(String rbmFilePathRincianObat) {
        this.rbmFilePathRincianObat = rbmFilePathRincianObat;
    }

    public String getRbmFilePathHasilLab() {
        return rbmFilePathHasilLab;
    }

    public void setRbmFilePathHasilLab(String rbmFilePathHasilLab) {
        this.rbmFilePathHasilLab = rbmFilePathHasilLab;
    }

    public String getRbmFilePathResumeMedis() {
        return rbmFilePathResumeMedis;
    }

    public void setRbmFilePathResumeMedis(String rbmFilePathResumeMedis) {
        this.rbmFilePathResumeMedis = rbmFilePathResumeMedis;
    }

    public String getRbmStatusSubmit() {
        return rbmStatusSubmit;
    }

    public void setRbmStatusSubmit(String rbmStatusSubmit) {
        this.rbmStatusSubmit = rbmStatusSubmit;
    }

    public String getRbmCreatedBy() {
        return rbmCreatedBy;
    }

    public void setRbmCreatedBy(String rbmCreatedBy) {
        this.rbmCreatedBy = rbmCreatedBy;
    }

    public Date getRbmCreatedDate() {
        return rbmCreatedDate;
    }

    public void setRbmCreatedDate(Date rbmCreatedDate) {
        this.rbmCreatedDate = rbmCreatedDate;
    }

    public String getRbmModifyBy() {
        return rbmModifyBy;
    }

    public void setRbmModifyBy(String rbmModifyBy) {
        this.rbmModifyBy = rbmModifyBy;
    }

    public Date getRbmModifyDate() {
        return rbmModifyDate;
    }

    public void setRbmModifyDate(Date rbmModifyDate) {
        this.rbmModifyDate = rbmModifyDate;
    }

    public String getOrgNama() {
        return orgNama;
    }

    public void setOrgNama(String orgNama) {
        this.orgNama = orgNama;
    }

    public String getOrgHubungan() {
        return orgHubungan;
    }

    public void setOrgHubungan(String orgHubungan) {
        this.orgHubungan = orgHubungan;
    }

    public String getKryNama() {
        return kryNama;
    }

    public void setKryNama(String kryNama) {
        this.kryNama = kryNama;
    }

    public String getRsTipe() {
        return rsTipe;
    }

    public void setRsTipe(String rsTipe) {
        this.rsTipe = rsTipe;
    }

    public String getRsNama() {
        return rsNama;
    }

    public void setRsNama(String rsNama) {
        this.rsNama = rsNama;
    }

    public String getDgsNama() {
        return dgsNama;
    }

    public void setDgsNama(String dgsNama) {
        this.dgsNama = dgsNama;
    }

    public String getRbmAlasanPembatalan() {
        return rbmAlasanPembatalan;
    }

    public void setRbmAlasanPembatalan(String rbmAlasanPembatalan) {
        this.rbmAlasanPembatalan = rbmAlasanPembatalan;
    }
}