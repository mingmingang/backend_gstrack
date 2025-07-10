package com.astratech.backend_gstrack.VO.DataBantuan;

import com.astratech.backend_gstrack.VO.Karyawan;
import jakarta.persistence.*;
import org.aspectj.weaver.ast.Or;

@Entity
@Table(name = "gs_track_keluarga")
public class Keluarga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "klg_id")
    private Integer klgId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kry_npk", referencedColumnName = "kry_npk", insertable = false, updatable = false)
    private Karyawan karyawan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id", referencedColumnName = "org_id", insertable = false, updatable = false)
    private Orang orang;

    public Keluarga() {}

    public Keluarga(Integer klgId, Karyawan karyawan, Orang orang) {
        this.klgId = klgId;
        this.karyawan = karyawan;
        this.orang = orang;
    }

    public Keluarga(Integer klgId) {
        this.klgId = klgId;
    }

    public Integer getKlgId() {
        return klgId;
    }

    public void setKlgId(Integer klgId) {
        this.klgId = klgId;
    }

    public Karyawan getKaryawan() {
        return karyawan;
    }

    public void setKaryawan(Karyawan karyawan) {
        this.karyawan = karyawan;
    }

    public Orang getOrang() {
        return orang;
    }

    public void setOrang(Orang orang) {
        this.orang = orang;
    }
}
