package com.astratech.backend_gstrack.Service;

import com.astratech.backend_gstrack.Repository.IMPRepository;
import com.astratech.backend_gstrack.VO.IMP;
import com.astratech.backend_gstrack.VO.Karyawan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class IMPService {
    @Qualifier("IMPRepository")
    @Autowired
    private IMPRepository mIMPRepository;

    public IMP getIMP(int impId) { return mIMPRepository.getIMPByImpId(impId); }

    public IMP getIMPbyImpNoRequest(String impNoRequest) { return mIMPRepository.findByImpNoRequest(impNoRequest); }

    public List<IMP> getIMPs() { return mIMPRepository.findAllByOrderByImpIdDesc(); }

    public List<IMP> getIMPbyImpNpk(String impNpk) { return mIMPRepository.findByImpNpkOrderByImpCreatedDateDesc(impNpk); }

    public List<IMP> getIMPbyImpNpkAndImpStatusAndImpCreatedDate(String impNpk, String impStatus, Integer year) { return mIMPRepository.findByNpkAndStatusAndYear(impNpk, impStatus, year); }

    public List<IMP> getIMPbyImpNpkAndImpStatus(String impNpk, String impStatus) { return mIMPRepository.findByNpkAndStatus(impNpk, impStatus); }

//    @Transactional(readOnly = true)
//    public List<IMP> findForAtasan(String npkAtasan) {
//        return mIMPRepository.findForAtasan(npkAtasan);
//    }

    public boolean saveIMP(IMP imp) {
        IMP result = mIMPRepository.save(imp);
        return result != null;
    }

    public boolean updateIMP(IMP imp) {
        IMP existingIMP = mIMPRepository.getIMPByImpId(imp.getImpId());
        if(existingIMP == null) {
            return false;
        }

        if (StringUtils.hasLength(imp.getImpNoRequest())) {
            existingIMP.setImpNoRequest(imp.getImpNoRequest());
        }
        if (StringUtils.hasLength(imp.getImpNpk())) {
            existingIMP.setImpNpk(imp.getImpNpk());
        }
        if (StringUtils.hasLength(imp.getImpKegiatan())) {
            existingIMP.setImpKegiatan(imp.getImpKegiatan());
        }
        if (StringUtils.hasLength(String.valueOf(imp.getImpTanggalBerangkat()))) {
            existingIMP.setImpTanggalBerangkat(imp.getImpTanggalBerangkat());
        }
        if (StringUtils.hasLength(String.valueOf(imp.getImpWaktuBerangkat()))) {
            existingIMP.setImpWaktuBerangkat(imp.getImpWaktuBerangkat());
        }
        if (StringUtils.hasLength(String.valueOf(imp.getImpTanggalKembali()))) {
            existingIMP.setImpTanggalKembali(imp.getImpTanggalKembali());
        }
        if (StringUtils.hasLength(String.valueOf(imp.getImpWaktuKembali()))) {
            existingIMP.setImpWaktuKembali(imp.getImpWaktuKembali());
        }
        if (StringUtils.hasLength(imp.getImpLokasi())) {
            existingIMP.setImpLokasi(imp.getImpLokasi());
        }
        if (StringUtils.hasLength(imp.getImpKeterangan())) {
            existingIMP.setImpKeterangan(imp.getImpKeterangan());
        }
        if (StringUtils.hasLength(imp.getImpBerkasLampiran())) {
            existingIMP.setImpBerkasLampiran(imp.getImpBerkasLampiran());
        }
        if (StringUtils.hasLength(imp.getImpStatus())) {
            existingIMP.setImpStatus(imp.getImpStatus());
        }
        if (StringUtils.hasLength(imp.getImpCreatedBy())) {
            existingIMP.setImpCreatedBy(imp.getImpCreatedBy());
        }
        if (StringUtils.hasLength(String.valueOf(imp.getImpCreatedDate()))) {
            existingIMP.setImpCreatedDate(imp.getImpCreatedDate());
        }
        if (StringUtils.hasLength(imp.getImpModifBy())) {
            existingIMP.setImpModifBy(imp.getImpModifBy());
        }
        if (StringUtils.hasLength(String.valueOf(imp.getImpModifDate()))) {
            existingIMP.setImpModifDate(imp.getImpModifDate());
        }

        mIMPRepository.save(existingIMP);
        return true;
    }

    public boolean deleteIMP(int impId) {
        IMP existingIMP = mIMPRepository.getIMPByImpId(impId);
        if(existingIMP == null) {
            return false;
        }
        mIMPRepository.delete(existingIMP);
        return true;
    }

    //ATASAN
    public List<IMP> getIMPByYear(Integer year) {
        return mIMPRepository.findAllByYear(year);
    }

    public List<Integer> getAllAvailableYears() {
        return mIMPRepository.findDistinctYears();
    }

    //KARYAWAN
    public List<IMP> getIMPbyImpNpkAndImpCreatedDate(String impNpk, Integer year) { return mIMPRepository.findByNpkAndYear(impNpk, year); }

    public List<Integer> getAvailableYearsByNpk(String npk) {
        return mIMPRepository.findAvailableYearsByNpk(npk);
    }

}
