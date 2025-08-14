package com.astratech.backend_gstrack.VO;

import com.astratech.backend_gstrack.VO.DataBantuan.Diagnosa;
import com.astratech.backend_gstrack.VO.DataBantuan.Orang;
import com.astratech.backend_gstrack.VO.DataBantuan.RumahSakit;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "gs_track_reimbursement")
public class Reimbursement {
    @Id
    @Column(name = "rbm_id")
    private BigInteger rbmId;

    @Column(name = "kry_npk")
    private String kryNpk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kry_npk", referencedColumnName = "kry_npk", insertable = false, updatable = false)
    private Karyawan karyawan;

    @Column(name = "org_id")
    private BigInteger orgId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id", referencedColumnName = "org_id", insertable = false, updatable = false)
    private Orang orang;

    @Column(name = "rbm_tipe")
    private String rbmTipe;

    @Column(name = "rbm_tanggal_mulai")
    private Date rbmTanggalMulai;

    @Column(name = "rbm_tanggal_selesai")
    private Date rbmTanggalSelesai;

    @Column(name = "rbm_cost")
    private BigDecimal rbmCost;

    @Column(name = "dgs_id")
    private String dgsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dgs_id", referencedColumnName = "dgs_id", insertable = false, updatable = false)
    private Diagnosa diagnosa;

    @Column(name = "rbm_diagnosa_other")
    private String rbmDiagnosaOther;

    @Column(name = "rs_id")
    private Integer rsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rs_id", referencedColumnName = "rs_id", insertable = false, updatable = false)
    private RumahSakit rumahSakit;

    @Column(name = "rbm_dokter")
    private String rbmDokter;

    @Lob
    @Column(name = "rbm_file_path_kwitansi")
    private String rbmFilePathKwitansi;

    @Lob
    @Column(name = "rbm_file_path_rincian_obat")
    private String rbmFilePathRincianObat;

    @Lob
    @Column(name = "rbm_file_path_hasil_lab")
    private String rbmFilePathHasilLab;

    @Lob
    @Column(name = "rbm_file_path_resume_medis")
    private String rbmFilePathResumeMedis;

    @Column(name = "rbm_status_submit")
    private String rbmStatusSubmit;

    @Column(name = "rbm_created_by")
    private String rbmCreatedBy;

    @Column(name = "rbm_created_date")
    private Date rbmCreatedDate;

    @Column(name = "rbm_modify_by")
    private String rbmModifyBy;

    @Column(name = "rbm_modify_date")
    private Date rbmModifyDate;

    @Column(name = "rbm_alasan_pembatalan")
    private String rbmAlasanPembatalan;

    public Reimbursement() {
    }

    public Reimbursement(BigInteger rbmId, String kryNpk, Karyawan karyawan, BigInteger orgId, Orang orang, String rbmTipe, Date rbmTanggalMulai, Date rbmTanggalSelesai, BigDecimal rbmCost, String dgsId, Diagnosa diagnosa, String rbmDiagnosaOther, Integer rsId, RumahSakit rumahSakit, String rbmDokter, String rbmFilePathKwitansi, String rbmFilePathRincianObat, String rbmFilePathHasilLab, String rbmFilePathResumeMedis, String rbmStatusSubmit, String rbmCreatedBy, Date rbmCreatedDate, String rbmModifyBy, Date rbmModifyDate, String rbmAlasanPembatalan) {
        this.rbmId = rbmId;
        this.kryNpk = kryNpk;
        this.karyawan = karyawan;
        this.orgId = orgId;
        this.orang = orang;
        this.rbmTipe = rbmTipe;
        this.rbmTanggalMulai = rbmTanggalMulai;
        this.rbmTanggalSelesai = rbmTanggalSelesai;
        this.rbmCost = rbmCost;
        this.dgsId = dgsId;
        this.diagnosa = diagnosa;
        this.rbmDiagnosaOther = rbmDiagnosaOther;
        this.rsId = rsId;
        this.rumahSakit = rumahSakit;
        this.rbmDokter = rbmDokter;
        this.rbmFilePathKwitansi = rbmFilePathKwitansi;
        this.rbmFilePathRincianObat = rbmFilePathRincianObat;
        this.rbmFilePathHasilLab = rbmFilePathHasilLab;
        this.rbmFilePathResumeMedis = rbmFilePathResumeMedis;
        this.rbmStatusSubmit = rbmStatusSubmit;
        this.rbmCreatedBy = rbmCreatedBy;
        this.rbmCreatedDate = rbmCreatedDate;
        this.rbmModifyBy = rbmModifyBy;
        this.rbmModifyDate = rbmModifyDate;
        this.rbmAlasanPembatalan = rbmAlasanPembatalan;
    }

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

    public Karyawan getKaryawan() {
        return karyawan;
    }

    public void setKaryawan(Karyawan karyawan) {
        this.karyawan = karyawan;
    }

    public BigInteger getOrgId() {
        return orgId;
    }

    public void setOrgId(BigInteger orgId) {
        this.orgId = orgId;
    }

    public Orang getOrang() {
        return orang;
    }

    public void setOrang(Orang orang) {
        this.orang = orang;
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

    public Diagnosa getDiagnosa() {
        return diagnosa;
    }

    public void setDiagnosa(Diagnosa diagnosa) {
        this.diagnosa = diagnosa;
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

    public RumahSakit getRumahSakit() {
        return rumahSakit;
    }

    public void setRumahSakit(RumahSakit rumahSakit) {
        this.rumahSakit = rumahSakit;
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

    public String getRbmAlasanPembatalan() {
        return rbmAlasanPembatalan;
    }

    public void setRbmAlasanPembatalan(String rbmAlasanPembatalan) {
        this.rbmAlasanPembatalan = rbmAlasanPembatalan;
    }
}