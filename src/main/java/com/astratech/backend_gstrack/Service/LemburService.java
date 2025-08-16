package com.astratech.backend_gstrack.Service;

import com.astratech.backend_gstrack.Repository.LemburRepository;
import com.astratech.backend_gstrack.VO.Cuti;
import com.astratech.backend_gstrack.VO.Lembur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LemburService {

    @Qualifier("LemburRepository")
    @Autowired
    private LemburRepository mlemburRepository;

    public Lembur getLemburById(String id) {
        return mlemburRepository.findById(id).orElse(null);
    }

    public List<Lembur> getAllLembur() {
        return mlemburRepository.findAll();
    }
    public List<Lembur> getLemburByStatus(String status) {
        return mlemburRepository.findByLbrStatusIgnoreCase(status);
    }
    public Lembur addLembur(Lembur lembur) {
        return mlemburRepository.save(lembur);
    }

}
