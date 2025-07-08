package com.astratech.backend_gstrack.Repository;

import com.astratech.backend_gstrack.VO.Karyawan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KaryawanRepository extends JpaRepository<Karyawan, String> {

    Karyawan findByNpk(String npk);

    List<Karyawan> findAllByOrderByNpkAsc();
}
