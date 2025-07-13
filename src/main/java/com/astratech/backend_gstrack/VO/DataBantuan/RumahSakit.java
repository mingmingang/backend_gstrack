package com.astratech.backend_gstrack.VO.DataBantuan;

import jakarta.persistence.*;

@Entity
@Table(name = "gs_track_rumah_sakit")
public class RumahSakit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rs_id")
    private Integer rsId;

    @Column(name = "rs_nama")
    private String rsNama;

    @Column(name = "rs_tipe")
    private String rsTipe;

    public RumahSakit() {}

    public RumahSakit(Integer rsId, String rsNama, String rsTipe) {
        this.rsId = rsId;
        this.rsNama = rsNama;
        this.rsTipe = rsTipe;
    }

    public RumahSakit(Integer rsId) {
        this.rsId = rsId;
    }

    public Integer getRsId() {
        return rsId;
    }

    public void setRsId(Integer rsId) {
        this.rsId = rsId;
    }

    public String getRsNama() {
        return rsNama;
    }

    public void setRsNama(String rsNama) {
        this.rsNama = rsNama;
    }

    public String getRsTipe() {
        return rsTipe;
    }

    public void setRsTipe(String rsTipe) {
        this.rsTipe = rsTipe;
    }
}
