package com.astratech.backend_gstrack.Repository;

import com.astratech.backend_gstrack.VO.PeSeKet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PSKRepository extends JpaRepository<PeSeKet, String> {

    @Query("SELECT p.id, p.kryNpk, p.ap, p.keterangan, p.status, p.createdDate, p.createdBy, " +
            "k.namaKaryawan, k.departemen, k.jabatan " + // <-- PERBAIKAN
            "FROM PeSeKet p " +
            "LEFT JOIN Karyawan k ON p.kryNpk = k.npk " +
            "ORDER BY CASE p.status " +
            "WHEN 'Belum Diverifikasi' THEN 1 " +
            "WHEN 'Diverifikasi' THEN 2 " +
            "WHEN 'Disetujui' THEN 3 " +
            "WHEN 'Ditolak' THEN 4 " +
            "ELSE 5 END, p.createdDate DESC")
    List<Object[]> findAllWithEmployeeNameOrderByStatusPrioritized();

    @Query("SELECT COALESCE(MAX(p.id), 'PSK000') FROM PeSeKet p")
    String getLastId();

    @Query(value = "SELECT p.psk_id, p.kry_npk, p.psk_ap, p.psk_ket, p.psk_status, p.psk_crea_date, p.psk_crea_by, " +
            "k.kry_nama_karyawan, " +
            "k.kry_departemen, " +
            "k.kry_jabatan " +
            "FROM gs_track_psk p " +
            "LEFT JOIN tlkp_karyawan k ON p.kry_npk = k.kry_npk " +
            "WHERE p.psk_id = :id", nativeQuery = true)
    List<Object[]> getPSKById(@Param("id") String id);

    @Query("SELECT p.id, p.kryNpk, p.ap, p.keterangan, p.status, p.createdDate, p.createdBy, " +
            "k.namaKaryawan, k.departemen, k.jabatan " + // <-- PERBAIKAN
            "FROM PeSeKet p " +
            "LEFT JOIN Karyawan k ON p.kryNpk = k.npk " +
            "WHERE p.status = :status " +
            "ORDER BY p.createdDate DESC")
    List<Object[]> findAllByStatusWithEmployeeName(String status);

    @Query("""
           SELECT p.id, p.kryNpk, p.ap, p.keterangan, p.status, p.createdDate,
                  p.createdBy, k.namaKaryawan, k.departemen, k.jabatan
             FROM PeSeKet p
             LEFT JOIN Karyawan k ON p.kryNpk = k.npk
            WHERE LOWER(p.ap) = LOWER(:alasan)
         ORDER BY p.createdDate DESC
           """) // <-- PERBAIKAN
    List<Object[]> findAllByAlasanWithEmployeeName(String alasan);

    @Query("""
            SELECT p.id, p.kryNpk, p.ap, p.status, p.createdDate, p.createdBy,
                   k.namaKaryawan, k.departemen, k.jabatan
            FROM PeSeKet p
            LEFT JOIN Karyawan k ON p.kryNpk = k.npk
            WHERE LOWER(p.ap) = LOWER(:alasan)
            AND LOWER(p.status) = LOWER(:status)
            ORDER BY FIELD(LOWER(p.status), 'belum diverifikasi', 'diverifikasi', 'disetujui', 'ditolak')
            """) // <-- PERBAIKAN
    List<Object[]> findAllByAlasanAndStatusWithEmployeeName(String alasan, String status);

    @Query("""
           SELECT p.id, p.kryNpk, p.ap, p.keterangan, p.status,
                  p.createdDate, p.createdBy, k.namaKaryawan, k.departemen, k.jabatan
             FROM PeSeKet p
             LEFT JOIN Karyawan k ON p.kryNpk = k.npk
            WHERE p.createdDate BETWEEN :start AND :end
         ORDER BY p.createdDate DESC
           """) // <-- PERBAIKAN
    List<Object[]> findAllByCreatedDateBetween(LocalDateTime start,
                                               LocalDateTime end);
    @Query("""
    SELECT p.id, p.kryNpk, p.ap, p.keterangan, p.status, p.createdDate, p.createdBy,
           k.namaKaryawan, k.departemen, k.jabatan
    FROM PeSeKet p
    LEFT JOIN Karyawan k ON p.kryNpk = k.npk
    WHERE LOWER(p.ap) = LOWER(:alasan)
      AND LOWER(p.status) = LOWER(:status)
      AND p.createdDate BETWEEN :start AND :end
    ORDER BY p.createdDate DESC
    """) // <-- PERBAIKAN
    List<Object[]> findAllByCreatedDateAndStatusAndAlasan(
            LocalDateTime start,
            LocalDateTime end,
            String status,
            String alasan);

}