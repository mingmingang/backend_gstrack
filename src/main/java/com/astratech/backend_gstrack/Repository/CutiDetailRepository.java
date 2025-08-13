package com.astratech.backend_gstrack.Repository;

import com.astratech.backend_gstrack.VO.CutiDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository("CutiDetailRepository")
public interface CutiDetailRepository extends JpaRepository<CutiDetail, Long> {

    List<CutiDetail> findByCutiId(String cutiId);

    CutiDetail findByCutiIdAndTanggalCuti(String cutiId, java.sql.Date tanggalCuti);

    // Cari semua berdasarkan status
    List<CutiDetail> findByStatus(String status);

    // Cari semua berdasarkan tanggal_cuti
    List<CutiDetail> findByTanggalCuti(java.sql.Date tanggalCuti);
    @Modifying
    @Query("UPDATE CutiDetail cd SET cd.status = :status, cd.alasan = :alasan WHERE cd.cutiId = :cutiId AND cd.tanggalCuti = :tanggal")
    void updateStatusDanAlasan(
            @Param("cutiId") String cutiId,
            @Param("tanggal") Date tanggal,
            @Param("status") String status,
            @Param("alasan") String alasan
    );

    @Transactional
    @Modifying
    @Query("UPDATE CutiDetail cd SET cd.status = :status WHERE cd.cutiId = :cutiId")
    void updateSemuaStatusDetail(@Param("cutiId") String cutiId, @Param("status") String status);




}
