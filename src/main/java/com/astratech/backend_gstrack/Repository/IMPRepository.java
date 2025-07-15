package com.astratech.backend_gstrack.Repository;

import com.astratech.backend_gstrack.VO.IMP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("IMPRepository")
public interface IMPRepository extends JpaRepository<IMP, Serializable> {
    IMP getIMPByImpId(int impId);
    IMP findByImpNoRequest(String impNoRequest);
    List<IMP> findAllByOrderByImpIdDesc();
    List<IMP> findByImpNpkOrderByImpCreatedDateDesc(String impNpk);

    @Query("SELECT DISTINCT YEAR(i.impCreatedDate) " +
            "FROM IMP i WHERE i.impNpk = :npk ORDER BY YEAR(i.impCreatedDate) DESC")
    List<IMP> findAvailableYearsByNpk(@Param("npk") String npk);

    @Query("""
    SELECT i FROM IMP i 
    WHERE FUNCTION('YEAR', i.impCreatedDate) = :year
    AND i.impNpk = :npk
    AND i.impStatus = :status
    ORDER BY i.impCreatedDate DESC
""")
    List<IMP> findByNpkAndStatusAndYear(
            @Param("npk") String npk,
            @Param("status") String status,
            @Param("year") Integer year
    );

    @Query("""
    SELECT i FROM IMP i 
    WHERE FUNCTION('YEAR', i.impCreatedDate) = :year
    AND i.impNpk = :npk
    ORDER BY i.impCreatedDate DESC
""")
    List<IMP> findByNpkAndYear(
            @Param("npk") String npk,
            @Param("year") Integer year
    );

    @Query("""
    SELECT i FROM IMP i 
    WHERE i.impNpk = :npk
    AND i.impStatus = :status
    ORDER BY i.impCreatedDate DESC
""")
    List<IMP> findByNpkAndStatus(
            @Param("npk") String npk,
            @Param("status") String status
    );
}
