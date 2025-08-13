//JaminanRepository
package com.astratech.backend_gstrack.Repository;

import com.astratech.backend_gstrack.VO.Jaminan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository("JaminanRepository")
public interface JaminanRepository extends JpaRepository<Jaminan, Serializable> {
    //get data jaminan berdasarkan noreqrc
    Jaminan findByPsjNoRequestRecord(String psjNoRequestRecord);

    //mengambil data jaminan lalu mengurutkan berdasarkan status jaminan
    @Query("""
    SELECT i FROM Jaminan i ORDER BY CASE i.psjStatus
        WHEN 'Menunggu Persetujuan HC' THEN 1
        WHEN 'Selesai' THEN 2
        WHEN 'Ditolak' THEN 3
        ELSE 4
      END
    """)
    List<Jaminan> findAllOrderByCustomStatusAndPsjCreatedDateDesc();

    //get semua jaminan milik 1 karyawan (psj_npk) lalu mengurutkan berdasarkan tanggal dibuat
    List<Jaminan> findByKryNpkOrderByPsjCreatedDateDesc(String kryNpk);

    @Query("""
    SELECT i FROM Jaminan i 
    WHERE FUNCTION('YEAR', i.psjCreatedDate) = :year
    AND i.kryNpk = :npk
    AND i.psjStatus = :status
    ORDER BY i.psjCreatedDate DESC
""")
    List<Jaminan> findByNpkAndStatusAndYear(
            @org.springframework.data.repository.query.Param("npk") String npk,
            @org.springframework.data.repository.query.Param("status") String status,
            @org.springframework.data.repository.query.Param("year") Integer year
    );


    @Query("""
    SELECT i FROM Jaminan i 
    WHERE FUNCTION('YEAR', i.psjCreatedDate) = :year
    AND i.kryNpk = :npk
    ORDER BY i.psjCreatedDate DESC
""")
    List<Jaminan> findByNpkAndYear(
            @org.springframework.data.repository.query.Param("npk") String npk,
            @org.springframework.data.repository.query.Param("year") Integer year
    );

    @Query("""
    SELECT i FROM Jaminan i 
    WHERE i.kryNpk = :npk
    AND i.psjStatus = :status
    ORDER BY i.psjCreatedDate DESC
""")
    List<Jaminan> findByNpkAndStatus(
            @org.springframework.data.repository.query.Param("npk") String npk,
            @org.springframework.data.repository.query.Param("status") String status
    );

    //fulldata
    @Query("SELECT j, o.orgNama, o.orgHubungan, k.jumlah_plafon, k.penggunaan_plafon, k.namaKaryawan, r.rsNama " +
            "FROM Jaminan j " +
            "LEFT JOIN Orang o ON j.orgId = o.orgId " +
            "LEFT JOIN Karyawan k ON j.kryNpk = k.npk " +
            "LEFT JOIN RumahSakit r ON j.rsId = r.rsId " +
            "WHERE j.kryNpk = :npk " +
            "ORDER BY CASE j.psjStatus " +
            "           WHEN 'Menunggu Persetujuan HC' THEN 1 " +
            "           WHEN 'Selesai' THEN 2 " +
            "           WHEN 'Ditolak' THEN 3 " +
            "           ELSE 4 " +
            "         END, " +
            "         j.psjCreatedDate DESC")
    List<Object[]> findByNpkWithOrangAndPlafonAndRs(@Param("npk") String npk);


    @Query(value = "SELECT MAX(psj_no_request_record) FROM gs_track_psj", nativeQuery = true)
    Optional<String> findMaxNoRequestRecord();




}