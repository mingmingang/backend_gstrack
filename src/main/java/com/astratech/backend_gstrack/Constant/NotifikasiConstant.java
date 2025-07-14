package com.astratech.backend_gstrack.Constant;

public class NotifikasiConstant {
    public static final String qGetAllbyNPK = "SELECT * FROM gs_track_notifikasi WHERE kry_npk = ?1";
    public static final String qIsRead = "SELECT COUNT(*) FROM gs_track_notifikasi WHERE kry_npk = ?1 AND status_dibaca = 0";
}
