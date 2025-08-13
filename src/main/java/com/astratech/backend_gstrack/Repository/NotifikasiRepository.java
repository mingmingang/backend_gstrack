package com.astratech.backend_gstrack.Repository;

import com.astratech.backend_gstrack.VO.Notifikasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import static com.astratech.backend_gstrack.Constant.NotifikasiConstant.qGetAllbyNPK;
import static com.astratech.backend_gstrack.Constant.NotifikasiConstant.qIsRead;

public interface NotifikasiRepository extends JpaRepository<Notifikasi, Integer> {
    @Query(value = qGetAllbyNPK, nativeQuery = true)
    List<Notifikasi> findAllbyNPK(String npk);
    @Query(value = qGetAllbyNPK, nativeQuery = true)
    Notifikasi findByNPK(String npk);
    @Query(value = qIsRead,nativeQuery = true)
    Integer countByNPK(String npk);
}
