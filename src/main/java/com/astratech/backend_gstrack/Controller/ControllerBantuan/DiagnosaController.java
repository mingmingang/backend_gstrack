package com.astratech.backend_gstrack.Controller.ControllerBantuan;

import com.astratech.backend_gstrack.Service.ServiceBantuan.DiagnosaService;
import com.astratech.backend_gstrack.VO.DataBantuan.Diagnosa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/diagnosa") // <- Path yang cocok dengan permintaan frontend
public class DiagnosaController {

    @Autowired
    private DiagnosaService diagnosaService;

    /**
     * Endpoint untuk mengambil semua data diagnosa.
     * Path URL: GET /api/diagnosa
     */
    @GetMapping
    public ResponseEntity<?> getAllDiagnosa() {
        try {
            List<Diagnosa> data = diagnosaService.getAll();
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("message", "Internal Server Error: " + e.getMessage()));
        }
    }
}