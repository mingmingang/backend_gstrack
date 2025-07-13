package com.astratech.backend_gstrack.Repository;

import com.astratech.backend_gstrack.VO.PSK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PSKRepository extends JpaRepository<PSK, String> {

    @Query("SELECT p.id, p.kryNpk, p.ap, p.keterangan, p.status, p.createdDate, p.createdBy, " +
            "k.namaKaryawan as kryNamaKaryawan " +
            "FROM PSK p " +
            "LEFT JOIN Karyawan k ON p.kryNpk = k.npk " +
            "ORDER BY CASE p.status " +
            "WHEN 'Belum Diverifikasi' THEN 1 " +
            "WHEN 'Diverifikasi' THEN 2 " +
            "WHEN 'Selesai' THEN 3 " +
            "WHEN 'Ditolak' THEN 4 " +
            "ELSE 5 END, p.createdDate DESC")
    List<Object[]> findAllWithEmployeeNameOrderByStatusPrioritized();

    @Query("SELECT COALESCE(MAX(p.id), 'PSK000') FROM PSK p")
    String getLastId();

    @Query("SELECT p FROM PSK p WHERE p.id = :id")
    PSK getPSKById(String id);
}