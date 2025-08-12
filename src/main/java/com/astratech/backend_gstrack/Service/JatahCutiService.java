package com.astratech.backend_gstrack.Service;

import com.astratech.backend_gstrack.Repository.JatahCutiRepository;
import com.astratech.backend_gstrack.VO.JatahCuti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JatahCutiService {

    @Autowired
    private JatahCutiRepository jatahCutiRepository;

    public JatahCuti getById(Integer id) {
        return jatahCutiRepository.findById(id).orElse(null);
    }

    public List<JatahCuti> getAll() {
        return jatahCutiRepository.findAll();
    }

    public List<JatahCuti> getByNpk(String npk) {
        return jatahCutiRepository.findByKryNpk(npk);
    }

    public List<JatahCuti> getByNpkAndTahun(String npk, int tahun) {
        return jatahCutiRepository.findByKryNpkAndTahun(npk, tahun);
    }

    public List<JatahCuti> getByTahun(int tahun) {
        return jatahCutiRepository.findByTahun(tahun);
    }

    public List<JatahCuti> getByNpkTahunAndTipeCuti(String npk, int tahun, String tipeCuti) {
        return jatahCutiRepository.findByKryNpkAndTahunAndTipeCuti(npk, tahun, tipeCuti);
    }

    public boolean saveJatahCuti(JatahCuti jatahCuti) {
        JatahCuti saved = jatahCutiRepository.save(jatahCuti);
        return saved != null;
    }

    public boolean updateJatahCuti(JatahCuti jatahCuti) {
        JatahCuti existing = jatahCutiRepository.findById(jatahCuti.getId()).orElse(null);
        if (existing == null) {
            return false;
        }

        existing.setKryNpk(jatahCuti.getKryNpk());
        existing.setTahun(jatahCuti.getTahun());
        existing.setHakCuti(jatahCuti.getHakCuti());
        existing.setCutiDipakai(jatahCuti.getCutiDipakai());
        existing.setCutiSisa(jatahCuti.getCutiSisa());
        existing.setTipeCuti(jatahCuti.getTipeCuti());

        jatahCutiRepository.save(existing);
        return true;
    }

    public boolean deleteJatahCuti(Integer id) {
        JatahCuti existing = jatahCutiRepository.findById(id).orElse(null);
        if (existing == null) {
            return false;
        }
        jatahCutiRepository.delete(existing);
        return true;
    }
}
