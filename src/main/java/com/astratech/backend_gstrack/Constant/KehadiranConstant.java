package com.astratech.backend_gstrack.Constant;

public class KehadiranConstant {
    public static final String qFilterTanggal = "SELECT k FROM gs_track_absensi k WHERE (?1 IS NULL OR k.tanggal >= ?1) AND (?2 IS NULL OR k.tanggal <= ?2)";

}
