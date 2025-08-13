package com.astratech.backend_gstrack.Service;

//import com.astratech.backend_gstrack.Repository.CutiDetailRepository;
import com.astratech.backend_gstrack.Repository.CutiRepository;
import com.astratech.backend_gstrack.VO.Cuti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class CutiService {

    @Qualifier("CutiRepository")
    @Autowired
    private CutiRepository cutiRepository;

//    @Autowired
//    private CutiDetailRepository cutiDetailRepository;

    public Cuti getCutiById(String cutiId) {
        return cutiRepository.findByCutiId(cutiId);
    }

    public List<Cuti> getAllCuti() {
        return cutiRepository.findAll();
    }

    public List<Cuti> getCutiByNpk(String npk) {
        return cutiRepository.findByNpkOrderByTanggalPengajuanDesc(npk);
    }

    public boolean saveCuti(Cuti cuti) {
        Cuti result = cutiRepository.save(cuti);
        return result != null;
    }

    public boolean updateCuti(Cuti cuti) {
        Cuti existingCuti = cutiRepository.findByCutiId(cuti.getCutiId());
        if (existingCuti == null) {
            return false;
        }

        if (StringUtils.hasLength(cuti.getJenisCuti())) {
            existingCuti.setJenisCuti(cuti.getJenisCuti());
        }

        if (cuti.getTanggalAwal() != null) {
            existingCuti.setTanggalAwal(cuti.getTanggalAwal());
        }

        if (cuti.getTanggalAkhir() != null) {
            existingCuti.setTanggalAkhir(cuti.getTanggalAkhir());
        }

        if (cuti.getTanggalPengajuan() != null) {
            existingCuti.setTanggalPengajuan(cuti.getTanggalPengajuan());
        }

        if (StringUtils.hasLength(cuti.getAlasan())) {
            existingCuti.setAlasan(cuti.getAlasan());
        }

        if (StringUtils.hasLength(cuti.getStatus())) {
            existingCuti.setStatus(cuti.getStatus());
        }

        cutiRepository.save(existingCuti);
        return true;
    }

    public boolean deleteCuti(String cutiId) {
        Cuti existingCuti = cutiRepository.findByCutiId(cutiId);
        if (existingCuti == null) {
            return false;
        }
        cutiRepository.delete(existingCuti);
        return true;
    }

    public List<Cuti> getCutiByNpkAndJenis(String npk, String tipeCuti) {
        return cutiRepository.findByNpkAndTipeCuti(npk, tipeCuti);
    }

    public List<Cuti> getCutiByNpkAndStatus(String npk, String status) {
        return cutiRepository.findByNpkAndStatus(npk, status);
    }

    public List<Cuti> getCutiByNpkAndJenisAndStatus(String npk, String tipeCuti, String status) {
        return cutiRepository.findByNpkAndTipeCutiAndStatus(npk, tipeCuti, status);
    }

    public List<Cuti> getCutiByOptionalTipeCutiAndStatus(String tipeCuti, String status) {
        return cutiRepository.findByOptionalTipeCutiAndStatus(tipeCuti, status);
    }

    @Transactional
    public void updateStatusCuti(String cutiId, String status) {
        cutiRepository.updateStatusById(cutiId, status);
    }


//    @Transactional
//    public void updateStatusCuti(String cutiId, String status) {
//        Optional<Cuti> optionalCuti = cutiRepository.findById(cutiId);
//        if (optionalCuti.isPresent()) {
//            Cuti cuti = optionalCuti.get();
//            cuti.setStatus(status);
//            cutiRepository.save(cuti);
//
//            // Jika statusnya Ditolak, maka update semua tanggal cuti detail menjadi Ditolak juga
//            if ("Ditolak".equalsIgnoreCase(status)) {
//                cutiDetailRepository.updateSemuaStatusDetail(cutiId, "Ditolak");
//            }
//        } else {
//            throw new RuntimeException("Cuti tidak ditemukan");
//        }
//    }


}
