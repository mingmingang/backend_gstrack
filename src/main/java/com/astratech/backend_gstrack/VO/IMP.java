package com.astratech.backend_gstrack.VO;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "gs_track_imp")
public class IMP {
    @Id
    @Column(name = "imp_id")
    private int impId;

    @Column(name = "imp_no_request")
    private String impNoRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imp_npk", referencedColumnName = "kry_npk")
    private Karyawan karyawan;

    @Column(name = "imp_kegiatan")
    private String impKegiatan;

    @Column(name = "imp_tanggal_berangkat")
    private Date impTanggalBerangkat;

    @Column(name = "imp_waktu_berangkat")
    private LocalTime impWaktuBerangkat;

    @Column(name = "imp_tanggal_pulang")
    private Date impTanggalPulang;

    @Column(name = "imp_waktu_pulang")
    private LocalTime impWaktuPulang;

    @Column(name = "imp_lokasi")
    private String impLokasi;

    @Column(name = "imp_keterangan")
    private String impKeterangan;

    @Column(name = "imp_berkas_lampiran")
    private String impBerkasLampiran;

    @Column(name = "imp_status")
    private String impStatus;

    @Column(name = "imp_created_by")
    private String impCreatedBy;

    @Column(name = "imp_created_date")
    private Date impCreatedDate;

    @Column(name = "imp_modif_by")
    private String impModifBy;

    @Column(name = "imp_modif_date")
    private Date impModifDate;

    public IMP() {}

    public IMP(int impId, String impNoRequest, Karyawan karyawan, String impKegiatan, Date impTanggalBerangkat, LocalTime impWaktuBerangkat, Date impTanggalPulang, LocalTime impWaktuPulang, String impLokasi, String impKeterangan, String impBerkasLampiran, String impStatus, String impCreatedBy, Date impCreatedDate, String impModifBy, Date impModifDate) {
        this.impId = impId;
        this.impNoRequest = impNoRequest;
        this.karyawan = karyawan;
        this.impKegiatan = impKegiatan;
        this.impTanggalBerangkat = impTanggalBerangkat;
        this.impWaktuBerangkat = impWaktuBerangkat;
        this.impTanggalPulang = impTanggalPulang;
        this.impWaktuPulang = impWaktuPulang;
        this.impLokasi = impLokasi;
        this.impKeterangan = impKeterangan;
        this.impBerkasLampiran = impBerkasLampiran;
        this.impStatus = impStatus;
        this.impCreatedBy = impCreatedBy;
        this.impCreatedDate = impCreatedDate;
        this.impModifBy = impModifBy;
        this.impModifDate = impModifDate;
    }

    public int getImpId() {
        return impId;
    }

    public void setImpId(int impId) {
        this.impId = impId;
    }

    public String getImpNoRequest() {
        return impNoRequest;
    }

    public void setImpNoRequest(String impNoRequest) {
        this.impNoRequest = impNoRequest;
    }

    public Karyawan getKaryawan() {
        return karyawan;
    }

    public void setKaryawan(Karyawan karyawan) {
        this.karyawan = karyawan;
    }

    public String getImpKegiatan() {
        return impKegiatan;
    }

    public void setImpKegiatan(String impKegiatan) {
        this.impKegiatan = impKegiatan;
    }

    public Date getImpTanggalBerangkat() {
        return impTanggalBerangkat;
    }

    public void setImpTanggalBerangkat(Date impTanggalBerangkat) {
        this.impTanggalBerangkat = impTanggalBerangkat;
    }

    public LocalTime getImpWaktuBerangkat() {
        return impWaktuBerangkat;
    }

    public void setImpWaktuBerangkat(LocalTime impWaktuBerangkat) {
        this.impWaktuBerangkat = impWaktuBerangkat;
    }

    public Date getImpTanggalPulang() {
        return impTanggalPulang;
    }

    public void setImpTanggalPulang(Date impTanggalPulang) {
        this.impTanggalPulang = impTanggalPulang;
    }

    public LocalTime getImpWaktuPulang() {
        return impWaktuPulang;
    }

    public void setImpWaktuPulang(LocalTime impWaktuPulang) {
        this.impWaktuPulang = impWaktuPulang;
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

    public String getImpBerkasLampiran() {
        return impBerkasLampiran;
    }

    public void setImpBerkasLampiran(String impBerkasLampiran) {
        this.impBerkasLampiran = impBerkasLampiran;
    }

    public String getImpStatus() {
        return impStatus;
    }

    public void setImpStatus(String impStatus) {
        this.impStatus = impStatus;
    }

    public String getImpCreatedBy() {
        return impCreatedBy;
    }

    public void setImpCreatedBy(String impCreatedBy) {
        this.impCreatedBy = impCreatedBy;
    }

    public Date getImpCreatedDate() {
        return impCreatedDate;
    }

    public void setImpCreatedDate(Date impCreatedDate) {
        this.impCreatedDate = impCreatedDate;
    }

    public String getImpModifBy() {
        return impModifBy;
    }

    public void setImpModifBy(String impModifBy) {
        this.impModifBy = impModifBy;
    }

    public Date getImpModifDate() {
        return impModifDate;
    }

    public void setImpModifDate(Date impModifDate) {
        this.impModifDate = impModifDate;
    }
}
