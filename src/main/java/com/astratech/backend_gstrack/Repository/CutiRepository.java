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

    List<Cuti> findByNpkOrderByTanggalPengajuanDesc(String npk);

    List<Cuti> findByNpkAndTipeCutiAndStatus(String npk, String tipeCuti, String status);

    List<Cuti> findByNpkAndTipeCuti(String npk, String tipeCuti);

    List<Cuti> findByNpkAndStatus(String npk, String status);

    List<Cuti> findByNpk(String npk);
}
