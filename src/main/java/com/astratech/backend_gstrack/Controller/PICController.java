package com.astratech.backend_gstrack.Controller;

import com.astratech.backend_gstrack.Service.PICService;
import com.astratech.backend_gstrack.VO.PIC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/save")
    public ResponseEntity<String> savePIC(@RequestBody PIC request) {
        try {
            boolean success = picService.savePIC(request);
            return success
                    ? ResponseEntity.ok("PIC request saved successfully")
                    : ResponseEntity.badRequest().body("Failed to save PIC request");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Internal server error");
        }
    }
}
