package com.astratech.backend_gstrack.Service;

import com.astratech.backend_gstrack.Repository.KehadiranRepository;
import com.astratech.backend_gstrack.VO.Kehadiran;
import com.astratech.backend_gstrack.VO.Result;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class KehadiranService {
    @Autowired private KehadiranRepository kehadiranRepository;
    public Result getKehadiran(Date startDate, Date endDate, String kryNpk) {
        List<Kehadiran> kehadiranList =
                (startDate != null && endDate != null && kryNpk != null)
                        ? kehadiranRepository.FilterDate(startDate, endDate, kryNpk)
                        : kehadiranRepository.findAll();

        String message = kehadiranList.isEmpty() ? "No data available yet." : "SUKSES";
        return new Result(200, message, kehadiranList);
    }

    public Result checkIn(Kehadiran kehadiran, MultipartFile foto) {
        try {
            // Pastikan path folder
            String folderPath = "uploads/kehadiran/checkin/";
            File folder = new File(folderPath);
            if (!folder.exists()) folder.mkdirs();

            // Ambil info NPK dan tanggal
            String npk = kehadiran.getIdKaryawan();
            LocalDate tanggal = kehadiran.getTanggalMasuk().toLocalDate(); // pastikan pakai `java.time.LocalDate`

            // Format nama file: EMP001_20250712.jpg
            String tanggalStr = tanggal.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            String extension = getFileExtension(foto.getOriginalFilename());
            String fileName = npk + "_" + tanggalStr + "." + extension;

            // Simpan file
            Path filePath = Paths.get(folderPath + fileName);
            Files.copy(foto.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Set nama file ke entity
            kehadiran.setFotoMasuk(fileName);
            kehadiran.setMasukAbsen(LocalDateTime.now());

            // Save ke DB
            Kehadiran saved = kehadiranRepository.save(kehadiran);

            return new Result(200, "SUKSES", saved);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(500, "GAGAL UPLOAD FOTO", null);
        }
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex >= 0) ? fileName.substring(dotIndex + 1) : "jpg"; // default jpg
    }



    public Result checkOut(Kehadiran data, MultipartFile foto) {
        try {
            // Cari data berdasarkan npk & tanggal
            String npk = data.getIdKaryawan();
            Optional<Kehadiran> optional = Optional.ofNullable(kehadiranRepository.findByKryNpkAndTanggal(npk));
            if (optional.isEmpty()) {
                return new Result(404, "DATA TIDAK DITEMUKAN", null);
            }

            Kehadiran kehadiran = optional.get();

            // Simpan foto keluar
            String folderPath = "uploads/kehadiran/checkout/";
            File folder = new File(folderPath);
            if (!folder.exists()) folder.mkdirs();

            LocalDate tanggal = LocalDate.now();
            String tanggalStr = tanggal.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            String extension = getFileExtension(foto.getOriginalFilename());
            String fileName = npk + "_" + tanggalStr + "_OUT." + extension;

            Path filePath = Paths.get(folderPath + fileName);
            Files.copy(foto.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Set field foto_keluar
            kehadiran.setFotoKeluar(fileName);
            // Update jam_keluar (optional, kalau mau otomatis)
            kehadiran.setKeluarAbsen(LocalDateTime.now());
            kehadiran.setLongitudeKeluar(data.getLongitudeKeluar());
            kehadiran.setLatitudeKeluar(data.getLatitudeKeluar());

            Kehadiran updated = kehadiranRepository.save(kehadiran);
            System.out.println("IDDDDDDDDDDDDDD: " + kehadiran.getIdAbsen());
            System.out.println(kehadiran.getIdKaryawan());
            System.out.println(kehadiran.getLatitudeKeluar());
            System.out.println(kehadiran.getKeluarAbsen());

            return new Result(200, "SUKSES CHECKOUT", updated);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(500, "GAGAL UPLOAD FOTO", null);
        }
    }

    public Result currentSessionKehadiran(Kehadiran kehadiran) {
        Kehadiran current = kehadiranRepository.currentSessionKehadiran(Date.valueOf(LocalDate.now()),kehadiran.getIdKaryawan());
        return new Result(200, "SUKSES", current);
    }

    public Result currentLoggedKehadiran(String npk) {
        List<Kehadiran> kehadiranList = kehadiranRepository.currentLoggedKehadiran(npk);
        return new Result(200, "SUKSES", kehadiranList);
    }

}
