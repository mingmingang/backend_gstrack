package com.astratech.backend_gstrack.VO.DataBantuan;

import com.astratech.backend_gstrack.VO.Karyawan;
import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "gs_track_orang")
public class Orang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "org_id")
    private BigInteger orgId;

    @Column(name = "org_nama")
    private String orgNama;

    @Column(name = "org_hubungan")
    private String orgHubungan;

    @Column(name = "kry_npk")
    private String kryNpk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kry_npk", referencedColumnName = "kry_npk", insertable = false, updatable = false)
    private Karyawan karyawan;

    public Orang() {}

    public Orang(BigInteger orgId, String orgNama, String orgHubungan, String kryNpk, Karyawan karyawan) {
        this.orgId = orgId;
        this.orgNama = orgNama;
        this.orgHubungan = orgHubungan;
        this.kryNpk = kryNpk;
        this.karyawan = karyawan;
    }

    public BigInteger getOrgId() {
        return orgId;
    }

    public void setOrgId(BigInteger orgId) {
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

    public String getKryNpk() {
        return kryNpk;
    }

    public void setKryNpk(String kryNpk) {
        this.kryNpk = kryNpk;
    }

    public Karyawan getKaryawan() {
        return karyawan;
    }

    public void setKaryawan(Karyawan karyawan) {
        this.karyawan = karyawan;
    }
}
