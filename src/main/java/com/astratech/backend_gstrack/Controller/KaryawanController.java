package com.astratech.backend_gstrack.Controller;

import com.astratech.backend_gstrack.Service.KaryawanService;
import com.astratech.backend_gstrack.VO.Karyawan;
import com.astratech.backend_gstrack.VO.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; // Import ResponseEntity
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map; // Import Map

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/karyawan") // Menetapkan prefix /api/karyawan untuk semua endpoint di kelas ini
public class KaryawanController {

    @Autowired
    private KaryawanService karyawanService;

    // === ENDPOINT BARU YANG DIPERLUKAN OLEH FRONTEND "TAMBAH REIMBURSEMENT" ===
    /**
     * Endpoint untuk mengambil detail dasar karyawan (NPK, Nama) beserta daftar keluarganya.
     * Path ini akan diakses oleh halaman "Tambah Reimbursement" untuk mengisi dropdown pasien.
     * Path URL: GET /api/karyawan/{npk}/keluarga
     */
    @GetMapping("/{npk}/keluarga")
    public ResponseEntity<?> getKaryawanWithKeluarga(@PathVariable("npk") String npk) {
        try {
            // Memanggil method baru di KaryawanService yang perlu Anda buat
            Map<String, Object> userData = karyawanService.getUserDataWithFamily(npk);

            // Jika service tidak menemukan karyawan, kembalikan 404 Not Found
            if (userData == null || userData.isEmpty()) {
                return ResponseEntity.status(404).body(Map.of("message", "Karyawan atau data keluarga tidak ditemukan."));
            }

            // Jika berhasil, kembalikan data dengan status 200 OK
            return ResponseEntity.ok(userData);

        } catch (Exception e) {
            // Jika terjadi error tak terduga di service, kembalikan 500 Internal Server Error
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("message", "Internal Server Error: " + e.getMessage()));
        }
    }
    // === AKHIR ENDPOINT BARU ===


    // === ENDPOINT ANDA YANG SUDAH ADA, SEKARANG DENGAN PENYESUAIAN ===

    /**
     * Mengambil semua data karyawan.
     * Path URL: GET /api/karyawan
     */
    @GetMapping
    public List<Karyawan> getAllKaryawan() {
        return karyawanService.getAllKaryawan();
    }

    /**
     * Mengambil detail lengkap satu karyawan berdasarkan NPK.
     * Path URL: GET /api/karyawan/detail?npk={npk}
     */
    @GetMapping("/detail")
    public Karyawan getKaryawanByNpk(@RequestParam("npk") String npk) {
        return karyawanService.getKaryawanByNpk(npk);
    }

    /**
     * Menyimpan data karyawan baru.
     * Path URL: POST /api/karyawan
     */
    @PostMapping
    public Object saveKaryawan(HttpServletResponse response, @RequestBody Karyawan param) {
        boolean isSuccess = karyawanService.saveKaryawan(param);
        if (isSuccess) {
            return new Result(200, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Failed to save karyawan");
        }
    }

    /**
     * Mengupdate data karyawan yang sudah ada.
     * Path URL: PUT /api/karyawan
     */
    @PutMapping
    public Object updateKaryawan(HttpServletResponse response, @RequestBody Karyawan param) {
        boolean isSuccess = karyawanService.updateKaryawan(param);
        if (isSuccess) {
            return new Result(200, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Failed to update karyawan");
        }
    }

    /**
     * Menghapus data karyawan berdasarkan NPK.
     * Path URL: DELETE /api/karyawan?npk={npk}
     */
    @DeleteMapping
    public Object deleteKaryawan(HttpServletResponse response, @RequestParam("npk") String npk) {
        boolean isSuccess = karyawanService.deleteKaryawan(npk);
        if (isSuccess) {
            return new Result(200, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Failed to delete karyawan");
        }
    }

    /**
     * Endpoint untuk proses login.
     * Path URL: POST /api/karyawan/login
     */
    @PostMapping("/login")
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