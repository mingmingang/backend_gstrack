package com.astratech.backend_gstrack.Controller;

import com.astratech.backend_gstrack.Service.PeSeKetService;
import com.astratech.backend_gstrack.VO.PeSeKet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/certificate-request")
public class PeSeKetController {

    @Autowired
    private PeSeKetService peSeKetService;

    @PostMapping("/get")
    public ResponseEntity<List<Map<String, Object>>> getAllPSK() {
        try {
            List<Map<String, Object>> pskList = peSeKetService.getAllSorted();
            return ResponseEntity.ok(pskList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/getbystatus")
    public ResponseEntity<List<Map<String, Object>>> getPSKByStatus(@RequestBody Map<String, String> request) {
        try {
            String status = request.get("status");
            List<Map<String, Object>> filteredPSK = peSeKetService.getAllByStatus(status);
            return ResponseEntity.ok(filteredPSK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody PeSeKet request) {
        boolean result = peSeKetService.savePSK(request);
        Map<String, Object> response = new HashMap<>();
        if (result) {
            response.put("message", "Berhasil");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Gagal simpan");
            return ResponseEntity.status(500).body(response);
        }
    }
    @PostMapping("/getbyalasan")
    public ResponseEntity<List<Map<String, Object>>> getPSKByAlasan(@RequestBody Map<String, String> request) {
        try {
            String alasan = request.get("alasan"); // contoh nilai: "Pengurusan KPR"
            List<Map<String, Object>> filteredPSK = peSeKetService.getAllByAlasan(alasan);
            return ResponseEntity.ok(filteredPSK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @PostMapping("/getbyalasanstatus")
    public ResponseEntity<?> getByAlasanStatus(@RequestBody Map<String, String> body){
        String alasan = body.get("alasan");
        String status = body.get("status");
        return ResponseEntity.ok(peSeKetService.getAllByAlasanAndStatus(alasan, status));
    }
    @PostMapping("/getbycreateddate")
    public ResponseEntity<List<Map<String, Object>>> getPSKByCreatedDate(
            @RequestBody Map<String, String> body) {
        try {
            LocalDate date = LocalDate.parse(body.get("createdDate"));
            LocalDateTime startDT = date.atStartOfDay();
            LocalDateTime endDT   = date.atTime(23, 59, 59, 999_999_999);

            List<Map<String, Object>> data = peSeKetService.getAllByCreatedDate(startDT, endDT);
            return ResponseEntity.ok(data);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/filter")
    public ResponseEntity<List<Map<String, Object>>> filterPSK(@RequestBody Map<String, String> body) {
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
                    peSeKetService.getAllByCreatedDateAndStatusAndAlasan(status, alasan, start, end)
            );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }
    @PostMapping("/getbyid")
    public ResponseEntity<?> getPSKById(@RequestBody Map<String, String> request) {
        try {
            String id = request.get("id");
            List<Map<String,Object>> data = peSeKetService.getById(id);
            if (!data.isEmpty()) {
                return ResponseEntity.ok(data);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
