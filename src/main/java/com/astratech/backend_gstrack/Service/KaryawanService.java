package com.astratech.backend_gstrack.Service;

import com.astratech.backend_gstrack.Repository.KaryawanRepository;
import com.astratech.backend_gstrack.VO.Karyawan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
//Karyawan Service
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

        existingKaryawan.setNamaKaryawan(karyawan.getNamaKaryawan());
        existingKaryawan.setEmail(karyawan.getEmail());
        existingKaryawan.setDepartemen(karyawan.getDepartemen());
        existingKaryawan.setNoHandphone(karyawan.getNoHandphone());
        existingKaryawan.setPlant(karyawan.getPlant());
        existingKaryawan.setStatus(karyawan.getStatus());
        existingKaryawan.setFotoKaryawan(karyawan.getFotoKaryawan());
        existingKaryawan.setTanggalLahir(karyawan.getTanggalLahir());
        existingKaryawan.setModifBy(karyawan.getModifBy());
        existingKaryawan.setModifDate(karyawan.getModifDate());

        karyawanRepository.save(existingKaryawan);
        return true;
    }

    @Transactional
    public boolean updatePassword(String npk, String newPassword) {
        int updated = karyawanRepository.updatePassword(npk, newPassword);
        return updated > 0;
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
