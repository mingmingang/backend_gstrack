package com.astratech.backend_gstrack.Repository.RepositoryBantuan;

import com.astratech.backend_gstrack.VO.DataBantuan.Diagnosa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosaRepository extends JpaRepository<Diagnosa, String> {

    // Spring Data JPA secara otomatis menyediakan method findAll().
    // Untuk konsistensi, kita bisa menambahkan sorting di sini.
    List<Diagnosa> findAllByOrderByDgsNamaAsc();
}