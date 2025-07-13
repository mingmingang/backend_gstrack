package com.astratech.backend_gstrack.Service;

import com.astratech.backend_gstrack.Repository.PSKRepository;
import com.astratech.backend_gstrack.VO.PSK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

@Service
public class PSKService {
    @Qualifier("PSKRepository")
    @Autowired
    private PSKRepository pskRepository;

    public List<Map<String, Object>> getAllSorted() {
        List<Object[]> results = pskRepository.findAllWithEmployeeNameOrderByStatusPrioritized();
        List<Map<String, Object>> pskList = new ArrayList<>();

        for (Object[] result : results) {
            Map<String, Object> pskMap = new HashMap<>();
            pskMap.put("id", result[0]);
            pskMap.put("kryNpk", result[1]);
            pskMap.put("ap", result[2]);
            pskMap.put("keterangan", result[3]);
            pskMap.put("status", result[4]);
            pskMap.put("createdDate", result[5]);
            pskMap.put("createdBy", result[6]);
            pskMap.put("kryNamaKaryawan", result[7] != null ? result[7] : "Unknown Employee");
            pskList.add(pskMap);
        }

        return pskList;
    }

    public PSK getById(String id) {
        return pskRepository.getPSKById(id);
    }

    public boolean savePSK(PSK request) {
        try {
            PSK entity = new PSK();
            String lastId = pskRepository.getLastId();
            int next = 1;
            if (lastId != null && lastId.startsWith("PSK")) {
                next = Integer.parseInt(lastId.substring(3)) + 1;
            }
            String generatedId = String.format("PSK%03d", next);
            entity.setId(generatedId);
            entity.setKryNpk(request.getKryNpk());
            entity.setAp(request.getAp());
            entity.setKeterangan(request.getKeterangan());
            entity.setStatus("Belum Diverifikasi");
            entity.setCreatedDate(LocalDateTime.now());
            entity.setCreatedBy("SYSTEM");
            pskRepository.save(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}