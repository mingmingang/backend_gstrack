//JaminanController
package com.astratech.backend_gstrack.Controller;

import com.astratech.backend_gstrack.Service.JaminanService;
import com.astratech.backend_gstrack.VO.Jaminan;
import com.astratech.backend_gstrack.VO.Orang;
import com.astratech.backend_gstrack.VO.Result;
import com.astratech.backend_gstrack.VO.RumahSakit;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JaminanController {
    @Autowired
    private JaminanService jaminanService;

    @GetMapping("/jaminan")
    public List<Jaminan> getAllJaminan() {
        return jaminanService.getAllJaminan();
    }

    @GetMapping("/jaminan/{id}")
    public Jaminan getJaminanByNoRequest(@PathVariable("id") String noRequest) {
        return jaminanService.getJaminanByNoRequest(noRequest);
    }

    @GetMapping("/jaminan/karyawan")
    public List<Jaminan> getJaminanByNpk(
            @RequestParam("npk") String npk,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "year", required = false) Integer year
    ) {
        if (status != null && !status.isEmpty() && year != null && year >= 2020) {
            return jaminanService.getJaminanByNpkAndStatusAndYear(npk, status, year);
        } else if (status != null && !status.isEmpty()) {
            return jaminanService.getJaminanByNpkAndStatus(npk, status);
        } else if (year != null && year >= 2020) {
            return jaminanService.getJaminanByNpkAndYear(npk, year);
        } else {
            return jaminanService.getJaminanByNpk(npk);
        }
    }

    @PostMapping("/jaminan")
    public Object saveJaminan(HttpServletResponse response, @RequestBody Jaminan jaminan) {
        boolean isSuccess = jaminanService.saveJaminan(jaminan);
        if (isSuccess) {
            return new Result(200, "Jaminan berhasil disimpan");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Gagal menyimpan Jaminan");
        }
    }

    // ORANG
    @GetMapping("/keluarga")
    public List<Orang> getKeluargaByNpk(@RequestParam("npk") String npk) {
        return jaminanService.getOrangByKryNpk(npk);
    }

    // RUMAH SAKIT
    @GetMapping("/rumah-sakit")
    public List<RumahSakit> getAllRumahSakit() {
        return jaminanService.getAllRumahSakit();
    }
}