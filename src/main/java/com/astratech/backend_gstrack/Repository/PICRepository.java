package com.astratech.backend_gstrack.Repository;

import com.astratech.backend_gstrack.VO.PIC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PICRepository extends JpaRepository<PIC, String> {

    @Query("SELECT p.id, p.kryNpk, p.ah, p.status, p.createdDate, p.createdBy, " +
            "k.namaKaryawan as kryNamaKaryawan " +
            "FROM PIC p " +
            "LEFT JOIN Karyawan k ON p.kryNpk = k.npk " +
            "ORDER BY CASE p.status " +
            "WHEN 'Belum Diverifikasi' THEN 1 " +
            "WHEN 'Diverifikasi' THEN 2 " +
            "WHEN 'Disetujui' THEN 3 " +
            "WHEN 'Ditolak' THEN 4 " +
            "ELSE 5 END, p.createdDate DESC")
    List<Object[]> findAllWithEmployeeNameOrderByStatusPrioritized();

    @Query("SELECT COALESCE(MAX(p.id), 'PIC000') FROM PIC p")
    String getLastId();
    @Query("SELECT p.id, p.kryNpk, p.ah, p.status, p.createdDate, p.createdBy, " +
            "k.namaKaryawan as kryNamaKaryawan " +
            "FROM PIC p " +
            "LEFT JOIN Karyawan k ON p.kryNpk = k.npk " +
            "WHERE p.status = :status " +
            "ORDER BY p.createdDate DESC")
    List<Object[]> findAllByStatusWithEmployeeName(String status);
    @Query("""
           SELECT p.id, p.kryNpk, p.ah, p.status, p.createdDate, p.createdBy,
                  k.namaKaryawan AS kryNamaKaryawan
             FROM PIC p
             LEFT JOIN Karyawan k ON p.kryNpk = k.npk
            WHERE LOWER(p.ah) = LOWER(:alasan)     
         ORDER BY p.createdDate DESC
           """)
    List<Object[]> findAllByAlasanWithEmployeeName(String alasan);
    @Query("""
SELECT p.id, p.kryNpk, p.ah, p.status, p.createdDate, p.createdBy, k.namaKaryawan
FROM PIC p
LEFT JOIN Karyawan k ON p.kryNpk = k.npk
WHERE LOWER(p.ah) = LOWER(:alasan)
AND LOWER(p.status) = LOWER(:status)
ORDER BY FIELD(LOWER(p.status), 'belum diverifikasi', 'diverifikasi', 'disetujui', 'ditolak')
""")
    List<Object[]> findAllByAlasanAndStatusWithEmployeeName(String alasan, String status);
    @Query("""
           SELECT p.id, p.kryNpk, p.ah, p.status, p.createdDate, p.createdBy,
                  k.namaKaryawan AS kryNamaKaryawan
             FROM PIC p
             LEFT JOIN Karyawan k ON p.kryNpk = k.npk
            WHERE p.createdDate BETWEEN :start AND :end
         ORDER BY p.createdDate DESC
           """)
    List<Object[]> findAllByCreatedDateBetween(LocalDateTime start,
                                               LocalDateTime end);
    @Query("""
    SELECT p.id, p.kryNpk, p.ah, p.status, p.createdDate, p.createdBy, k.namaKaryawan
    FROM PIC p
    LEFT JOIN Karyawan k ON p.kryNpk = k.npk
    WHERE LOWER(p.ah) = LOWER(:alasan)
      AND LOWER(p.status) = LOWER(:status)
      AND p.createdDate BETWEEN :start AND :end
    ORDER BY p.createdDate DESC
""")
    List<Object[]> findAllByCreatedDateAndStatusAndAlasan(
            LocalDateTime start,
            LocalDateTime end,
            String status,
            String alasan);

}