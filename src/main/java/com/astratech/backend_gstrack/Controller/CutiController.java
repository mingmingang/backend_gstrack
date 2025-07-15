package com.astratech.backend_gstrack.Controller;

import com.astratech.backend_gstrack.Service.CutiService;
import com.astratech.backend_gstrack.VO.Cuti;
import com.astratech.backend_gstrack.VO.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

@RestController
public class CutiController {

    @Autowired
    private CutiService cutiService;

    @GetMapping("/cuti")
    public List<Cuti> getAllCuti() {
        return cutiService.getAllCuti();
    }

    @GetMapping("/cuti/{id}")
    public Cuti getCutiById(@PathVariable("id") String cutiId) {
        return cutiService.getCutiById(cutiId);
    }

    @GetMapping("/cuti/karyawan")
    public List<Cuti> getCutiByNpk(
            @RequestParam("npk") String npk,
            @RequestParam(value = "jenis", required = false) String jenisCuti,
            @RequestParam(value = "status", required = false) String status
    ) {
        if (jenisCuti != null && !jenisCuti.isEmpty() &&
                status != null && !status.isEmpty()) {
            return cutiService.getCutiByNpkAndJenisAndStatus(npk, jenisCuti, status);
        } else if (jenisCuti != null && !jenisCuti.isEmpty()) {
            return cutiService.getCutiByNpkAndJenis(npk, jenisCuti);
        } else if (status != null && !status.isEmpty()) {
            return cutiService.getCutiByNpkAndStatus(npk, status);
        } else {
            return cutiService.getCutiByNpk(npk);
        }
    }

    @PostMapping("/cuti")
    public Object saveCuti(HttpServletResponse response, @RequestBody Cuti cuti) {
        boolean isSuccess = cutiService.saveCuti(cuti);
        if (isSuccess) {
            return new Result(200, "Cuti berhasil disimpan");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Gagal menyimpan cuti");
        }
    }

    @PutMapping("/cuti")
    public Object updateCuti(HttpServletResponse response, @RequestBody Cuti cuti) {
        boolean isSuccess = cutiService.updateCuti(cuti);
        if (isSuccess) {
            return new Result(200, "Cuti berhasil diperbarui");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Gagal memperbarui cuti");
        }
    }

    @DeleteMapping("/cuti/{id}")
    public Object deleteCuti(HttpServletResponse response, @PathVariable("id") String cutiId) {
        boolean isSuccess = cutiService.deleteCuti(cutiId);
        if (isSuccess) {
            return new Result(200, "Cuti berhasil dihapus");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Gagal menghapus cuti");
        }
    }

    @PostMapping("/cuti/upload-lampiran")
    public ResponseEntity<String> uploadLampiran(@RequestParam("file") MultipartFile file) {
        try {
            String rootPath = System.getProperty("user.dir");
            String folderPath = rootPath + File.separator + "uploads" + File.separator + "lampiran";

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


    @GetMapping("/cuti/lampiran/{filename:.+}")
    public ResponseEntity<Resource> getLampiran(@PathVariable String filename) {
        try {
            Path filePath = Paths.get("uploads/lampiran").resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.notFound().build();
            }

            // Deteksi MIME type (application/pdf, image/jpeg, dll)
            String contentType = Files.probeContentType(filePath);
            if (contentType == null) {
                contentType = "application/octet-stream"; // fallback
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/cuti/size")
    public ResponseEntity<Map<String, Long>> getFolderSizes() {
        Map<String, Long> sizes = new HashMap<>();

        sizes.put("cuti", calculateSize("uploads/lampiran"));
        // tambahin folder lain juga

        return ResponseEntity.ok(sizes);
    }

    private long calculateSize(String folderPath) {
        try (Stream<Path> files = Files.walk(Paths.get(folderPath))) {
            return files
                    .filter(Files::isRegularFile)
                    .mapToLong(p -> {
                        try {
                            return Files.size(p);
                        } catch (IOException e) {
                            return 0;
                        }
                    }).sum();
        } catch (IOException e) {
            return 0;
        }
    }
}
