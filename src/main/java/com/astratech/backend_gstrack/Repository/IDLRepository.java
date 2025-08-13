package com.astratech.backend_gstrack.Repository;

import com.astratech.backend_gstrack.VO.IDL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

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

    @Query("SELECT DISTINCT YEAR(i.idlCreatedDate) FROM IDL i ORDER BY YEAR(i.idlCreatedDate) DESC")
    List<Integer> findDistinctYears();

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
    ORDER BY i.idlCreatedDate DESC
""")
    List<IDL> findAllByYear(@Param("year") Integer year);

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

    // Method untuk mendapatkan IDL yang terakhir dibuat (secara global)
    @Query("SELECT i FROM IDL i ORDER BY i.idlCreatedDate DESC LIMIT 1")
    Optional<IDL> findLatestIdl();

    // Method untuk mendapatkan IDL yang terakhir dibuat berdasarkan NPK
    @Query("SELECT i FROM IDL i WHERE i.idlNpk = :npk ORDER BY i.idlCreatedDate DESC LIMIT 1")
    Optional<IDL> findLatestIdlByNpk(@Param("npk") String npk);

    // Method untuk mendapatkan IDL yang terakhir dibuat berdasarkan status
    @Query("SELECT i FROM IDL i WHERE i.idlStatus = :status ORDER BY i.idlCreatedDate DESC LIMIT 1")
    Optional<IDL> findLatestIdlByStatus(@Param("status") String status);

    // Method untuk mendapatkan IDL yang terakhir dibuat berdasarkan NPK dan status
    @Query("SELECT i FROM IDL i WHERE i.idlNpk = :npk AND i.idlStatus = :status ORDER BY i.idlCreatedDate DESC LIMIT 1")
    Optional<IDL> findLatestIdlByNpkAndStatus(@Param("npk") String npk, @Param("status") String status);


}
