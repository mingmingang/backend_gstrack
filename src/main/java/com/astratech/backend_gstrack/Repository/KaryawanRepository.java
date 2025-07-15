package com.astratech.backend_gstrack.Repository;

import com.astratech.backend_gstrack.VO.Karyawan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KaryawanRepository extends JpaRepository<Karyawan, String> {

    Karyawan findByNpk(String npk);

    List<Karyawan> findAllByOrderByNpkAsc();
    @Modifying
    @Query("UPDATE Karyawan k SET k.password = :newPassword WHERE k.npk = :npk")
    int updatePassword(@Param("npk") String npk, @Param("newPassword") String newPassword);

    @Query("SELECT k.jumlah_plafon, k.penggunaan_plafon FROM Karyawan k WHERE k.npk = :npk")
    List<Object[]> findPlafonByNpk(@Param("npk") String npk);

}
