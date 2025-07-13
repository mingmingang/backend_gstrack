// Orang - VO
package com.astratech.backend_gstrack.VO;

import jakarta.persistence.*;

@Entity
@Table(name = "gs_track_orang")
public class Orang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "org_id")
    private Integer orgId;

    @Column(name = "kry_npk")
    private String kryNpk;

    @Column(name = "org_nama")
    private String orgNama;

    @Column(name = "org_hubungan")
    private String orgHubungan;

    public Orang() {
    }

    public Orang(Integer orgId, String kryNpk, String orgNama, String orgHubungan) {
        this.orgId = orgId;
        this.kryNpk = kryNpk;
        this.orgNama = orgNama;
        this.orgHubungan = orgHubungan;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getKryNpk() {
        return kryNpk;
    }

    public void setKryNpk(String kryNpk) {
        this.kryNpk = kryNpk;
    }

    public String getOrgNama() {
        return orgNama;
    }

    public void setOrgNama(String orgNama) {
        this.orgNama = orgNama;
    }

    public String getOrgHubungan() {
        return orgHubungan;
    }

    public void setOrgHubungan(String orgHubungan) {
        this.orgHubungan = orgHubungan;
    }

}