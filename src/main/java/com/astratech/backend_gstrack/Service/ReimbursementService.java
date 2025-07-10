package com.astratech.backend_gstrack.Service;

import com.astratech.backend_gstrack.DTO.PembatalanReimbursementDto;
import com.astratech.backend_gstrack.DTO.ReimbursementDto;
import com.astratech.backend_gstrack.Repository.ReimbursementRepository;
import com.astratech.backend_gstrack.VO.Reimbursement;
import com.astratech.backend_gstrack.Exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReimbursementService {

    @Autowired
    private ReimbursementRepository reimbursementRepository;

    // ============== FUNGSI UNTUK MEMBACA DATA (GET) ==============
    // FUNGSI GET TIDAK PERLU DIUBAH, TAPI PERHATIKAN DTO

    @Transactional(readOnly = true)
    public List<ReimbursementDto> getReimbursements(String npk, int year) {
        List<Reimbursement> reimbursementList = reimbursementRepository.findReimbursementsByNpkAndYear(npk, year);
        return reimbursementList.stream()
                .map(this::convertToDto) // Pastikan DTO tidak memuat byte[]
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ReimbursementDto getReimbursementById(BigInteger rbmId) {
        Reimbursement reimbursement = reimbursementRepository.findById(rbmId)
                .orElseThrow(() -> new NotFoundException("Reimbursement with ID " + rbmId + " not found."));
        return convertToDto(reimbursement);
    }


    // ============== FUNGSI UNTUK MENULIS DATA (ADD/CREATE) DENGAN VARBINARY ==============
    @Transactional
    public Reimbursement saveReimbursement(
            String kryNpk, Integer klgId, String rbmTipe, BigDecimal rbmCost, String rbmDokter, String rbmCreatedBy,
            String dgsId, Integer rsId, Date rbmTanggalMulai, Date rbmTanggalSelesai, String rbmDiagnosaOther,
            MultipartFile fileKwitansi, MultipartFile fileRincianObat, MultipartFile fileHasilLab, MultipartFile fileResumeMedis
    ) throws IOException {

        Reimbursement newReimbursement = new Reimbursement();

        // 1. Set ID menggunakan generator baru (PERBAIKAN 1 & 3)
        newReimbursement.setRbmId(generateReimbursementId());

        // Set semua data teks dan numerik lainnya
        newReimbursement.setKryNpk(kryNpk);
        newReimbursement.setDgsId(dgsId);
        newReimbursement.setKlgId(klgId);
        newReimbursement.setRsId(rsId);
        newReimbursement.setRbmTipe(rbmTipe);
        newReimbursement.setRbmCost(rbmCost);
        newReimbursement.setRbmDokter(rbmDokter);
        newReimbursement.setRbmTanggalMulai(rbmTanggalMulai);
        newReimbursement.setRbmTanggalSelesai(rbmTanggalSelesai);
        newReimbursement.setRbmDiagnosaOther(rbmDiagnosaOther);
        newReimbursement.setRbmCreatedBy(rbmCreatedBy);
        newReimbursement.setRbmCreatedDate(new Date());
        newReimbursement.setRbmStatusSubmit("Menunggu Persetujuan");

        // 2. Ubah MultipartFile menjadi byte[] dan set ke entity (Tidak ada perubahan di sini)
        if (fileKwitansi != null && !fileKwitansi.isEmpty()) {
            newReimbursement.setRbmFilePathKwitansi(fileKwitansi.getBytes());
        }
        if (fileRincianObat != null && !fileRincianObat.isEmpty()) {
            newReimbursement.setRbmFilePathRincianObat(fileRincianObat.getBytes());
        }
        if (fileHasilLab != null && !fileHasilLab.isEmpty()) {
            newReimbursement.setRbmFilePathHasilLab(fileHasilLab.getBytes());
        }
        if (fileResumeMedis != null && !fileResumeMedis.isEmpty()) {
            newReimbursement.setRbmFilePathResumeMedis(fileResumeMedis.getBytes());
        }

        // 3. Simpan entity ke database
        return reimbursementRepository.save(newReimbursement);
    }

    /**
     * (BARU) Method untuk generate RBM_ID sesuai format: yyMMddSS (8 digit)
     * yy = tahun, MM = bulan, dd = tanggal, SS = urutan (01-99)
     * Contoh: 24052101 (pengajuan pertama pada 21 Mei 2024)
     */
    private BigInteger generateReimbursementId() {
        // 1. Dapatkan prefix tanggal hari ini (yyMMdd)
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        String datePrefix = today.format(formatter); // Contoh: "240521"

        // 2. Tentukan rentang ID untuk hari ini untuk query ke database
        BigInteger startRange = new BigInteger(datePrefix + "01"); // 24052101
        BigInteger endRange = new BigInteger(datePrefix + "99");   // 24052199

        // 3. Hitung berapa banyak entri yang sudah ada hari ini
        long countForToday = reimbursementRepository.countByIdInRange(startRange, endRange);

        // 4. Tentukan nomor urut berikutnya
        long sequence = countForToday + 1;

        // 5. Cek jika urutan melebihi 99 (opsional, tapi best practice)
        if (sequence > 99) {
            throw new IllegalStateException("Batas maksimal pengajuan harian (99) telah tercapai.");
        }

        // 6. Gabungkan prefix tanggal dengan nomor urut
        String newIdString = datePrefix + String.format("%02d", sequence); // Format urutan menjadi 2 digit, e.g., 1 -> "01"

        return new BigInteger(newIdString);
    }

    // ============== METODE HELPER (PRIVATE) UNTUK DTO ==============
    private ReimbursementDto convertToDto(Reimbursement reimbursement) {
        if (reimbursement == null) {
            return null;
        }

        ReimbursementDto dto = new ReimbursementDto();

        // Salin semua properti dari entity ke DTO
        dto.setRbmId(reimbursement.getRbmId()); // Pastikan tipe data konsisten (Long)
        dto.setRbmTipe(reimbursement.getRbmTipe());
        dto.setRbmTanggalMulai(reimbursement.getRbmTanggalMulai());
        dto.setRbmTanggalSelesai(reimbursement.getRbmTanggalSelesai());
        dto.setRbmCost(reimbursement.getRbmCost());
        dto.setRbmDiagnosaOther(reimbursement.getRbmDiagnosaOther());
        dto.setRbmDokter(reimbursement.getRbmDokter());
        dto.setRbmStatusSubmit(reimbursement.getRbmStatusSubmit());
        dto.setRbmCreatedBy(reimbursement.getRbmCreatedBy());
        dto.setRbmCreatedDate(reimbursement.getRbmCreatedDate());
        dto.setRbmModifyBy(reimbursement.getRbmModifyBy());
        dto.setRbmModifyDate(reimbursement.getRbmModifyDate());
        dto.setRbmAlasanPembatalan(reimbursement.getRbmAlasanPembatalan());

        // PENTING: JANGAN MASUKKAN DATA BINARY (byte[]) KE DTO
        // Ini akan membuat response JSON sangat besar dan lambat.
        // Jika frontend perlu menampilkan file, buat endpoint terpisah untuk men-download-nya.
        // Cukup beritahu frontend apakah file ada atau tidak.
        dto.setRbmFilePathKwitansi(reimbursement.getRbmFilePathKwitansi() != null ? "file_exists" : null);
        dto.setRbmFilePathRincianObat(reimbursement.getRbmFilePathRincianObat() != null ? "file_exists" : null);
        dto.setRbmFilePathHasilLab(reimbursement.getRbmFilePathHasilLab() != null ? "file_exists" : null);
        dto.setRbmFilePathResumeMedis(reimbursement.getRbmFilePathResumeMedis() != null ? "file_exists" : null);

        // --- Proses Mapping dari Relasi (Penting!) ---
        // Null-check diperlukan karena query menggunakan LEFT JOIN.
        // Jika tidak ada data yang cocok di tabel join, objek relasi akan bernilai null.

        if (reimbursement.getKaryawan() != null) {
            dto.setKryNpk(reimbursement.getKaryawan().getNpk());
            dto.setKryNama(reimbursement.getKaryawan().getNamaKaryawan());
        }

        if (reimbursement.getKeluarga() != null) {
            dto.setKlgId(reimbursement.getKeluarga().getKlgId());
            if (reimbursement.getKeluarga().getOrang() != null) {
                dto.setOrgNama(reimbursement.getKeluarga().getOrang().getOrgNama());
                dto.setOrgHubungan(reimbursement.getKeluarga().getOrang().getOrgHubungan());
            }
        }

        if(reimbursement.getDiagnosa() != null){
            dto.setDgsId(reimbursement.getDiagnosa().getDgsId());
            dto.setDgsNama(reimbursement.getDiagnosa().getDgsNama());
        }

        if(reimbursement.getRumahSakit() != null){
            dto.setRsId(reimbursement.getRumahSakit().getRsId());
            dto.setRsTipe(reimbursement.getRumahSakit().getRsTipe());
            dto.setRsNama(reimbursement.getRumahSakit().getRsNama());
        }

        return dto;
    }

    // ============== FUNGSI UNTUK PEMBATALAN (UPDATE) ==============
    //                       --- METODE BARU ---
    @Transactional
    public ReimbursementDto cancelReimbursement(BigInteger rbmId, ReimbursementDto request) {
        // 1. Cari reimbursement berdasarkan ID
        Reimbursement reimbursementToCancel = reimbursementRepository.findById(rbmId)
                .orElseThrow(() -> new NotFoundException("Reimbursement with ID " + rbmId + " not found. Cannot cancel."));

        // 2. Lakukan update pada kolom-kolom yang diperlukan
        reimbursementToCancel.setRbmStatusSubmit("Dibatalkan");
        reimbursementToCancel.setRbmAlasanPembatalan(request.getRbmAlasanPembatalan()); // Ambil alasan dari DTO
        reimbursementToCancel.setRbmModifyBy(request.getKryNpk()); // Ambil NPK dari DTO
        reimbursementToCancel.setRbmModifyDate(new Date()); // Set tanggal modifikasi ke waktu sekarang

        // 3. Simpan perubahan ke database
        Reimbursement updatedReimbursement = reimbursementRepository.save(reimbursementToCancel);

        // 4. Kembalikan hasil yang sudah diupdate dalam bentuk DTO
        return convertToDto(updatedReimbursement);
    }
}