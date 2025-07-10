package com.astratech.backend_gstrack.VO;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "gs_track_cuti")
public class Cuti {

    @Id
    @Column(name = "cuti_id")
    private String cutiId;

    @Column(name = "kry_npk")
    private String npk;

    @Column(name = "tipe_cuti")
    private String tipeCuti;

    @Column(name = "sub_tipe_cuti")
    private String subTipeCuti;

    @Column(name = "mulai_dari")
    private Date mulaiDari;

    @Column(name = "sampai_dengan")
    private Date sampaiDengan;

    @Column(name = "durasi")
    private Integer durasi;

    @Column(name = "status")
    private String status;

    @Column(name = "alasan")
    private String alasan;

    @Column(name = "lampiran")
    private String lampiran;

    @Column(name = "tanggal_pengajuan")
    private Date tanggalPengajuan;

    @Column(name = "masa_berlaku_cuti")
    private Date masaBerlakuCuti;

    @Column(name = "jenis_cuti")
    private String jenisCuti;

    @Column(name = "tanggal_akhir")
    private Date tanggalAkhir;

    @Column(name = "tanggal_awal")
    private Date tanggalAwal;

    public Cuti() {
    }

    public Cuti(String cutiId, String npk, String tipeCuti, String subTipeCuti, Date mulaiDari, Date sampaiDengan, Integer durasi, String status, String alasan, String lampiran, Date tanggalPengajuan, Date masaBerlakuCuti, String jenisCuti, Date tanggalAkhir, Date tanggalAwal) {
        this.cutiId = cutiId;
        this.npk = npk;
        this.tipeCuti = tipeCuti;
        this.subTipeCuti = subTipeCuti;
        this.mulaiDari = mulaiDari;
        this.sampaiDengan = sampaiDengan;
        this.durasi = durasi;
        this.status = status;
        this.alasan = alasan;
        this.lampiran = lampiran;
        this.tanggalPengajuan = tanggalPengajuan;
        this.masaBerlakuCuti = masaBerlakuCuti;
        this.jenisCuti = jenisCuti;
        this.tanggalAkhir = tanggalAkhir;
        this.tanggalAwal = tanggalAwal;
    }

    public String getCutiId() {
        return cutiId;
    }

    public void setCutiId(String cutiId) {
        this.cutiId = cutiId;
    }

    public String getNpk() {
        return npk;
    }

    public void setNpk(String npk) {
        this.npk = npk;
    }

    public String getTipeCuti() {
        return tipeCuti;
    }

    public void setTipeCuti(String tipeCuti) {
        this.tipeCuti = tipeCuti;
    }

    public String getSubTipeCuti() {
        return subTipeCuti;
    }

    public void setSubTipeCuti(String subTipeCuti) {
        this.subTipeCuti = subTipeCuti;
    }

    public Date getMulaiDari() {
        return mulaiDari;
    }

    public void setMulaiDari(Date mulaiDari) {
        this.mulaiDari = mulaiDari;
    }

    public Date getSampaiDengan() {
        return sampaiDengan;
    }

    public void setSampaiDengan(Date sampaiDengan) {
        this.sampaiDengan = sampaiDengan;
    }

    public Integer getDurasi() {
        return durasi;
    }

    public void setDurasi(Integer durasi) {
        this.durasi = durasi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }

    public String getLampiran() {
        return lampiran;
    }

    public void setLampiran(String lampiran) {
        this.lampiran = lampiran;
    }

    public Date getTanggalPengajuan() {
        return tanggalPengajuan;
    }

    public void setTanggalPengajuan(Date tanggalPengajuan) {
        this.tanggalPengajuan = tanggalPengajuan;
    }

    public Date getMasaBerlakuCuti() {
        return masaBerlakuCuti;
    }

    public void setMasaBerlakuCuti(Date masaBerlakuCuti) {
        this.masaBerlakuCuti = masaBerlakuCuti;
    }

    public String getJenisCuti() {
        return jenisCuti;
    }

    public void setJenisCuti(String jenisCuti) {
        this.jenisCuti = jenisCuti;
    }

    public Date getTanggalAkhir() {
        return tanggalAkhir;
    }

    public void setTanggalAkhir(Date tanggalAkhir) {
        this.tanggalAkhir = tanggalAkhir;
    }

    public Date getTanggalAwal() {
        return tanggalAwal;
    }

    public void setTanggalAwal(Date tanggalAwal) {
        this.tanggalAwal = tanggalAwal;
    }
}
