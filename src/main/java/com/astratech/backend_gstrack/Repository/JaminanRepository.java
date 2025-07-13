//JaminanRepository
package com.astratech.backend_gstrack.Repository;

import com.astratech.backend_gstrack.VO.Jaminan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

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
}