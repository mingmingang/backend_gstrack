package com.astratech.backend_gstrack.Service.ServiceBantuan;

import com.astratech.backend_gstrack.Repository.RepositoryBantuan.RumahSakitRepository;
import com.astratech.backend_gstrack.VO.DataBantuan.RumahSakit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RumahSakitService {

    @Autowired
    private RumahSakitRepository rumahSakitRepository;

    /**
     * Mengambil data rumah sakit berdasarkan tipenya (Rayon / Non Rayon).
     * @param tipe Tipe rumah sakit yang akan difilter.
     * @return List dari entitas RumahSakit yang cocok.
     */
    @Transactional(readOnly = true)
    public List<RumahSakit> getByTipe(String tipe) {
        // Memanggil method kustom dari repository
        System.out.println("RUMAH SAKIT SERVICE: Mencari RS dengan tipe = '" + tipe + "'");
        List<RumahSakit> results = rumahSakitRepository.findByRsTipeOrderByRsNamaAsc(tipe);
        System.out.println("RUMAH SAKIT SERVICE: Menemukan " + results.size() + " data untuk tipe '" + tipe + "'");
        return results;
    }
}