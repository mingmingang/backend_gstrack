package com.astratech.backend_gstrack.Repository;

import com.astratech.backend_gstrack.VO.JatahCuti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JatahCutiRepository extends JpaRepository<JatahCuti, Integer> {

    List<JatahCuti> findByKryNpk(String kryNpk);

    List<JatahCuti> findByKryNpkAndTahun(String kryNpk, int tahun);

    List<JatahCuti> findAllByOrderByKryNpkAsc();

    List<JatahCuti> findByTahun(int tahun);
    List<JatahCuti> findByKryNpkAndTahunAndTipeCuti(String kryNpk, int tahun, String tipeCuti);

}
