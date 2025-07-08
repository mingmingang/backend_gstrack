package com.astratech.backend_gstrack.Controller;

import com.astratech.backend_gstrack.Service.KaryawanService;
import com.astratech.backend_gstrack.VO.Karyawan;
import com.astratech.backend_gstrack.VO.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KaryawanController {

    @Autowired
    private KaryawanService karyawanService;

    @GetMapping("/karyawan")
    public List<Karyawan> getAllKaryawan() {
        return karyawanService.getAllKaryawan();
    }

    @GetMapping("/karyawan/detail")
    public Karyawan getKaryawanByNpk(@RequestParam("npk") String npk) {
        return karyawanService.getKaryawanByNpk(npk);
    }

    @PostMapping("/karyawan")
    public Object saveKaryawan(HttpServletResponse response, @RequestBody Karyawan param) {
        boolean isSuccess = karyawanService.saveKaryawan(param);
        if (isSuccess) {
            return new Result(200, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Failed to save karyawan");
        }
    }

    @PutMapping("/karyawan")
    public Object updateKaryawan(HttpServletResponse response, @RequestBody Karyawan param) {
        boolean isSuccess = karyawanService.updateKaryawan(param);
        if (isSuccess) {
            return new Result(200, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Failed to update karyawan");
        }
    }

    @DeleteMapping("/karyawan")
    public Object deleteKaryawan(HttpServletResponse response, @RequestParam("npk") String npk) {
        boolean isSuccess = karyawanService.deleteKaryawan(npk);
        if (isSuccess) {
            return new Result(200, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Failed to delete karyawan");
        }
    }

    @PostMapping("/karyawan/login")
    public Object login(HttpServletResponse response, @RequestBody Karyawan loginRequest) {
        Karyawan karyawan = karyawanService.getKaryawanByNpk(loginRequest.getNpk());
        if (karyawan != null && karyawan.getPassword().equals(loginRequest.getPassword())) {
            return new Result(200, "Login Successful", karyawan);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return new Result(401, "Invalid NPK or password");
        }
    }
}
