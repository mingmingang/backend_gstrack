package com.astratech.backend_gstrack.VO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "gs_track_imp")
public class IMP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imp_id")
    private int impId;

    @Column(name = "imp_no_request")
    private String impNoRequest;

    @Column(name = "imp_npk")
    private String impNpk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="imp_npk", insertable=false, updatable=false)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Karyawan karyawan;

    @Column(name = "imp_kegiatan")
    private String impKegiatan;

    @Column(name = "imp_tanggal_berangkat")
    private LocalDate impTanggalBerangkat;

    @Column(name = "imp_waktu_berangkat")
    private LocalTime impWaktuBerangkat;

    @Column(name = "imp_tanggal_kembali")
    private LocalDate impTanggalKembali;

    @Column(name = "imp_waktu_kembali")
    private LocalTime impWaktuKembali;

    @Column(name = "imp_lokasi")
    private String impLokasi;

    @Column(name = "imp_keterangan")
    private String impKeterangan;

    @Lob
    @Column(name = "imp_berkas_lampiran")
    private String impBerkasLampiran;

    @Column(name = "imp_status")
    private String impStatus;

    @Column(name = "imp_created_by")
    private String impCreatedBy;

    @Column(name = "imp_created_date")
    private LocalDateTime impCreatedDate;

    @Column(name = "imp_modif_by")
    private String impModifBy;

    @Column(name = "imp_modif_date")
    private LocalDateTime impModifDate;

    public IMP() {}

    public IMP(int impId, String impNoRequest, String impNpk, Karyawan karyawan, String impKegiatan, LocalDate impTanggalBerangkat, LocalTime impWaktuBerangkat, LocalDate impTanggalKembali, LocalTime impWaktuKembali, String impLokasi, String impKeterangan, String impBerkasLampiran, String impStatus, String impCreatedBy, LocalDateTime impCreatedDate, String impModifBy, LocalDateTime impModifDate) {
        this.impId = impId;
        this.impNoRequest = impNoRequest;
        this.impNpk = impNpk;
        this.karyawan = karyawan;
        this.impKegiatan = impKegiatan;
        this.impTanggalBerangkat = impTanggalBerangkat;
        this.impWaktuBerangkat = impWaktuBerangkat;
        this.impTanggalKembali = impTanggalKembali;
        this.impWaktuKembali = impWaktuKembali;
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

    public String getImpNpk() {
        return impNpk;
    }

    public void setImpNpk(String impNpk) {
        this.impNpk = impNpk;
    }

    public Karyawan getKaryawan() {
        return karyawan;
    }

    public void setKaryawan(Karyawan karyawan) {
        this.karyawan = karyawan;
    }

    @JsonProperty("namaKaryawan")
    public String getNamaKaryawan() {
        return karyawan!=null ? karyawan.getNamaKaryawan() : null;
    }

    public String getImpKegiatan() {
        return impKegiatan;
    }

    public void setImpKegiatan(String impKegiatan) {
        this.impKegiatan = impKegiatan;
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

    public LocalDateTime getImpCreatedDate() {
        return impCreatedDate;
    }

    public void setImpCreatedDate(LocalDateTime impCreatedDate) {
        this.impCreatedDate = impCreatedDate;
    }

    public String getImpModifBy() {
        return impModifBy;
    }

    public void setImpModifBy(String impModifBy) {
        this.impModifBy = impModifBy;
    }

    public LocalDateTime getImpModifDate() {
        return impModifDate;
    }

    public void setImpModifDate(LocalDateTime impModifDate) {
        this.impModifDate = impModifDate;
    }
}
