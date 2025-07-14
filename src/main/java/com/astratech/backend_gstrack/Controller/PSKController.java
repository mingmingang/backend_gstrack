package com.astratech.backend_gstrack.Controller;

import com.astratech.backend_gstrack.Service.PSKService;
import com.astratech.backend_gstrack.VO.PSK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/psk")
public class PSKController {

    @Autowired
    private PSKService pskService;

    @PostMapping("/get")
    public ResponseEntity<List<Map<String, Object>>> getAllPSK() {
        try {
            List<Map<String, Object>> pskList = pskService.getAllSorted();
            return ResponseEntity.ok(pskList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> savePSK(@RequestBody PSK request) {
        try {
            boolean success = pskService.savePSK(request);
            return success
                    ? ResponseEntity.ok("PSK request saved successfully")
                    : ResponseEntity.badRequest().body("Failed to save PSK request");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Internal server error");
        }
    }
}
