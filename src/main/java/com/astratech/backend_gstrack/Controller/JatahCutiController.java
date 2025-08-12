package com.astratech.backend_gstrack.Controller;

import com.astratech.backend_gstrack.Service.JatahCutiService;
import com.astratech.backend_gstrack.VO.JatahCuti;
import com.astratech.backend_gstrack.VO.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JatahCutiController {

    @Autowired
    private JatahCutiService jatahCutiService;

    @GetMapping("/jatahcuti")
    public List<JatahCuti> getAllJatahCuti() {
        return jatahCutiService.getAll();
    }

    @GetMapping("/jatahcuti/{id}")
    public JatahCuti getJatahCutiById(@PathVariable("id") Integer id) {
        return jatahCutiService.getById(id);
    }

    @GetMapping("/jatahcuti/karyawan")
    public List<JatahCuti> getJatahCutiByParams(
            @RequestParam("npk") String npk,
            @RequestParam(value = "tahun", required = false) Integer tahun,
            @RequestParam(value = "tipe", required = false) String tipeCuti
    ) {
        if (tahun != null && tipeCuti != null && !tipeCuti.isEmpty()) {
            return jatahCutiService.getByNpkTahunAndTipeCuti(npk, tahun, tipeCuti);
        } else if (tahun != null) {
            return jatahCutiService.getByNpkAndTahun(npk, tahun);
        } else {
            return jatahCutiService.getByNpk(npk);
        }
    }

    @PostMapping("/jatahcuti")
    public Object saveJatahCuti(HttpServletResponse response, @RequestBody JatahCuti jatahCuti) {
        boolean isSuccess = jatahCutiService.saveJatahCuti(jatahCuti);
        if (isSuccess) {
            return new Result(200, "Jatah cuti berhasil disimpan");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Gagal menyimpan jatah cuti");
        }
    }

    @PutMapping("/jatahcuti")
    public Object updateJatahCuti(HttpServletResponse response, @RequestBody JatahCuti jatahCuti) {
        boolean isSuccess = jatahCutiService.updateJatahCuti(jatahCuti);
        if (isSuccess) {
            return new Result(200, "Jatah cuti berhasil diperbarui");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Gagal memperbarui jatah cuti");
        }
    }

    @DeleteMapping("/jatahcuti/{id}")
    public Object deleteJatahCuti(HttpServletResponse response, @PathVariable("id") Integer id) {
        boolean isSuccess = jatahCutiService.deleteJatahCuti(id);
        if (isSuccess) {
            return new Result(200, "Jatah cuti berhasil dihapus");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Gagal menghapus jatah cuti");
        }
    }
}
