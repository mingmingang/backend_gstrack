//JaminanService
package com.astratech.backend_gstrack.Service;

import com.astratech.backend_gstrack.Repository.JaminanRepository;
import com.astratech.backend_gstrack.Repository.KaryawanRepository;
import com.astratech.backend_gstrack.Repository.RepositoryBantuan.OrangRepository;
import com.astratech.backend_gstrack.Repository.RepositoryBantuan.RumahSakitRepository;
import com.astratech.backend_gstrack.VO.DataBantuan.Orang;
import com.astratech.backend_gstrack.VO.DataBantuan.RumahSakit;
import com.astratech.backend_gstrack.VO.Jaminan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JaminanService {
    @Qualifier("JaminanRepository")
    @Autowired
    private JaminanRepository jaminanRepository;

    @Autowired
    private KaryawanRepository karyawanRepository;

    @Autowired
    private OrangRepository orangRepository;

    @Autowired
    private RumahSakitRepository rumahSakitRepository;

    public Integer[] getPlafonByNpk(String npk) {
        List<Object[]> results = karyawanRepository.findPlafonByNpk(npk);
        if (results != null && !results.isEmpty()) {
            Object[] result = results.get(0);
            Integer jumlahPlafon = (Integer) result[0];
            Integer penggunaanPlafon = (Integer) result[1];
            return new Integer[] { jumlahPlafon, penggunaanPlafon };
        }
        return null;
    }


    public List<Orang> getOrangByKryNpk(String npk) {
        return orangRepository.findByKryNpk(npk);
    }

    public List<RumahSakit> getAllRumahSakit() {
        return rumahSakitRepository.findAll();
    }

    public Jaminan getJaminanByNoRequest(String noRequest) {
        return jaminanRepository.findByPsjNoRequestRecord(noRequest);
    }

    public List<Jaminan> getAllJaminan() {
        return jaminanRepository.findAllOrderByCustomStatusAndPsjCreatedDateDesc();
    }

    public List<Jaminan> getJaminanByNpk(String npk) {
        return jaminanRepository.findByKryNpkOrderByPsjCreatedDateDesc(npk);
    }

    public List<Jaminan> getJaminanByNpkAndStatusAndYear(String npk, String status, Integer year) {
        return jaminanRepository.findByNpkAndStatusAndYear(npk, status, year);
    }

    public List<Jaminan> getJaminanByNpkAndStatus(String npk, String status) {
        return jaminanRepository.findByNpkAndStatus(npk, status);
    }

    public List<Jaminan> getJaminanByNpkAndYear(String npk, Integer year) {
        return jaminanRepository.findByNpkAndYear(npk, year);
    }

    public boolean saveJaminan(Jaminan jaminan) {
        Jaminan result = jaminanRepository.save(jaminan);
        return result != null;
    }
}