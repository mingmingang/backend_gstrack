package com.astratech.backend_gstrack.Service.ServiceBantuan;

import com.astratech.backend_gstrack.Repository.RepositoryBantuan.DiagnosaRepository;
import com.astratech.backend_gstrack.VO.DataBantuan.Diagnosa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DiagnosaService {

    @Autowired
    private DiagnosaRepository diagnosaRepository;

    /**
     * Mengambil semua data diagnosa dari database dan mengurutkannya berdasarkan nama.
     * @return List dari entitas Diagnosa.
     */
    @Transactional(readOnly = true) // Menandakan transaksi ini hanya untuk membaca
    public List<Diagnosa> getAll() {
        // Menggunakan method yang sudah disorting
        List<Diagnosa> results = diagnosaRepository.findAllByOrderByDgsNamaAsc();
        System.out.println("DIAGNOSA SERVICE: Menemukan " + results.size() + " data diagnosa.");
        return results;
    }
}