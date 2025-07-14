package com.astratech.backend_gstrack.Repository.RepositoryBantuan;

import com.astratech.backend_gstrack.VO.DataBantuan.Keluarga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KeluargaRepository extends JpaRepository<Keluarga, Integer> {
    /**
     * Finds a list of Keluarga entities based on the NPK of the related Karyawan.
     * We use a custom JPQL query to define the logic explicitly.
     * @param npk The Nomor Pokok Karyawan to search for.
     * @return A list of matching Keluarga entities.
     */
    @Query("SELECT k FROM Keluarga k WHERE k.karyawan.npk = :npk")
    List<Keluarga> findByKryNpk(@Param("npk") String npk);

    @Query("SELECT k FROM Keluarga k LEFT JOIN FETCH k.orang WHERE k.karyawan.npk = :npk")
    List<Keluarga> findKeluargaByNpk(@Param("npk") String npk);
}
