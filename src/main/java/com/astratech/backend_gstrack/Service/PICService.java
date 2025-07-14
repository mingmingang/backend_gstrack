package com.astratech.backend_gstrack.Service;

import com.astratech.backend_gstrack.Repository.PICRepository;
import com.astratech.backend_gstrack.VO.PIC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

@Service
public class PICService {

    @Qualifier("PICRepository")
    @Autowired
    private PICRepository picRepository;

    public List<Map<String, Object>> getAllSorted() {
        List<Object[]> results = picRepository.findAllWithEmployeeNameOrderByStatusPrioritized();
        List<Map<String, Object>> picList = new ArrayList<>();

        for (Object[] result : results) {
            Map<String, Object> picMap = new HashMap<>();
            picMap.put("id", result[0]);
            picMap.put("kryNpk", result[1]);
            picMap.put("ah", result[2]);
            picMap.put("status", result[3]);
            picMap.put("createdDate", result[4]);
            picMap.put("createdBy", result[5]);
            picMap.put("kryNamaKaryawan", result[6] != null ? result[6] : "Unknown Employee");
            picList.add(picMap);
        }

        return picList;
    }

    public boolean savePIC(PIC request) {
        try {
            PIC entity = new PIC();
            String lastId = picRepository.getLastId();
            int next = 1;
            if (lastId != null && lastId.startsWith("PIC")) {
                next = Integer.parseInt(lastId.substring(3)) + 1;
            }
            entity.setId(String.format("PIC%03d", next));
            entity.setKryNpk(request.getKryNpk());
            entity.setAh(request.getAh());
            entity.setStatus("Belum Diverifikasi");
            entity.setCreatedDate(LocalDateTime.now());
            entity.setCreatedBy("SYSTEM");
            picRepository.save(entity);
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}