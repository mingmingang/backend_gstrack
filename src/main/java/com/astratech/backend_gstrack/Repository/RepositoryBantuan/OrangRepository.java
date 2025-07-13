package com.astratech.backend_gstrack.Repository.RepositoryBantuan;

import com.astratech.backend_gstrack.VO.DataBantuan.Orang;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrangRepository extends JpaRepository<Orang, Integer> {
    /**
     * Finds a list of Keluarga entities based on the NPK of the related Karyawan.
     * We use a custom JPQL query to define the logic explicitly.
     * @ param npk The Nomor Pokok Karyawan to search for.
     * @return A list of matching Keluarga entities.
     */
//    @Query("SELECT o FROM Orang o WHERE o.karyawan.npk = :npk")
//    List<Orang> findByKryNpk(@Param("npk") String npk);

//    @Query("SELECT r FROM Reimbursement r LEFT JOIN FETCH r.orang WHERE r.karyawan.npk = :npk")
//    List<Orang> findOrangByNpk(@Param("npk") String npk);

    List<Orang> findByKryNpk(String kryNpk);
}
