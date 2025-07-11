package com.astratech.backend_gstrack.Controller;

import com.astratech.backend_gstrack.DTO.ApprovalReimbursementDto;
import com.astratech.backend_gstrack.DTO.PembatalanReimbursementDto;
import com.astratech.backend_gstrack.DTO.ReimbursementDto;
import com.astratech.backend_gstrack.Exception.NotFoundException;
import com.astratech.backend_gstrack.Repository.ReimbursementRepository;
import com.astratech.backend_gstrack.Service.ReimbursementService;
import com.astratech.backend_gstrack.VO.Reimbursement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
// PERUBAHAN 1: Path utama untuk semua endpoint reimbursement.
@RequestMapping("/reimbursement")
public class ReimbursementController {

    @Autowired
    private ReimbursementService reimbursementService;

    @Autowired
    private ReimbursementRepository reimbursementRepository;

    // ======== ENDPOINT UNTUK MEMBACA DATA (GET) - CONTOH ========

    /**
     * Endpoint untuk mendapatkan daftar reimbursement berdasarkan NPK dan tahun.
     * URL: GET /api/reimbursement/list?npk=230093&year=2024
     */
    @GetMapping("/list-karyawan")
    public ResponseEntity<?> getAllReimbursementsByNpkAndYear(
            @RequestParam("npk") String npk,
            @RequestParam("year") int year) {
        try {
            List<ReimbursementDto> data = reimbursementService.getReimbursements(npk, year);
            if (data.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No reimbursements found for the given criteria."));
            }
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Internal server error: " + e.getMessage()));
        }
    }

    @GetMapping("/list-atasan")
    public ResponseEntity<?> getAllReimbursementsByYear(
            @RequestParam("year") int year) {
        try {
            List<ReimbursementDto> data = reimbursementService.getReimbursements(year);
            if (data.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No reimbursements found for the given criteria."));
            }
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Internal server error: " + e.getMessage()));
        }
    }

