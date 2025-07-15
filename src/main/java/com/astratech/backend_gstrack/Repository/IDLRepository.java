package com.astratech.backend_gstrack.Repository;

import com.astratech.backend_gstrack.VO.IDL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("IDLRepository")
public interface IDLRepository extends JpaRepository<IDL, Serializable> {
    IDL findByIdlNoRequest(String idlNoRequest);

    @Query("""
    SELECT i FROM IDL i ORDER BY CASE i.idlStatus
        WHEN 'Menunggu Persetujuan' THEN 1
        WHEN 'Belum Diverifikasi' THEN 2
        WHEN 'Selesai' THEN 3
        WHEN 'Ditolak' THEN 4
        ELSE 5
      END
    """)
    List<IDL> findAllOrderByCustomStatusAndIdlCreatedDateDesc();

    List<IDL> findByIdlNpkOrderByIdlCreatedDateDesc(String idlNpk);

    @Query("""
    SELECT i FROM IDL i 
    WHERE FUNCTION('YEAR', i.idlCreatedDate) = :year
    AND i.idlNpk = :npk
    AND i.idlStatus = :status
    ORDER BY i.idlCreatedDate DESC
""")
    List<IDL> findByNpkAndStatusAndYear(
            @org.springframework.data.repository.query.Param("npk") String npk,
            @org.springframework.data.repository.query.Param("status") String status,
            @org.springframework.data.repository.query.Param("year") Integer year
    );

    @Query("""
    SELECT i FROM IDL i 
    WHERE FUNCTION('YEAR', i.idlCreatedDate) = :year
    AND i.idlNpk = :npk
    ORDER BY i.idlCreatedDate DESC
""")
    List<IDL> findByNpkAndYear(
            @org.springframework.data.repository.query.Param("npk") String npk,
            @org.springframework.data.repository.query.Param("year") Integer year
    );

    @Query("""
    SELECT i FROM IDL i 
    WHERE i.idlNpk = :npk
    AND i.idlStatus = :status
    ORDER BY i.idlCreatedDate DESC
""")
    List<IDL> findByNpkAndStatus(
            @org.springframework.data.repository.query.Param("npk") String npk,
            @org.springframework.data.repository.query.Param("status") String status
    );

    @Query("SELECT DISTINCT FUNCTION('YEAR', i.idlCreatedDate) FROM IDL i WHERE i.idlNpk = :npk ORDER BY 1 DESC")
    List<Integer> findAvailableYearsByNpk(@Param("npk") String npk);


}
