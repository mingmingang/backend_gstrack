package com.astratech.backend_gstrack.Constant;

public class TokenConstant {
    public static final String qToken = "SELECT TOP 1 * FROM gs_track_expo_token WHERE kry_npk = ?1 ORDER BY created_at DESC";
}
