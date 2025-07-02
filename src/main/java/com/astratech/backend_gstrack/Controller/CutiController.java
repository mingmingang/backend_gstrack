package com.astratech.backend_gstrack.Controller;

import com.astratech.backend_gstrack.Service.CutiService;
import com.astratech.backend_gstrack.VO.Cuti;
import com.astratech.backend_gstrack.VO.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CutiController {

    @Autowired
    private CutiService cutiService;

    @GetMapping("/cuti")
    public List<Cuti> getAllCuti() {
        return cutiService.getAllCuti();
    }

    @GetMapping("/cuti/{id}")
    public Cuti getCutiById(@PathVariable("id") String cutiId) {
        return cutiService.getCutiById(cutiId);
    }

    @GetMapping("/cuti/user")
    public List<Cuti> getCutiByUser(
            @RequestParam("userId") String userId,
            @RequestParam(value = "jenis", required = false) String jenisCuti,
            @RequestParam(value = "status", required = false) String status
    ) {
        if (jenisCuti != null && !jenisCuti.isEmpty() &&
                status != null && !status.isEmpty()) {
            return cutiService.getCutiByUserAndJenisAndStatus(userId, jenisCuti, status);
        } else if (jenisCuti != null && !jenisCuti.isEmpty()) {
            return cutiService.getCutiByUserAndJenis(userId, jenisCuti);
        } else if (status != null && !status.isEmpty()) {
            return cutiService.getCutiByUserAndStatus(userId, status);
        } else {
            return cutiService.getCutiByUser(userId);
        }
    }


    @PostMapping("/cuti")
    public Object saveCuti(HttpServletResponse response, @RequestBody Cuti cuti) {
        boolean isSuccess = cutiService.saveCuti(cuti);
        if (isSuccess) {
            return new Result(200, "Cuti berhasil disimpan");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Gagal menyimpan cuti");
        }
    }

    @PutMapping("/cuti")
    public Object updateCuti(HttpServletResponse response, @RequestBody Cuti cuti) {
        boolean isSuccess = cutiService.updateCuti(cuti);
        if (isSuccess) {
            return new Result(200, "Cuti berhasil diperbarui");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Gagal memperbarui cuti");
        }
    }

    @DeleteMapping("/cuti/{id}")
    public Object deleteCuti(HttpServletResponse response, @PathVariable("id") String cutiId) {
        boolean isSuccess = cutiService.deleteCuti(cutiId);
        if (isSuccess) {
            return new Result(200, "Cuti berhasil dihapus");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Gagal menghapus cuti");
        }
    }
}
