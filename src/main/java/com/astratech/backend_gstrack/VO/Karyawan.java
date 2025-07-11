package com.astratech.backend_gstrack.VO;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "tlkp_karyawan")
public class Karyawan {

    @Id
    @Column(name = "kry_npk", length = 10)
    private String npk;

    @Column(name = "kry_nama_karyawan", length = 50)
    private String namaKaryawan;

    @Column(name = "kry_foto_karyawan", columnDefinition = "VARCHAR(MAX)")
    private String fotoKaryawan;

    @Column(name = "kry_plant", length = 10)
    private String plant;

    @Column(name = "kry_departemen", length = 50)
    private String departemen;

    @Column(name = "kry_email", length = 50)
    private String email;

    @Column(name = "kry_no_handphone", length = 15)
    private String noHandphone;

    @Column(name = "kry_tanggal_lahir")
    @Temporal(TemporalType.DATE)
    private Date tanggalLahir;

    @Column(name = "kry_status", length = 20)
    private String status;

    @Column(name = "kry_created_by", columnDefinition = "VARCHAR(MAX)")
    private String createdBy;

    @Column(name = "kry_created_date")
    private LocalDateTime createdDate;

    @Column(name = "kry_modif_by", columnDefinition = "VARCHAR(MAX)")
    private String modifBy;

    @Column(name = "kry_modif_date")
    private LocalDateTime modifDate;

    @Column(name = "kry_password")
    private String password;

    @Column(name = "kry_jabatan")
    private String jabatan;

    public Karyawan() {}

    public Karyawan(String npk, String namaKaryawan, String fotoKaryawan, String plant, String departemen, String email, String noHandphone, Date tanggalLahir, String status, String createdBy, LocalDateTime createdDate, String modifBy, LocalDateTime modifDate, String password, String jabatan) {
        this.npk = npk;
        this.namaKaryawan = namaKaryawan;
        this.fotoKaryawan = fotoKaryawan;
        this.plant = plant;
        this.departemen = departemen;
        this.email = email;
        this.noHandphone = noHandphone;
        this.tanggalLahir = tanggalLahir;
        this.status = status;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifBy = modifBy;
        this.modifDate = modifDate;
        this.password = password;
        this.jabatan = jabatan;
    }

    public Karyawan(String kryNpk) {
        this.npk = kryNpk;
    }

    public String getNpk() {
        return npk;
    }

    public void setNpk(String npk) {
        this.npk = npk;
    }

    public String getNamaKaryawan() {
        return namaKaryawan;
    }

    public void setNamaKaryawan(String namaKaryawan) {
        this.namaKaryawan = namaKaryawan;
    }

    public String getFotoKaryawan() {
        return fotoKaryawan;
    }

    public void setFotoKaryawan(String fotoKaryawan) {
        this.fotoKaryawan = fotoKaryawan;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getDepartemen() {
        return departemen;
    }

    public void setDepartemen(String departemen) {
        this.departemen = departemen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoHandphone() {
        return noHandphone;
    }

    public void setNoHandphone(String noHandphone) {
        this.noHandphone = noHandphone;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifBy() {
        return modifBy;
    }

    public void setModifBy(String modifBy) {
        this.modifBy = modifBy;
    }

    public LocalDateTime getModifDate() {
        return modifDate;
    }

    public void setModifDate(LocalDateTime modifDate) {
        this.modifDate = modifDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }
}
