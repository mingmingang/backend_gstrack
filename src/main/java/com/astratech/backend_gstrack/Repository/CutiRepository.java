package com.astratech.backend_gstrack.Repository;

import com.astratech.backend_gstrack.VO.Cuti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("CutiRepository")
public interface CutiRepository extends JpaRepository<Cuti, Serializable> {

    Cuti findByCutiId(String cutiId);

    List<Cuti> findAllByOrderByTanggalPengajuanDesc();

    List<Cuti> findByUserIdOrderByTanggalPengajuanDesc(String userId);

    List<Cuti> findByUserIdAndJenisCutiAndStatus(String userId, String jenisCuti, String status);
    List<Cuti> findByUserIdAndJenisCuti(String userId, String jenisCuti);
    List<Cuti> findByUserIdAndStatus(String userId, String status);
    List<Cuti> findByUserId(String userId);
}
