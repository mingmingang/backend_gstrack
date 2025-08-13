package com.astratech.backend_gstrack.Service;

import com.astratech.backend_gstrack.Repository.CutiDetailRepository;
import com.astratech.backend_gstrack.VO.CutiDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
public class CutiDetailService {

    @Qualifier("CutiDetailRepository")
    @Autowired
    private CutiDetailRepository cutiDetailRepository;

    public List<CutiDetail> getAllCutiDetail() {
        return cutiDetailRepository.findAll();
    }

    public List<CutiDetail> getCutiDetailByCutiId(String cutiId) {
        return cutiDetailRepository.findByCutiId(cutiId);
    }

    public CutiDetail getCutiDetailByCutiIdAndTanggal(String cutiId, Date tanggalCuti) {
        return cutiDetailRepository.findByCutiIdAndTanggalCuti(cutiId, tanggalCuti);
    }

    public List<CutiDetail> getCutiDetailByStatus(String status) {
        return cutiDetailRepository.findByStatus(status);
    }

    public List<CutiDetail> getCutiDetailByTanggal(Date tanggalCuti) {
        return cutiDetailRepository.findByTanggalCuti(tanggalCuti);
    }

    public boolean saveCutiDetail(CutiDetail cutiDetail) {
        CutiDetail result = cutiDetailRepository.save(cutiDetail);
        return result != null;
    }

    public boolean deleteCutiDetail(Long cutiDetailId) {
        if (!cutiDetailRepository.existsById(cutiDetailId)) {
            return false;
        }
        cutiDetailRepository.deleteById(cutiDetailId);
        return true;
    }

    public boolean updateStatusTanggalCuti(String cutiId, List<Date> tanggalList, String newStatus) {
        try {
            for (Date tanggal : tanggalList) {
                CutiDetail detail = cutiDetailRepository.findByCutiIdAndTanggalCuti(cutiId, tanggal);
                if (detail != null) {
                    detail.setStatus(newStatus);
                    cutiDetailRepository.save(detail);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public boolean batalkanBeberapaTanggal(String cutiId, List<String> tanggalList, String alasan) {
        for (String tgl : tanggalList) {
            cutiDetailRepository.updateStatusDanAlasan(cutiId, Date.valueOf(tgl), "Dibatalkan", alasan);
        }
        return true;
    }

    public boolean cekSemuaTanggalDibatalkan(String cutiId) {
        List<CutiDetail> details = cutiDetailRepository.findByCutiId(cutiId);
        return details.stream().allMatch(cd -> "Dibatalkan".equalsIgnoreCase(cd.getStatus()));
    }

    @Transactional
    public void updateSemuaStatusDetail(String cutiId, String status) {
        cutiDetailRepository.updateSemuaStatusDetail(cutiId, status);
    }



}
