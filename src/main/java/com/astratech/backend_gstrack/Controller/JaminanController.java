//JaminanController
package com.astratech.backend_gstrack.Controller;

import com.astratech.backend_gstrack.Repository.KaryawanRepository;
import com.astratech.backend_gstrack.Service.JaminanService;
import com.astratech.backend_gstrack.Service.KaryawanService;
import com.astratech.backend_gstrack.VO.DataBantuan.Orang;
import com.astratech.backend_gstrack.VO.DataBantuan.RumahSakit;
import com.astratech.backend_gstrack.VO.Jaminan;
import com.astratech.backend_gstrack.VO.Karyawan;
import com.astratech.backend_gstrack.VO.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class JaminanController {
    @Autowired
    private JaminanService jaminanService;

    @Autowired
    private KaryawanRepository karyawanRepository;

    @GetMapping("/jaminan")
    public List<Jaminan> getAllJaminan() {
        return jaminanService.getAllJaminan();
    }

    //full ada nama
    @GetMapping("/jaminan/full-info")
    public List<Map<String, Object>> getJaminanFullInfo(@RequestParam("npk") String npk) {
        return jaminanService.getJaminanWithOrangAndPlafonAndRsByNpk(npk);
    }

    @GetMapping("/karyawan/plafon-info")
    public Map<String, Object> getPlafonInfo(@RequestParam("npk") String npk) {
        return jaminanService.getPlafonInfoByNpk(npk);
    }


    @GetMapping("/jaminan/{id}")
    public Jaminan getJaminanByNoRequest(@PathVariable("id") String noRequest) {
        return jaminanService.getJaminanByNoRequest(noRequest);
    }

    //full tidak ada nama
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
        try {
            // Generate nomor request dari service (dengan format REQ-001, REQ-002, dst)
            String noRequest = jaminanService.generateNoRequest();
            jaminan.setPsjNoRequestRecord(noRequest);

            // Ambil data karyawan dari NPK
            Optional<Karyawan> karyawan = karyawanRepository.findById(jaminan.getKryNpk());
            if (karyawan.isPresent()) {
                Karyawan kry = karyawan.get();
                jaminan.setPsjPlant(kry.getPlant());
                jaminan.setPsjDepartemen(kry.getDepartemen());
            }

            boolean isSuccess = jaminanService.saveJaminan(jaminan);
            if (isSuccess) {
                return new Result(200, "Jaminan berhasil disimpan");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return new Result(500, "Gagal menyimpan Jaminan");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Terjadi kesalahan: " + e.getMessage());
        }
    }

    @GetMapping("/keluarga/nama-hubungan")
    public List<Map<String, Object>> getKeluargaNamaHubunganByNpk(@RequestParam("npk") String npk) {
        return jaminanService.getOrangNamaHubunganByKryNpk(npk);
    }


    @GetMapping("/rumah-sakit/rayon")
    public List<RumahSakit> getRumahSakitRayon() {
        return jaminanService.getRumahSakitRayon();
    }
}