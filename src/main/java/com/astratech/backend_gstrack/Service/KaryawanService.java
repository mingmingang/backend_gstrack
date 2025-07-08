package com.astratech.backend_gstrack.Service;

import com.astratech.backend_gstrack.Repository.KaryawanRepository;
import com.astratech.backend_gstrack.VO.Karyawan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class KaryawanService {

    @Autowired
    private KaryawanRepository karyawanRepository;

    public Karyawan getKaryawanByNpk(String npk) {
        return karyawanRepository.findByNpk(npk);
    }

    public List<Karyawan> getAllKaryawan() {
        return karyawanRepository.findAllByOrderByNpkAsc();
    }

    public boolean saveKaryawan(Karyawan karyawan) {
        Karyawan result = karyawanRepository.save(karyawan);
        return result != null;
    }

    public boolean updateKaryawan(Karyawan karyawan) {
        Karyawan existingKaryawan = karyawanRepository.findByNpk(karyawan.getNpk());
        if (existingKaryawan == null) {
            return false;
        }

        if (StringUtils.hasLength(karyawan.getNamaKaryawan())) {
            existingKaryawan.setNamaKaryawan(karyawan.getNamaKaryawan());
        }

        if (StringUtils.hasLength(karyawan.getEmail())) {
            existingKaryawan.setEmail(karyawan.getEmail());
        }

        if (StringUtils.hasLength(karyawan.getDepartemen())) {
            existingKaryawan.setDepartemen(karyawan.getDepartemen());
        }

        if (StringUtils.hasLength(karyawan.getNoHandphone())) {
            existingKaryawan.setNoHandphone(karyawan.getNoHandphone());
        }

        if (StringUtils.hasLength(karyawan.getPlant())) {
            existingKaryawan.setPlant(karyawan.getPlant());
        }

        if (StringUtils.hasLength(karyawan.getStatus())) {
            existingKaryawan.setStatus(karyawan.getStatus());
        }

        karyawanRepository.save(existingKaryawan);
        return true;
    }

    public boolean deleteKaryawan(String npk) {
        Karyawan existingKaryawan = karyawanRepository.findByNpk(npk);
        if (existingKaryawan == null) {
            return false;
        }
        karyawanRepository.delete(existingKaryawan);
        return true;
    }
}
