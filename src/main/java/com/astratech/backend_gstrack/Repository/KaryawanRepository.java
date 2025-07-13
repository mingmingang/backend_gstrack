package com.astratech.backend_gstrack.Repository;

import com.astratech.backend_gstrack.VO.Karyawan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.astratech.backend_gstrack.Constant.KaryawanConstant.qFilterLogin;

@Repository
public interface KaryawanRepository extends JpaRepository<Karyawan, String> {

    @Query(value = qFilterLogin, nativeQuery = true)
    Karyawan findByNpk(String npk);

    List<Karyawan> findAllByOrderByNpkAsc();
}
