package com.astratech.backend_gstrack.Repository.RepositoryBantuan;

import com.astratech.backend_gstrack.VO.DataBantuan.RumahSakit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RumahSakitRepository extends JpaRepository<RumahSakit, Integer> {
    /**
     * [SEBELUMNYA]
     * Method ini secara otomatis membuat query "SELECT * FROM ...".
     * Jika ada data duplikat di tabel, metode ini akan mengambil semuanya.
     * Kita ganti dengan query kustom di bawah ini.
     *
     * List<RumahSakit> findByRsTipeOrderByRsNamaAsc(String rsTipe);
     */


    /**
     * [PERBAIKAN] Mengambil daftar rumah sakit yang unik berdasarkan tipenya.
     * Menggunakan klausa 'DISTINCT' dalam query JPQL untuk memastikan setiap entitas
     * RumahSakit yang dikembalikan adalah unik, meskipun ada data duplikat di tabel.
     *
     * @param tipe Tipe rumah sakit yang akan dicari (misalnya: "Rayon" atau "Non Rayon").
     * @return Daftar RumahSakit yang sudah unik, diurutkan berdasarkan nama.
     */
    @Query("SELECT DISTINCT rs FROM RumahSakit rs WHERE rs.rsTipe = :tipe ORDER BY rs.rsNama ASC")
    List<RumahSakit> findUniqueByTipe(@Param("tipe") String tipe);
    List<RumahSakit> findByRsTipeOrderByRsNamaAsc(String rsTipe);

}
