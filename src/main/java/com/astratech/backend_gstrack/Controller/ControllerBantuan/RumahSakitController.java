package com.astratech.backend_gstrack.Controller.ControllerBantuan;

import com.astratech.backend_gstrack.Service.ServiceBantuan.RumahSakitService;
import com.astratech.backend_gstrack.VO.DataBantuan.RumahSakit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rumahsakit") // <- Path yang cocok dengan permintaan frontend
public class RumahSakitController {

    @Autowired
    private RumahSakitService rumahSakitService;

    /**
     * Endpoint untuk mengambil data rumah sakit, dengan filter berdasarkan tipe.
     * Path URL: GET /api/rumahsakit?tipe=Rayon atau GET /rumahsakit?tipe=Non%20Rayon
     */
    @GetMapping
    public ResponseEntity<?> getRumahSakitByTipe(@RequestParam("tipe") String tipe) {
        try {
            // Tipe akan berisi "Rayon" atau "Non Rayon"
            List<RumahSakit> data = rumahSakitService.getByTipe(tipe);

            // Mengembalikan list kosong jika tidak ada data itu normal, jadi tidak perlu cek `isEmpty`
            return ResponseEntity.ok(data);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("message", "Internal Server Error: " + e.getMessage()));
        }
    }
}