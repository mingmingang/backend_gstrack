package com.astratech.backend_gstrack.VO;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "gs_track_cuti_detail")
public class CutiDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuti_detail_id")
    private Long cutiDetailId;

    @Column(name = "cuti_id")
    private String cutiId;

    @Column(name = "tanggal_cuti")
    private Date tanggalCuti;

    @Column(name = "status")
    private String status;

    @Column(name = "alasan")
    private String alasan;

    public CutiDetail() {
    }

    public CutiDetail(Long cutiDetailId, String cutiId, Date tanggalCuti, String status, String alasan) {
        this.cutiDetailId = cutiDetailId;
        this.cutiId = cutiId;
        this.tanggalCuti = tanggalCuti;
        this.status = status;
        this.alasan = alasan;
    }

    public Long getCutiDetailId() {
        return cutiDetailId;
    }

    public void setCutiDetailId(Long cutiDetailId) {
        this.cutiDetailId = cutiDetailId;
    }

    public String getCutiId() {
        return cutiId;
    }

    public void setCutiId(String cutiId) {
        this.cutiId = cutiId;
    }

    public Date getTanggalCuti() {
        return tanggalCuti;
    }

    public void setTanggalCuti(Date tanggalCuti) {
        this.tanggalCuti = tanggalCuti;
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
}
