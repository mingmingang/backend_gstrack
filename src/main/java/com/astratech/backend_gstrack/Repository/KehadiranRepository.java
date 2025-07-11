package com.astratech.backend_gstrack.Repository;

import com.astratech.backend_gstrack.VO.Kehadiran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

import static com.astratech.backend_gstrack.Constant.KehadiranConstant.qFilterTanggal;
import static com.astratech.backend_gstrack.Constant.KehadiranConstant.qValidateKehadiran;

public interface KehadiranRepository extends JpaRepository<Kehadiran, Integer> {
    @Query(value = qFilterTanggal, nativeQuery = true)
    List<Kehadiran> FilterDate(Date startDate, Date endDate);
    @Query(value = qValidateKehadiran, nativeQuery = true)
    List<String> FilterNpk(Date tanggal);
}
