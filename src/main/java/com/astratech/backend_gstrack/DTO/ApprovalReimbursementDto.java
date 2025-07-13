package com.astratech.backend_gstrack.DTO;

import java.math.BigInteger;

public class ApprovalReimbursementDto {
    /**
     * ID unik dari reimbursement yang akan diupdate.
     * Sesuai dengan body JSON: { "rbmId": 12345, ... }
     */
    private BigInteger rbmId;

    /**
     * Status baru yang dikirim dari frontend (e.g., "Setujui", "Ditolak").
     * Sesuai dengan body JSON: { "newStatus": "Setujui", ... }
     */
    private String newStatus;

    /**
     * NPK dari pengguna (atasan/admin) yang melakukan aksi update status.
     * Sesuai dengan body JSON: { "modifiedByNpk": "230093", ... }
     */
    private String modifiedByNpk;

    // --- Getters and Setters ---
    // Diperlukan oleh Spring Boot (Jackson) untuk mem-parsing JSON menjadi objek ini.

    public BigInteger getRbmId() {
        return rbmId;
    }

    public void setRbmId(BigInteger rbmId) {
        this.rbmId = rbmId;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    public String getModifiedByNpk() {
        return modifiedByNpk;
    }

    public void setModifiedByNpk(String modifiedByNpk) {
        this.modifiedByNpk = modifiedByNpk;
    }
}
