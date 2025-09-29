package com.astratech.backend_gstrack.Controller;

import com.astratech.backend_gstrack.Service.IMPService;
import com.astratech.backend_gstrack.VO.IMP;
import com.astratech.backend_gstrack.VO.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
public class IMPController {
    @Autowired
    IMPService mIMPService;

    @GetMapping("/imps")
    public List<IMP> getIMPs() {
        List<IMP> impList = mIMPService.getIMPs();
        return impList;
    }

//    @GetMapping("/imps")
//    public List<IMP> getIMPs(@RequestParam("npkAtasan") String npkAtasan) {
//        return mIMPService.findForAtasan(npkAtasan);
//    }


    @GetMapping("/IMP/{id}")
    public IMP getIMPbyImpNoRequest(@PathVariable("id") String impNoRequest) { return mIMPService.getIMPbyImpNoRequest(impNoRequest); }

    @PostMapping("/saveIMP")
    public Object saveIMP(HttpServletResponse response, @RequestBody IMP param) {
        IMP imp = new IMP();
        imp.setImpNoRequest(param.getImpNoRequest());
        imp.setImpNpk(param.getImpNpk());
        imp.setImpJenisKegiatan(param.getImpJenisKegiatan());
        imp.setImpWaktuIzin(param.getImpWaktuIzin());
        imp.setImpTanggalBerangkat(param.getImpTanggalBerangkat());
        imp.setImpWaktuBerangkat(param.getImpWaktuBerangkat());
        imp.setImpTanggalKembali(param.getImpTanggalKembali());
        imp.setImpWaktuKembali(param.getImpWaktuKembali());
        imp.setImpLokasi(param.getImpLokasi());
        imp.setImpKeterangan(param.getImpKeterangan());
        imp.setImpBerkasLampiran(param.getImpBerkasLampiran());
        imp.setImpStatus(param.getImpStatus());
        imp.setImpCreatedBy(param.getImpCreatedBy());
        imp.setImpCreatedDate(param.getImpCreatedDate());
        boolean isSuccess = mIMPService.saveIMP(imp);
        if (isSuccess) {
            return new Result(200, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Internal Server Error");
        }
    }

    @PutMapping("/updateIMP")
    public Object modifyIMP(HttpServletResponse response, @RequestBody IMP userParam) {
        IMP imp = new IMP();
        imp.setImpId(userParam.getImpId());
        imp.setImpNoRequest(userParam.getImpNoRequest());
        imp.setImpNpk(userParam.getImpNpk());
        imp.setImpJenisKegiatan(userParam.getImpJenisKegiatan());
        imp.setImpWaktuIzin(userParam.getImpWaktuIzin());
        imp.setImpTanggalBerangkat(userParam.getImpTanggalBerangkat());
        imp.setImpWaktuBerangkat(userParam.getImpWaktuBerangkat());
        imp.setImpTanggalKembali(userParam.getImpTanggalKembali());
        imp.setImpWaktuKembali(userParam.getImpWaktuKembali());
        imp.setImpLokasi(userParam.getImpLokasi());
        imp.setImpKeterangan(userParam.getImpKeterangan());
        imp.setImpBerkasLampiran(userParam.getImpBerkasLampiran());
        imp.setImpStatus(userParam.getImpStatus());
        imp.setImpCreatedBy(userParam.getImpCreatedBy());
        imp.setImpCreatedDate(userParam.getImpCreatedDate());
        imp.setImpModifBy(userParam.getImpModifBy());
        imp.setImpModifDate(userParam.getImpModifDate());
        imp.setImpBerangkatAktual(userParam.getImpBerangkatAktual());
        imp.setImpKembaliAktual(userParam.getImpKembaliAktual());
        imp.setImpAlasanPenolakan(userParam.getImpAlasanPenolakan());
        boolean isSuccess = mIMPService.updateIMP(imp);
        if (isSuccess) {
            return new Result(200, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Internal Server Error");
        }
    }

    @PutMapping("/updateIMPWaktuAktualSimple")
    public Object updateIMPWaktuAktualSimple(HttpServletResponse response, @RequestBody Map<String, Object> param) {
        try {
            String impNoRequest = (String) param.get("impNoRequest");
            String impBerangkatAktual = (String) param.get("impBerangkatAktual");
            String impKembaliAktual = (String) param.get("impKembaliAktual");

            // Cari IMP berdasarkan impNoRequest
            IMP existingIMP = mIMPService.getIMPbyImpNoRequest(impNoRequest);
            if (existingIMP == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return new Result(404, "IMP not found");
            }

            // Update hanya field yang diperlukan
            if (impBerangkatAktual != null) {
                existingIMP.setImpBerangkatAktual(LocalDateTime.parse(impBerangkatAktual));
            }
            if (impKembaliAktual != null) {
                existingIMP.setImpKembaliAktual(LocalDateTime.parse(impKembaliAktual));
            }

            mIMPService.saveIMP(existingIMP);
            return new Result(200, "Success");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Error: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/deleteIMP", method = RequestMethod.DELETE)
    public Object deleteIMP(HttpServletResponse response, @RequestParam("imp_id") int imp_id) {
        boolean isSuccess = mIMPService.deleteIMP(imp_id);
        if (isSuccess) {
            return new Result(200, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Internal Server Error");
        }
    }

    //ATASAN
    @GetMapping("/IMP/available-years-all")
    public ResponseEntity<List<Integer>> getAllAvailableYears() {
        List<Integer> years = mIMPService.getAllAvailableYears(); // hasilnya List<Integer>
        return ResponseEntity.ok(years);
    }

    @GetMapping("/IMP/year")
    public List<IMP> getIMPByYear(@RequestParam(value = "year", required = false) Integer year) {
        if (year == null) {
            // Misalnya default ke tahun sekarang
            year = java.time.Year.now().getValue();
        }
        return mIMPService.getIMPByYear(year);
    }

    //KARYAWAN
    @GetMapping("/IMP/available-years")
    public ResponseEntity<List<Integer>> getAvailableYears(@RequestParam String impNpk) {
        List<Integer> years = mIMPService.getAvailableYearsByNpk(impNpk); // hasilnya harus List<Integer>
        return ResponseEntity.ok(years);
    }

    @GetMapping("/IMP/karyawan")
    public List<IMP> getIMPbyImpNpk(
            @RequestParam("impNpk") String impNpk,
            @RequestParam(value = "status", required = false) String impStatus,
            @RequestParam(value = "year", required = false) Integer year
    ) {
        boolean hasStatus = impStatus != null && !impStatus.isEmpty();
        boolean hasYear = year != null;

        if (hasStatus && hasYear) {
            return mIMPService.getIMPbyImpNpkAndImpStatusAndImpCreatedDate(impNpk, impStatus, year);
        } else if (hasStatus) {
            return mIMPService.getIMPbyImpNpkAndImpStatus(impNpk, impStatus);
        } else if (hasYear) {
            return mIMPService.getIMPbyImpNpkAndImpCreatedDate(impNpk, year);
        } else {
            return mIMPService.getIMPbyImpNpk(impNpk);
        }
    }
}
