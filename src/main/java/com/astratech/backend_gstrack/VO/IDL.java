package com.astratech.backend_gstrack.VO;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Entity
@Table(name = "gs_track_idl")
public class IDL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idl_id")
    private Integer idlId;

    @Column(name = "idl_no_request", length = 20)
    private String idlNoRequest;

    @Column(name = "idl_npk", length = 10)
    private String idlNpk;

    @Column(name = "idl_jenis_kegiatan", length = 50)
    private String idlJenisKegiatan;

    @Column(name = "idl_tanggal_berangkat")
    private LocalDate idlTanggalBerangkat;

    @Column(name = "idl_waktu_berangkat")
    private LocalTime idlWaktuBerangkat;

    @Column(name = "idl_tanggal_kembali")
    private LocalDate idlTanggalKembali;

    @Column(name = "idl_waktu_kembali")
    private LocalTime idlWaktuKembali;

    @Column(name = "idl_lokasi_pertama", columnDefinition = "varchar(max)")
    private String idlLokasiPertama;

    @Column(name = "idl_lokasi_kedua", columnDefinition = "varchar(max)")
    private String idlLokasiKedua;

    @Column(name = "idl_lokasi_ketiga", columnDefinition = "varchar(max)")
    private String idlLokasiKetiga;

    @Column(name = "idl_keterangan", columnDefinition = "varchar(max)")
    private String idlKeterangan;

    @Column(name = "idl_berkas_lampiran", columnDefinition = "varchar(max)")
    private String idlBerkasLampiran;

    @Column(name = "idl_status", length = 20)
    private String idlStatus;

    @Column(name = "idl_berangkat")
    private LocalDateTime idlBerangkat;

    @Column(name = "idl_pulang")
    private LocalDateTime idlPulang;

    @Column(name = "idl_created_by", columnDefinition = "varchar(max)")
    private String idlCreatedBy;

    @Column(name = "idl_created_date")
    private LocalDateTime idlCreatedDate;

    @Column(name = "idl_modif_by", columnDefinition = "varchar(max)")
    private String idlModifBy;

    @Column(name = "idl_modif_date")
    private LocalDateTime idlModifDate;

    public IDL() {
    }

    public IDL(Integer idlId, String idlNoRequest, String idlNpk, String idlJenisKegiatan, LocalDate idlTanggalBerangkat, LocalTime idlWaktuBerangkat, LocalDate idlTanggalKembali, LocalTime idlWaktuKembali, String idlLokasiPertama, String idlLokasiKedua, String idlLokasiKetiga, String idlKeterangan, String idlBerkasLampiran, String idlStatus, LocalDateTime idlBerangkat, LocalDateTime idlPulang, String idlCreatedBy, LocalDateTime idlCreatedDate, String idlModifBy, LocalDateTime idlModifDate) {
        this.idlId = idlId;
        this.idlNoRequest = idlNoRequest;
        this.idlNpk = idlNpk;
        this.idlJenisKegiatan = idlJenisKegiatan;
        this.idlTanggalBerangkat = idlTanggalBerangkat;
        this.idlWaktuBerangkat = idlWaktuBerangkat;
        this.idlTanggalKembali = idlTanggalKembali;
        this.idlWaktuKembali = idlWaktuKembali;
        this.idlLokasiPertama = idlLokasiPertama;
        this.idlLokasiKedua = idlLokasiKedua;
        this.idlLokasiKetiga = idlLokasiKetiga;
        this.idlKeterangan = idlKeterangan;
        this.idlBerkasLampiran = idlBerkasLampiran;
        this.idlStatus = idlStatus;
        this.idlBerangkat = idlBerangkat;
        this.idlPulang = idlPulang;
        this.idlCreatedBy = idlCreatedBy;
        this.idlCreatedDate = idlCreatedDate;
        this.idlModifBy = idlModifBy;
        this.idlModifDate = idlModifDate;
    }

    public Integer getIdlId() {
        return idlId;
    }

    public void setIdlId(Integer idlId) {
        this.idlId = idlId;
    }

    public String getIdlNoRequest() {
        return idlNoRequest;
    }

    public void setIdlNoRequest(String idlNoRequest) {
        this.idlNoRequest = idlNoRequest;
    }

    public String getIdlNpk() {
        return idlNpk;
    }

    public void setIdlNpk(String idlNpk) {
        this.idlNpk = idlNpk;
    }

    public String getIdlJenisKegiatan() {
        return idlJenisKegiatan;
    }

    public void setIdlJenisKegiatan(String idlJenisKegiatan) {
        this.idlJenisKegiatan = idlJenisKegiatan;
    }

    public LocalDate getIdlTanggalBerangkat() {
        return idlTanggalBerangkat;
    }

    public void setIdlTanggalBerangkat(LocalDate idlTanggalBerangkat) {
        this.idlTanggalBerangkat = idlTanggalBerangkat;
    }

    public LocalTime getIdlWaktuBerangkat() {
        return idlWaktuBerangkat;
    }

    public void setIdlWaktuBerangkat(LocalTime idlWaktuBerangkat) {
        this.idlWaktuBerangkat = idlWaktuBerangkat;
    }

    public LocalDate getIdlTanggalKembali() {
        return idlTanggalKembali;
    }

    public void setIdlTanggalKembali(LocalDate idlTanggalKembali) {
        this.idlTanggalKembali = idlTanggalKembali;
    }

    public LocalTime getIdlWaktuKembali() {
        return idlWaktuKembali;
    }

    public void setIdlWaktuKembali(LocalTime idlWaktuKembali) {
        this.idlWaktuKembali = idlWaktuKembali;
    }

    public String getIdlLokasiPertama() {
        return idlLokasiPertama;
    }

    public void setIdlLokasiPertama(String idlLokasiPertama) {
        this.idlLokasiPertama = idlLokasiPertama;
    }

    public String getIdlLokasiKedua() {
        return idlLokasiKedua;
    }

    public void setIdlLokasiKedua(String idlLokasiKedua) {
        this.idlLokasiKedua = idlLokasiKedua;
    }

    public String getIdlLokasiKetiga() {
        return idlLokasiKetiga;
    }

    public void setIdlLokasiKetiga(String idlLokasiKetiga) {
        this.idlLokasiKetiga = idlLokasiKetiga;
    }

    public String getIdlKeterangan() {
        return idlKeterangan;
    }

    public void setIdlKeterangan(String idlKeterangan) {
        this.idlKeterangan = idlKeterangan;
    }

    public String getIdlBerkasLampiran() {
        return idlBerkasLampiran;
    }

    public void setIdlBerkasLampiran(String idlBerkasLampiran) {
        this.idlBerkasLampiran = idlBerkasLampiran;
    }

    public String getIdlStatus() {
        return idlStatus;
    }

    public void setIdlStatus(String idlStatus) {
        this.idlStatus = idlStatus;
    }

    public LocalDateTime getIdlBerangkat() {
        return idlBerangkat;
    }

    public void setIdlBerangkat(LocalDateTime idlBerangkat) {
        this.idlBerangkat = idlBerangkat;
    }

    public LocalDateTime getIdlPulang() {
        return idlPulang;
    }

    public void setIdlPulang(LocalDateTime idlPulang) {
        this.idlPulang = idlPulang;
    }

    public String getIdlCreatedBy() {
        return idlCreatedBy;
    }

    public void setIdlCreatedBy(String idlCreatedBy) {
        this.idlCreatedBy = idlCreatedBy;
    }

    public LocalDateTime getIdlCreatedDate() {
        return idlCreatedDate;
    }

    public void setIdlCreatedDate(LocalDateTime idlCreatedDate) {
        this.idlCreatedDate = idlCreatedDate;
    }

    public String getIdlModifBy() {
        return idlModifBy;
    }

    public void setIdlModifBy(String idlModifBy) {
        this.idlModifBy = idlModifBy;
    }

    public LocalDateTime getIdlModifDate() {
        return idlModifDate;
    }

    public void setIdlModifDate(LocalDateTime idlModifDate) {
        this.idlModifDate = idlModifDate;
    }
}
