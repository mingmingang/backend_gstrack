package com.astratech.backend_gstrack.Service;

import com.astratech.backend_gstrack.Repository.PSKRepository;
import com.astratech.backend_gstrack.VO.PeSeKet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class PeSeKetService {

    @Autowired
    private PSKRepository pskRepository;

    /* ---------- mapper util ---------- */
    private Map<String,Object> mapRow(Object[] r){
        return Map.of(
                "id",              r[0],
                "kryNpk",          r[1],
                "ap",              r[2],
                "keterangan",      r[3],
                "status",          r[4],
                "createdDate",     r[5],
                "createdBy",       r[6],
                "kryNamaKaryawan", r[7] != null ? r[7] : "Unknown Employee",
                // --- PERUBAHAN DI SINI ---
                "kry_departemen",  r[8] != null ? r[8] : "Departemen Tidak Tersedia", // Tambahkan departemen
                "kry_jabatan",     r[9] != null ? r[9] : "Jabatan Tidak Tersedia"      // Tambahkan jabatan
        );
    }

    /* ---------- existing ---------- */
    public List<Map<String,Object>> getAllSorted(){
        return pskRepository
                .findAllWithEmployeeNameOrderByStatusPrioritized()
                .stream().map(this::mapRow).toList();
    }

    public List<Map<String,Object>> getAllByStatus(String status){
        if(status == null || status.isBlank()) return getAllSorted();
        return pskRepository
                .findAllByStatusWithEmployeeName(status)
                .stream().map(this::mapRow).toList();
    }

    /* ---------- NEW : filter by JENIS ALASAN (ap) ---------- */
    public List<Map<String,Object>> getAllByAlasan(String alasan){
        if(alasan == null || alasan.isBlank()) return getAllSorted();
        return pskRepository
                .findAllByAlasanWithEmployeeName(alasan)
                .stream().map(this::mapRow).toList();
    }

    /* ---------- lainnya ---------- */
    public List<Map<String,Object>> getById(String id){
        return pskRepository.getPSKById(id)
                .stream()
                .map(this::mapRow)
                .toList();
    }

    public boolean savePSK(PeSeKet req){
        try{
            String last = pskRepository.getLastId();              // e.g. PSK012
            int next    = last != null && last.startsWith("AR")
                    ? Integer.parseInt(last.substring(3))+1 : 1;

            PeSeKet ent = new PeSeKet();
            ent.setId(String.format("AR%03d",next));
            ent.setKryNpk(req.getKryNpk());
            ent.setAp(req.getAp());
            ent.setKeterangan(req.getKeterangan());
            ent.setStatus("Belum Diverifikasi");
            ent.setCreatedDate(LocalDateTime.now());
            ent.setCreatedBy("SYSTEM");

            pskRepository.save(ent);
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    public List<Map<String,Object>> getAllByAlasanAndStatus(String alasan, String status){
        if ((alasan == null || alasan.isBlank()) && (status == null || status.isBlank()))
            return getAllSorted();
        else if (alasan != null && !alasan.isBlank() && (status == null || status.isBlank()))
            return getAllByAlasan(alasan);
        else if (status != null && !status.isBlank() && (alasan == null || alasan.isBlank()))
            return getAllByStatus(status);

        // Kombinasi alasan + status
        return pskRepository
                .findAllByAlasanAndStatusWithEmployeeName(alasan, status)
                .stream().map(this::mapRow).toList();
    }
    public List<Map<String, Object>> getAllByCreatedDate(LocalDateTime start, LocalDateTime end) {
        return pskRepository.findAllByCreatedDateBetween(start, end)
                .stream().map(this::mapRow).toList();
    }
    public List<Map<String, Object>> getAllByCreatedDateAndStatusAndAlasan(
            String status, String alasan, LocalDateTime start, LocalDateTime end) {

        return pskRepository
                .findAllByCreatedDateAndStatusAndAlasan(start, end, status, alasan)
                .stream().map(this::mapRow).toList();
    }


}

