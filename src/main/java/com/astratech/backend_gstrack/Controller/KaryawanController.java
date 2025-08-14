package com.astratech.backend_gstrack.Controller;

import com.astratech.backend_gstrack.Service.JaminanService;
import com.astratech.backend_gstrack.Service.KaryawanService;
import com.astratech.backend_gstrack.VO.Karyawan;
import com.astratech.backend_gstrack.VO.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class KaryawanController {

    @Autowired
    private KaryawanService karyawanService;

    @Autowired
    private JaminanService jaminanService;

    @GetMapping("/karyawan")
    public List<Karyawan> getAllKaryawan() {
        return karyawanService.getAllKaryawan();
    }

    @GetMapping("/karyawan/detail")
    public Karyawan getKaryawanByNpk(@RequestParam("npk") String npk) {
        return karyawanService.getKaryawanByNpk(npk);
    }

    @PostMapping("/karyawan")
    public Object saveKaryawan(HttpServletResponse response, @RequestBody Karyawan param) {
        boolean isSuccess = karyawanService.saveKaryawan(param);
        if (isSuccess) {
            return new Result(200, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Failed to save karyawan");
        }
    }

    @PutMapping("/karyawan")
    public Object updateKaryawan(HttpServletResponse response, @RequestBody Karyawan param) {
        boolean isSuccess = karyawanService.updateKaryawan(param);
        if (isSuccess) {
            return new Result(200, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Failed to update karyawan");
        }
    }

    @DeleteMapping("/karyawan")
    public Object deleteKaryawan(HttpServletResponse response, @RequestParam("npk") String npk) {
        boolean isSuccess = karyawanService.deleteKaryawan(npk);
        if (isSuccess) {
            return new Result(200, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Failed to delete karyawan");
        }
    }

    @PostMapping("/karyawan/login")
    public Object login(HttpServletResponse response, @RequestBody Karyawan loginRequest) {
        Karyawan karyawan = karyawanService.getKaryawanByNpk(loginRequest.getNpk());
        if (karyawan != null && karyawan.getPassword().equals(loginRequest.getPassword())) {
            return new Result(200, "Login Successful", karyawan);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return new Result(401, "Invalid NPK or password");
        }
    }

    @PutMapping("/karyawan/password")
    public ResponseEntity<Result> changePassword(@RequestBody Karyawan param) {
        Karyawan existing = karyawanService.getKaryawanByNpk(param.getNpk());
        System.out.println(existing.toString());
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Result(404, "Karyawan tidak ditemukan"));
        }

        if (!existing.getPassword().equals(param.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Result(401, "Password lama tidak cocok"));
        }

        boolean isUpdated = karyawanService.updatePassword(param.getNpk(), param.getNewPassword());

        if (isUpdated) {
            return ResponseEntity.ok(new Result(200, "Password berhasil diubah"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Result(500, "Gagal mengubah password"));
        }
    }


    @PostMapping("/karyawan/upload-lampiran")
    public ResponseEntity<String> uploadLampiran(@RequestParam("file") MultipartFile file) {
        try {
            String rootPath = System.getProperty("user.dir");
            String folderPath = rootPath + File.separator + "uploads" + File.separator + "karyawan";

            File directory = new File(folderPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            String originalName = file.getOriginalFilename();
            if (originalName == null || originalName.isBlank()) {
                originalName = "file_" + System.currentTimeMillis() + ".jpg";
            }
            String cleanedName = originalName != null ? originalName.replaceAll("\\s+", "_") : "file";
            String timestamp = String.valueOf(System.currentTimeMillis());
            String finalFileName = timestamp + "_" + cleanedName;

            File dest = new File(directory, finalFileName);
            file.transferTo(dest);

            return ResponseEntity.ok(finalFileName);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Gagal upload file: " + e.getMessage());
        }
    }

    @GetMapping("/karyawan/lampiran/{filename:.+}")
    public ResponseEntity<org.springframework.core.io.Resource> getLampiran(@PathVariable String filename) {
        try {
            Path filePath = Paths.get("uploads/karyawan").resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*    @GetMapping("/karyawan/plafon")
    public Integer[] getPlafonByNpk(@RequestParam("npk") String npk) {
        return jaminanService.getPlafonByNpk(npk);
    }*/
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
}
