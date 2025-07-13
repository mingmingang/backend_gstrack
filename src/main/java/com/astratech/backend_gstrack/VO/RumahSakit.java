// RumahSakit - VO
package com.astratech.backend_gstrack.VO;

import jakarta.persistence.*;

@Entity
@Table(name = "gs_track_rumah_sakit")
public class RumahSakit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rs_id")
    private Integer rsId;

    @Column(name = "rs_tipe")
    private String rsTipe;

    @Column(name = "rs_nama")
    private String rsNama;

    public RumahSakit() {
    }

    public RumahSakit(Integer rsId, String rsTipe, String rsNama) {
        this.rsId = rsId;
        this.rsTipe = rsTipe;
        this.rsNama = rsNama;
    }

    public Integer getRsId() {
        return rsId;
    }

    public void setRsId(Integer rsId) {
        this.rsId = rsId;
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
}