    @GetMapping("/earliest-year")
    public ResponseEntity<Map<String, Integer>> getEarliestYear() {
        int year = reimbursementService.getEarliestYear();
        Map<String, Integer> response = new HashMap<>();
        response.put("earliestYear", year);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint untuk mendapatkan detail satu reimbursement berdasarkan ID.
     * URL: GET /api/reimbursement/240601
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getReimbursementById(@PathVariable("id") BigInteger id) {
        try {
            ReimbursementDto data = reimbursementService.getReimbursementById(id);
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            e.printStackTrace();
            // NotFoundException akan ditangani oleh ExceptionHandler global, atau bisa secara lokal.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        }
    }

    // ======== ENDPOINT UNTUK MENYIMPAN (CREATE/ADD) PENGAJUAN BARU ========

    /**
     * Endpoint untuk MENYIMPAN pengajuan reimbursement baru.
     * Menerima data dalam format `multipart/form-data`.
     * URL: POST /api/reimbursement/add
     */

    /**
     * PERUBAHAN 2: Endpoint utama untuk menyimpan pengajuan baru.
     * Path-nya adalah "/api/reimbursement" dengan metode POST.
     * Tidak perlu "/add" lagi.
     */
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Map<String, Object>> addReimbursement(
            // --- Pastikan nama-nama ini SAMA PERSIS dengan key di FormData React Native ---

            // --- Data Teks ---
            @RequestParam("kry_npk") String kryNpk,
            @RequestParam("klg_id") Integer klgId,
            @RequestParam("rbm_tipe") String rbmTipe,
            @RequestParam("rbm_cost") BigDecimal rbmCost,
            @RequestParam("rbm_dokter") String rbmDokter,
            @RequestParam("rbm_created_by") String rbmCreatedBy,
            @RequestParam("dgs_id") String dgsId,
            @RequestParam("rs_id") Integer rsId,
            @RequestParam("rbm_tanggal_mulai") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date rbmTanggalMulai,

            // --- Field Opsional ---
            @RequestParam(value = "rbm_tanggal_selesai", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date rbmTanggalSelesai,
            @RequestParam(value = "rbm_diagnosa_other", required = false) String rbmDiagnosaOther,

            // --- File-file (Opsional) ---
            @RequestParam(value = "rbm_file_path_kwitansi", required = false) MultipartFile fileKwitansi,
            @RequestParam(value = "rbm_file_path_rincian_obat", required = false) MultipartFile fileRincianObat,
            @RequestParam(value = "rbm_file_path_hasil_lab", required = false) MultipartFile fileHasilLab,
            @RequestParam(value = "rbm_file_path_resume_medis", required = false) MultipartFile fileResumeMedis
    ) {
        try {
            Reimbursement savedReimbursement = reimbursementService.saveReimbursement(
                    kryNpk, klgId, rbmTipe, rbmCost, rbmDokter, rbmCreatedBy,
                    dgsId, rsId, rbmTanggalMulai, rbmTanggalSelesai, rbmDiagnosaOther,
                    fileKwitansi, fileRincianObat, fileHasilLab, fileResumeMedis
            );

            // Respon sukses
            Map<String, Object> response = Map.of(
                    "message", "Reimbursement submitted successfully.",
                    "id", savedReimbursement.getRbmId().toString() // kirim id sebagai string
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(response); // Gunakan 201 CREATED untuk resource baru

        } catch (Exception e) {
            e.printStackTrace(); // Penting untuk debugging di log server
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Failed to submit reimbursement: " + e.getMessage()));
        }
    }

    /**
     * Endpoint untuk mengambil file reimbursement berdasarkan ID dan tipe filenya.
     * URL: GET /reimbursement/file/12345/kwitansi
     * @param id ID dari reimbursement
     * @param fileKey Kunci file yang diminta (cth: kwitansi, rincian_obat, hasil_lab, resume_medis)
     * @return Data file dalam bentuk byte array dengan Content-Type yang sesuai.
     */
    @GetMapping("/file/{id}/{fileKey}")
    public ResponseEntity<byte[]> getReimbursementFile(
            @PathVariable("id") BigInteger id,
            @PathVariable("fileKey") String fileKey
    ) {
        try {
            Reimbursement reimbursement = reimbursementRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Reimbursement not found with id: " + id));

            byte[] fileData;

            // Switch tetap sama
            switch (fileKey.toLowerCase()) {
                case "kwitansi":
                    fileData = reimbursement.getRbmFilePathKwitansi();
                    break;
                case "rincian_obat":
                    fileData = reimbursement.getRbmFilePathRincianObat();
                    break;
                case "hasil_lab":
                    fileData = reimbursement.getRbmFilePathHasilLab();
                    break;
                case "resume_medis":
                    fileData = reimbursement.getRbmFilePathResumeMedis();
                    break;
                default:
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            if (fileData == null || fileData.length == 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            // --- PERBAIKAN UTAMA ADA DI SINI ---
            HttpHeaders headers = new HttpHeaders();
            String filename;

            // Cek "Magic Numbers" untuk menentukan tipe file
            // %PDF-
            if (fileData.length > 4 && fileData[0] == 0x25 && fileData[1] == 0x50 && fileData[2] == 0x44 && fileData[3] == 0x46) {
                headers.setContentType(MediaType.APPLICATION_PDF);
                filename = fileKey + ".pdf";
                // JFIF (JPEG) atau Exif
            } else if (fileData.length > 10 && (
                    (fileData[6] == 'J' && fileData[7] == 'F' && fileData[8] == 'I' && fileData[9] == 'F') ||
                            (fileData[6] == 'E' && fileData[7] == 'x' && fileData[8] == 'i' && fileData[9] == 'f')
            )) {
                headers.setContentType(MediaType.IMAGE_JPEG);
                filename = fileKey + ".jpg";
                // PNG
            } else if (fileData.length > 8 && fileData[1] == 'P' && fileData[2] == 'N' && fileData[3] == 'G') {
                headers.setContentType(MediaType.IMAGE_PNG);
                filename = fileKey + ".png";
            } else {
                // Fallback jika tidak terdeteksi, atau asumsikan JPEG
                headers.setContentType(MediaType.IMAGE_JPEG);
                filename = fileKey + ".jpg";
            }

            // PERBAIKAN: Gunakan ContentDisposition.builder untuk mengatur header yang benar.
            // "inline" mencoba menampilkan file di browser.
            // "attachment" akan langsung memicu unduhan. "inline" lebih cocok untuk kasus Anda.
            headers.setContentDisposition(ContentDisposition.builder("inline").filename(filename).build());

            return new ResponseEntity<>(fileData, headers, HttpStatus.OK);

        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            // Logging error sangat penting untuk debugging
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<ReimbursementDto> cancelReimbursement(
            @PathVariable("id") BigInteger id,
            @RequestBody ReimbursementDto requestDto) {

        try {
            ReimbursementDto updatedReimbursement = reimbursementService.cancelReimbursement(id, requestDto);
            return new ResponseEntity<>(updatedReimbursement, HttpStatus.OK);
        } catch (Exception e) {
            // Menangani berbagai jenis exception, misalnya NotFoundException
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ======== ENDPOINT BARU UNTUK UPDATE STATUS PERSETUJUAN ========
    /**
     * Endpoint untuk menyetujui atau menolak pengajuan reimbursement.
     * Menerima request POST dengan body JSON.
     * Contoh Body: { "rbmId": 24052101, "newStatus": "Setujui", "modifiedByNpk": "NPK_Approver" }
     * URL: POST /reimbursement/update/status
     */
    @PutMapping("/update/status")
    public ResponseEntity<?> updateReimbursementStatus(@RequestBody ApprovalReimbursementDto request) {
        try {
            // Validasi input sederhana
            if (request.getRbmId() == null || request.getNewStatus() == null || request.getModifiedByNpk() == null) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("message", "Request body is missing required fields (rbmId, newStatus, modifiedByNpk)."));
            }

            // Panggil service yang sudah dibuat
            ReimbursementDto updatedDto = reimbursementService.updateReimbursementStatus(
                    request.getRbmId(),
                    request.getNewStatus(),
                    request.getModifiedByNpk()
            );
            return ResponseEntity.ok(updatedDto);

        } catch (NotFoundException e) {
            // Jika reimbursement tidak ditemukan
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        } catch (java.lang.IllegalArgumentException e) {
            // Jika nilai status tidak valid (contoh: bukan 'Setujui' atau 'Ditolak')
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace(); // Penting untuk debugging
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "An unexpected error occurred: " + e.getMessage()));
        }
    }
}