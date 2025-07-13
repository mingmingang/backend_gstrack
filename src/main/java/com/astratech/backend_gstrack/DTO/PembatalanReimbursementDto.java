package com.astratech.backend_gstrack.DTO;

public class PembatalanReimbursementDto {
    private String kryNpk; // NPK karyawan yang melakukan pembatalan
    private String rbmAlasanPembatalan; // Alasan yang diinput dari frontend

    public String getKryNpk() {
        return kryNpk;
    }

    public void setKryNpk(String kryNpk) {
        this.kryNpk = kryNpk;
    }

    public String getRbmAlasanPembatalan() {
        return rbmAlasanPembatalan;
    }

    public void setRbmAlasanPembatalan(String rbmAlasanPembatalan) {
        this.rbmAlasanPembatalan = rbmAlasanPembatalan;
    }
}
