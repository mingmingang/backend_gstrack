package com.astratech.backend_gstrack.VO;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "cuti")
public class Cuti {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuti_id")
    private String cutiId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "mulai_dari")
    private Date tanggalAwal;

    @Column(name = "sampai_dengan")
    private Date tanggalAkhir;

    @Column(name = "tipe_cuti")
    private String jenisCuti;

    @Column(name = "alasan")
    private String alasan;

    @Column(name = "status")
    private String status;

    @Column(name = "tanggal_pengajuan")
    private Date tanggalPengajuan;

    public Cuti() {
    }

    public Cuti(String cutiId, String userId, Date tanggalAwal, Date tanggalAkhir, String jenisCuti, String alasan, String status, Date tanggalPengajuan) {
        this.cutiId = cutiId;
        this.userId = userId;
        this.tanggalAwal = tanggalAwal;
        this.tanggalAkhir = tanggalAkhir;
        this.jenisCuti = jenisCuti;
        this.alasan = alasan;
        this.status = status;
        this.tanggalPengajuan = tanggalPengajuan;
    }

    public String getCutiId() {
        return cutiId;
    }

    public void setCutiId(String cutiId) {
        this.cutiId = cutiId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getTanggalAwal() {
        return tanggalAwal;
    }

    public void setTanggalAwal(Date tanggalAwal) {
        this.tanggalAwal = tanggalAwal;
    }

    public Date getTanggalAkhir() {
        return tanggalAkhir;
    }

    public void setTanggalAkhir(Date tanggalAkhir) {
        this.tanggalAkhir = tanggalAkhir;
    }

    public String getJenisCuti() {
        return jenisCuti;
    }

    public void setJenisCuti(String jenisCuti) {
        this.jenisCuti = jenisCuti;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTanggalPengajuan() {
        return tanggalPengajuan;
    }

    public void setTanggalPengajuan(Date tanggalPengajuan) {
        this.tanggalPengajuan = tanggalPengajuan;
    }
}
