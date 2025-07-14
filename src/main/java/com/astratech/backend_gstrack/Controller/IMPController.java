package com.astratech.backend_gstrack.Controller;

import com.astratech.backend_gstrack.Service.IMPService;
import com.astratech.backend_gstrack.VO.IMP;
import com.astratech.backend_gstrack.VO.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IMPController {
    @Autowired
    IMPService mIMPService;

    @GetMapping("/imps")
    public List<IMP> getIMPs() {
        List<IMP> impList = mIMPService.getIMPs();
        return impList;
    }

    @PostMapping("/imp")
    public Object saveIMP(HttpServletResponse response, @RequestBody IMP param) {
        IMP imp = new IMP();
        imp.setImpId(param.getImpId());
        imp.setImpNoRequest(param.getImpNoRequest());
        imp.setKaryawan(param.getKaryawan());
        imp.setImpKegiatan(param.getImpKegiatan());
        imp.setImpTanggalBerangkat(param.getImpTanggalBerangkat());
        imp.setImpWaktuBerangkat(param.getImpWaktuBerangkat());
        imp.setImpTanggalPulang(param.getImpTanggalPulang());
        imp.setImpWaktuPulang(param.getImpWaktuPulang());
        imp.setImpLokasi(param.getImpLokasi());
        imp.setImpKeterangan(param.getImpKeterangan());
        imp.setImpBerkasLampiran(param.getImpBerkasLampiran());
        imp.setImpStatus(param.getImpStatus());
        imp.setImpCreatedBy(param.getImpCreatedBy());
        imp.setImpCreatedDate(param.getImpCreatedDate());
        imp.setImpModifBy(param.getImpModifBy());
        imp.setImpModifDate(param.getImpModifDate());
        boolean isSuccess = mIMPService.saveIMP(imp);
        if (isSuccess) {
            return new Result(200, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Internal Server Error");
        }
    }

    @PutMapping("/imp")
    public Object modifyIMP(HttpServletResponse response, @RequestBody IMP userParam) {
        IMP imp = new IMP();
        imp.setImpId(userParam.getImpId());
        imp.setImpNoRequest(userParam.getImpNoRequest());
        imp.setKaryawan(userParam.getKaryawan());
        imp.setImpKegiatan(userParam.getImpKegiatan());
        imp.setImpTanggalBerangkat(userParam.getImpTanggalBerangkat());
        imp.setImpWaktuBerangkat(userParam.getImpWaktuBerangkat());
        imp.setImpTanggalPulang(userParam.getImpTanggalPulang());
        imp.setImpWaktuPulang(userParam.getImpWaktuPulang());
        imp.setImpLokasi(userParam.getImpLokasi());
        imp.setImpKeterangan(userParam.getImpKeterangan());
        imp.setImpBerkasLampiran(userParam.getImpBerkasLampiran());
        imp.setImpStatus(userParam.getImpStatus());
        imp.setImpCreatedBy(userParam.getImpCreatedBy());
        imp.setImpCreatedDate(userParam.getImpCreatedDate());
        imp.setImpModifBy(userParam.getImpModifBy());
        imp.setImpModifDate(userParam.getImpModifDate());
        boolean isSuccess = mIMPService.updateIMP(imp);
        if (isSuccess) {
            return new Result(200, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Internal Server Error");
        }
    }

    @RequestMapping(value = "/imp", method = RequestMethod.DELETE)
    public Object deleteIMP(HttpServletResponse response, @RequestParam("imp_id") int imp_id) {
        boolean isSuccess = mIMPService.deleteIMP(imp_id);
        if (isSuccess) {
            return new Result(200, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Internal Server Error");
        }
    }
}
