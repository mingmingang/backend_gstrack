package com.astratech.backend_gstrack.Constant;

public class KehadiranConstant {
    public static final String qFilterTanggal =
            "SELECT * FROM gs_track_absensi " +
                    "WHERE (?1 IS NULL OR tanggal >= ?1) " +
                    "AND (?2 IS NULL OR tanggal <= ?2) " +
                    "AND (?3 IS NULL OR kry_npk = ?3)";
    public static final String qValidateKehadiran = "SELECT * FROM gs_track_absensi WHERE tanggal = ?1 AND jam_masuk IS NOT NULL";
    public static final String qValidateKeluar = "SELECT TOP 1 * FROM gs_track_absensi WHERE kry_npk = ?1 ORDER BY absensi_id DESC";
    public static final String qCurrentKehadiranSession = "SELECT TOP 1 * FROM gs_track_absensi WHERE tanggal = ?1 AND kry_npk = ?2 ORDER BY absensi_id ASC";
    public static final String qCurrentLoggedKehadiran = "SELECT * FROM gs_track_absensi WHERE kry_npk = ?1";
}