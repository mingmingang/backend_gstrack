package com.astratech.backend_gstrack.Service;

import com.astratech.backend_gstrack.Repository.IMPRepository;
import com.astratech.backend_gstrack.VO.IMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class IMPService {
    @Qualifier("IMPRepository")
    @Autowired
    private IMPRepository mIMPRepository;

    public IMP getIMP(int impId) { return mIMPRepository.getIMPByImpId(impId); }

    public List<IMP> getIMPs() { return mIMPRepository.findAllByOrderByImpIdDesc(); }

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
        if (StringUtils.hasLength(String.valueOf(imp.getKaryawan()))) {
            existingIMP.setKaryawan(imp.getKaryawan());
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
        if (StringUtils.hasLength(String.valueOf(imp.getImpTanggalPulang()))) {
            existingIMP.setImpTanggalPulang(imp.getImpTanggalPulang());
        }
        if (StringUtils.hasLength(String.valueOf(imp.getImpWaktuPulang()))) {
            existingIMP.setImpWaktuPulang(imp.getImpWaktuPulang());
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

}
