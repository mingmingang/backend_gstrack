package com.astratech.backend_gstrack.VO.DataBantuan;

import jakarta.persistence.*;

@Entity
@Table(name = "gs_track_orang")
public class Orang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "org_id")
    private String orgId;

    @Column(name = "org_nama")
    private String orgNama;

    @Column(name = "org_hubungan")
    private String orgHubungan;

    public Orang() {}

    public Orang(String orgId, String orgNama, String orgHubungan) {
        this.orgId = orgId;
        this.orgNama = orgNama;
        this.orgHubungan = orgHubungan;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
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
