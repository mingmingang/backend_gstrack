package com.astratech.backend_gstrack.Repository;

import com.astratech.backend_gstrack.VO.Cuti;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Repository("CutiRepository")
public interface CutiRepository extends JpaRepository<Cuti, Serializable> {

    Cuti findByCutiId(String cutiId);

    List<Cuti> findAllByOrderByTanggalPengajuanDesc();

    List<Cuti> findByNpkOrderByTanggalPengajuanDesc(String npk);

    List<Cuti> findByNpkAndTipeCutiAndStatus(String npk, String tipeCuti, String status);

    List<Cuti> findByNpkAndTipeCuti(String npk, String tipeCuti);

    List<Cuti> findByNpkAndStatus(String npk, String status);
    @Query("SELECT c FROM Cuti c WHERE " +
            "(:tipeCuti IS NULL OR c.tipeCuti = :tipeCuti) AND " +
            "(:status IS NULL OR c.status = :status)")
    List<Cuti> findByOptionalTipeCutiAndStatus(
            @Param("tipeCuti") String tipeCuti,
            @Param("status") String status);

    @Modifying
    @Transactional
    @Query("UPDATE Cuti c SET c.status = :status WHERE c.cutiId = :cutiId")
    void updateStatusCuti(@Param("cutiId") String cutiId, @Param("status") String status);



    List<Cuti> findByNpk(String npk);
}
