package com.astratech.backend_gstrack.Controller;

import com.astratech.backend_gstrack.Service.LemburService;
import com.astratech.backend_gstrack.VO.Cuti;
import com.astratech.backend_gstrack.VO.Lembur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LemburController {

    @Autowired
    private LemburService mlemburService;

    @GetMapping("/lembur")
    public List<Lembur> getAllLembur(@RequestParam(required = false) String status) {
        if (status == null || status.isEmpty() || status.equalsIgnoreCase("Semua")) {
            return mlemburService.getAllLembur();
        } else {
            return mlemburService.getLemburByStatus(status);
        }
    }


    @GetMapping("/lembur/{id}")
    public ResponseEntity<Lembur> getLemburById(@PathVariable String id) {
        Lembur lembur = mlemburService.getLemburById(id);
        if (lembur != null) {
            return ResponseEntity.ok(lembur);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/lembur")
    public ResponseEntity<Lembur> addLembur(@RequestBody Lembur lembur) {
        Lembur savedLembur = mlemburService.addLembur(lembur);
        return ResponseEntity.ok(savedLembur);
    }

}
