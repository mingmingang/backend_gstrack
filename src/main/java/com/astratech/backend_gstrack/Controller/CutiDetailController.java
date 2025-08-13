package com.astratech.backend_gstrack.Controller;

import com.astratech.backend_gstrack.Service.CutiDetailService;
import com.astratech.backend_gstrack.Service.CutiService;
import com.astratech.backend_gstrack.VO.CutiDetail;
import com.astratech.backend_gstrack.VO.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cuti-detail")
public class CutiDetailController {

    @Autowired
    private CutiDetailService cutiDetailService;
    @Autowired
    private CutiService cutiService;

    // GET ALL
    @GetMapping
    public List<CutiDetail> getAllCutiDetail() {
        return cutiDetailService.getAllCutiDetail();
    }

    // GET by cutiId
    @GetMapping("/by-cuti-id/{cutiId}")
    public List<CutiDetail> getByCutiId(@PathVariable("cutiId") String cutiId) {
        return cutiDetailService.getCutiDetailByCutiId(cutiId);
    }

    @GetMapping("/by-cuti-id-and-tanggal")
    public CutiDetail getByCutiIdAndTanggal(
            @RequestParam("cutiId") String cutiId,
            @RequestParam("tanggal") Date tanggalCuti
    ) {
        return cutiDetailService.getCutiDetailByCutiIdAndTanggal(cutiId, tanggalCuti);
    }

    // GET by status
    @GetMapping("/by-status")
    public List<CutiDetail> getByStatus(@RequestParam("status") String status) {
        return cutiDetailService.getCutiDetailByStatus(status);
    }

    // GET by tanggal
    @GetMapping("/by-tanggal")
    public List<CutiDetail> getByTanggal(@RequestParam("tanggal") Date tanggalCuti) {
        return cutiDetailService.getCutiDetailByTanggal(tanggalCuti);
    }

    // POST - save single detail
    @PostMapping
    public Object saveDetail(HttpServletResponse response, @RequestBody CutiDetail cutiDetail) {
        boolean isSuccess = cutiDetailService.saveCutiDetail(cutiDetail);
        if (isSuccess) {
            return new Result(200, "Cuti detail berhasil disimpan");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Gagal menyimpan cuti detail");
        }
    }

    // DELETE - by ID
    @DeleteMapping("/{id}")
    public Object deleteDetail(HttpServletResponse response, @PathVariable("id") Long id) {
        boolean isSuccess = cutiDetailService.deleteCutiDetail(id);
        if (isSuccess) {
            return new Result(200, "Cuti detail berhasil dihapus");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Gagal menghapus cuti detail");
        }
    }

    @PatchMapping("/update-status")
    public ResponseEntity<?> updateStatusTanggal(
            @RequestParam("cutiId") String cutiId,
            @RequestBody Map<String, Object> payload) {

        List<String> tanggalList = (List<String>) payload.get("tanggalList");
        String alasan = (String) payload.get("alasan");

        boolean updated = cutiDetailService.batalkanBeberapaTanggal(cutiId, tanggalList, alasan);

        if (!updated) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Result(500, "Gagal update status tanggal cuti"));
        }

        boolean semuaDibatalkan = cutiDetailService.cekSemuaTanggalDibatalkan(cutiId);
        if (semuaDibatalkan) {
            cutiService.updateStatusCuti(cutiId, "Dibatalkan");
        }

        return ResponseEntity.ok(new Result(200, "Tanggal cuti berhasil dibatalkan"));
    }


}
