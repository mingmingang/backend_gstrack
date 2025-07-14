package com.astratech.backend_gstrack.Repository.RepositoryBantuan;

import com.astratech.backend_gstrack.VO.DataBantuan.RumahSakit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RumahSakitRepository extends JpaRepository<RumahSakit, Integer> {
    /**
     * Spring Data JPA akan secara otomatis membuat query untuk method ini.
     * Ini setara dengan: "SELECT * FROM gs_track_rumah_sakit WHERE rs_tipe = ? ORDER BY rs_nama ASC"
     * @param rsTipe Nilai dari kolom rs_tipe yang akan dicari (misal: "Rayon" atau "Non Rayon")
     * @return List dari entitas RumahSakit yang cocok.
     */
    List<RumahSakit> findByRsTipeOrderByRsNamaAsc(String rsTipe);
}
