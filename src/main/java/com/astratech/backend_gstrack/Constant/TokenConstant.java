package com.astratech.backend_gstrack.Constant;

public class TokenConstant {
    public static final String qToken = "SELECT * FROM gs_track_expo_token WHERE kry_npk = ?1 ORDER BY createdAt DESC LIMIT 1";
}
