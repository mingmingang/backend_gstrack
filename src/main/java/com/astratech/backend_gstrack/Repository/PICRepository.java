package com.astratech.backend_gstrack.Repository;

import com.astratech.backend_gstrack.VO.PIC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
            "WHEN 'Selesai' THEN 3 " +
            "WHEN 'Ditolak' THEN 4 " +
            "ELSE 5 END, p.createdDate DESC")
    List<Object[]> findAllWithEmployeeNameOrderByStatusPrioritized();

    @Query("SELECT COALESCE(MAX(p.id), 'PIC000') FROM PIC p")
    String getLastId();
}