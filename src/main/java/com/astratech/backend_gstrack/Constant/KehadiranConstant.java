package com.astratech.backend_gstrack.Constant;

public class KehadiranConstant {
    public static final String qFilterTanggal = "SELECT * FROM gs_track_absensi WHERE (?1 IS NULL OR tanggal >= ?1) AND (?2 IS NULL OR tanggal <= ?2)";
    public static final String qValidateKehadiran = "SELECT kry_npk FROM gs_track_absensi WHERE tanggal = ?1 AND jam_masuk IS NOT NULL";
}
