package com.astratech.backend_gstrack.VO.DataBantuan;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "gs_track_diagnosa")
public class Diagnosa {
    @Id
    @Column(name = "dgs_id")
    private String dgsId;

    @Column(name = "dgs_nama")
    private String dgsNama;

    public Diagnosa() {}

    public Diagnosa(String dgsId, String dgsNama) {
        this.dgsId = dgsId;
        this.dgsNama = dgsNama;
    }

    public Diagnosa(String dgsId) {
        this.dgsId = dgsId.toString();
    }

    public String getDgsId() {
        return dgsId;
    }

    public void setDgsId(String dgsId) {
        this.dgsId = dgsId;
    }

    public String getDgsNama() {
        return dgsNama;
    }

    public void setDgsNama(String dgsNama) {
        this.dgsNama = dgsNama;
    }
}
