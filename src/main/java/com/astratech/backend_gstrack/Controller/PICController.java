package com.astratech.backend_gstrack.Controller;

import com.astratech.backend_gstrack.Service.PICService;
import com.astratech.backend_gstrack.VO.PIC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pic")
public class PICController {

    @Autowired
    private PICService picService;

    @PostMapping("/get")
    public ResponseEntity<List<Map<String, Object>>> getAllPIC() {
        try {
            List<Map<String, Object>> picList = picService.getAllSorted();
            return ResponseEntity.ok(picList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/getbystatus")
    public ResponseEntity<List<Map<String, Object>>> getPICByStatus(@RequestBody Map<String, String> request) {
        try {
            String status = request.get("status");
            List<Map<String, Object>> filteredPIC = picService.getAllByStatus(status);
            return ResponseEntity.ok(filteredPIC);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody PIC request) {
        boolean result = picService.savePIC(request);
        if (result) {
            return ResponseEntity.ok(Map.of("message", "Data berhasil disimpan"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Gagal menyimpan data"));
        }
    }
    @PostMapping("/getbyalasan")
    public ResponseEntity<List<Map<String, Object>>> getPICByAlasan(@RequestBody Map<String, String> request) {
        try {
            String alasan = request.get("alasan"); // contoh nilai: "Hilang", "Mutasi"
            List<Map<String, Object>> filteredPIC = picService.getAllByAlasan(alasan);
            return ResponseEntity.ok(filteredPIC);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @PostMapping("/getbyalasanstatus")
    public ResponseEntity<?> getByAlasanStatus(@RequestBody Map<String, String> body){
        String alasan = body.get("alasan");
        String status = body.get("status");
        return ResponseEntity.ok(picService.getAllByAlasanAndStatus(alasan, status));
    }
    @PostMapping("/getbycreateddate")
    public ResponseEntity<List<Map<String, Object>>> getPICByCreatedDate(
            @RequestBody Map<String, String> body) {
        try {
            LocalDate date = LocalDate.parse(body.get("createdDate")); // hanya 1 field
            LocalDateTime startDT = date.atStartOfDay();
            LocalDateTime endDT   = date.atTime(23, 59, 59, 999_999_999);

            List<Map<String, Object>> data = picService.getAllByCreatedDate(startDT, endDT);
            return ResponseEntity.ok(data);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/filter")
    public ResponseEntity<List<Map<String, Object>>> filterPIC(@RequestBody Map<String, String> body) {
        try {
            String status = body.get("status");
            String alasan = body.get("alasan");
            String createdDate = body.get("createdDate");

            if (status == null || alasan == null || createdDate == null)
                return ResponseEntity.badRequest().body(null);

            LocalDate date = LocalDate.parse(createdDate);
            LocalDateTime start = date.atStartOfDay();
            LocalDateTime end = date.atTime(23, 59, 59, 999_999_999);

            return ResponseEntity.ok(
                    picService.getAllByCreatedDateAndStatusAndAlasan(status, alasan, start, end)
            );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

}
