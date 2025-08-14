package com.astratech.backend_gstrack.Service;

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
import java.util.Base64;
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
        return reimbursementRepository.findReimbursementsByNpkAndYear(npk, year)
                .stream()
                .map(r -> {
                    ReimbursementDto dto = new ReimbursementDto();
                    dto.setRbmId(r.getRbmId());
                    dto.setKryNpk(r.getKryNpk());
                    dto.setOrgId(r.getOrgId());
                    dto.setRbmTipe(r.getRbmTipe());
                    dto.setRbmTanggalMulai(r.getRbmTanggalMulai());
                    dto.setRbmTanggalSelesai(r.getRbmTanggalSelesai());
                    dto.setRbmCost(r.getRbmCost());
                    dto.setDgsId(r.getDgsId());
                    dto.setRbmDiagnosaOther(r.getRbmDiagnosaOther());
                    dto.setRsId(r.getRsId());
                    dto.setRbmDokter(r.getRbmDokter());
                    dto.setRbmFilePathKwitansi(r.getRbmFilePathKwitansi());
                    dto.setRbmFilePathRincianObat(r.getRbmFilePathRincianObat());
                    dto.setRbmFilePathHasilLab(r.getRbmFilePathHasilLab());
                    dto.setRbmFilePathResumeMedis(r.getRbmFilePathResumeMedis());
                    dto.setRbmStatusSubmit(r.getRbmStatusSubmit());
                    dto.setRbmCreatedBy(r.getRbmCreatedBy());
                    dto.setRbmCreatedDate(r.getRbmCreatedDate());
                    dto.setRbmModifyBy(r.getRbmModifyBy());
                    dto.setRbmModifyDate(r.getRbmModifyDate());
                    dto.setRbmAlasanPembatalan(r.getRbmAlasanPembatalan());

                    // Mapping nama karyawan / anggota
                    dto.setKryNama(r.getKaryawan() != null ? r.getKaryawan().getNamaKaryawan() : null);
                    dto.setOrgNama(r.getOrang() != null ? r.getOrang().getOrgNama() : null);
                    dto.setOrgHubungan(r.getOrang() != null ? r.getOrang().getOrgHubungan() : null);

                    // Mapping Rumah Sakit & Diagnosa
                    dto.setRsNama(r.getRumahSakit() != null ? r.getRumahSakit().getRsNama() : null);
                    dto.setRsTipe(r.getRumahSakit() != null ? r.getRumahSakit().getRsTipe() : null);
                    dto.setDgsNama(r.getDiagnosa() != null ? r.getDiagnosa().getDgsNama() : null);

                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReimbursementDto> getReimbursements(int year) {
        List<Reimbursement> reimbursementList = reimbursementRepository.findReimbursementsByYear(year);
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

    @Transactional(readOnly = true)
    public int getEarliestYear() {
        Integer earliestYear = reimbursementRepository.findEarliestYear();
        return earliestYear != null ? earliestYear : LocalDate.now().getYear();
    }


    // ============== FUNGSI UNTUK MENULIS DATA (ADD/CREATE) DENGAN VARBINARY ==============
    @Transactional
    public Reimbursement saveReimbursement(
            String kryNpk, BigInteger orgId, String rbmTipe, BigDecimal rbmCost, String rbmDokter, String rbmCreatedBy,
            String dgsId, Integer rsId, Date rbmTanggalMulai, Date rbmTanggalSelesai, String rbmDiagnosaOther,
            MultipartFile fileKwitansi, MultipartFile fileRincianObat, MultipartFile fileHasilLab, MultipartFile fileResumeMedis
    ) throws IOException {

        Reimbursement newReimbursement = new Reimbursement();

        // 1. Set ID menggunakan generator baru (PERBAIKAN 1 & 3)
        newReimbursement.setRbmId(generateReimbursementId(kryNpk));

        // Set semua data teks dan numerik lainnya
        newReimbursement.setKryNpk(kryNpk);
        newReimbursement.setDgsId(dgsId);

        if (orgId != null && !orgId.equals(BigInteger.ZERO)) {
            newReimbursement.setOrgId(orgId);
        }

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
//        if (fileKwitansi != null && !fileKwitansi.isEmpty()) {
//            newReimbursement.setRbmFilePathKwitansi(fileKwitansi.getBytes());
//        }
//        if (fileRincianObat != null && !fileRincianObat.isEmpty()) {
//            newReimbursement.setRbmFilePathRincianObat(fileRincianObat.getBytes());
//        }
//        if (fileHasilLab != null && !fileHasilLab.isEmpty()) {
//            newReimbursement.setRbmFilePathHasilLab(fileHasilLab.getBytes());
//        }
//        if (fileResumeMedis != null && !fileResumeMedis.isEmpty()) {
//            newReimbursement.setRbmFilePathResumeMedis(fileResumeMedis.getBytes());
//        }

        if (fileKwitansi != null && !fileKwitansi.isEmpty()) {
            newReimbursement.setRbmFilePathKwitansi(Base64.getEncoder().encodeToString(fileKwitansi.getBytes()));
        }
        if (fileRincianObat != null && !fileRincianObat.isEmpty()) {
            newReimbursement.setRbmFilePathRincianObat(Base64.getEncoder().encodeToString(fileRincianObat.getBytes()));
        }
        if (fileHasilLab != null && !fileHasilLab.isEmpty()) {
            newReimbursement.setRbmFilePathHasilLab(Base64.getEncoder().encodeToString(fileHasilLab.getBytes()));
        }
        if (fileResumeMedis != null && !fileResumeMedis.isEmpty()) {
            newReimbursement.setRbmFilePathResumeMedis(Base64.getEncoder().encodeToString(fileResumeMedis.getBytes()));
        }

        // 3. Simpan entity ke database
        return reimbursementRepository.save(newReimbursement);
    }

    /**
     * (BARU) Method untuk generate RBM_ID sesuai format: yyMMddSS (8 digit)
     * yy = tahun, MM = bulan, dd = tanggal, SS = urutan (01-99)
     * Contoh: 24052101 (pengajuan pertama pada 21 Mei 2024)
     */
    private BigInteger generateReimbursementId(String kryNpk) {
        // Format: {kryNpk}{yyMMdd}{seq}
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        String datePrefix = today.format(formatter); // Contoh: "250711"

        // Range berbasis prefix NPK + tanggal
        String basePrefix = kryNpk + datePrefix; // Contoh: "123456250711"
        BigInteger startRange = new BigInteger(basePrefix + "01");
        BigInteger endRange = new BigInteger(basePrefix + "99");

        // Hitung entri yang sudah ada untuk hari ini dan NPK ini
        long count = reimbursementRepository.countByIdInRange(startRange, endRange);
        long sequence = count + 1;

        if (sequence > 99) {
            throw new IllegalStateException("Batas maksimal pengajuan harian oleh karyawan telah tercapai (99).");
        }

        String newIdString = basePrefix + String.format("%02d", sequence);
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

        if (reimbursement.getOrang() != null) {
            dto.setOrgId(reimbursement.getOrang().getOrgId());
            if (reimbursement.getOrang() != null) {
                dto.setOrgNama(reimbursement.getOrang().getOrgNama());
                dto.setOrgHubungan(reimbursement.getOrang().getOrgHubungan());
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

    // ============== FUNGSI UNTUK UPDATE STATUS PERSETUJUAN (METODE BARU) ==============
    @Transactional
    public ReimbursementDto updateReimbursementStatus(BigInteger rbmId, String newStatus, String modifiedByNpk) {
        // 1. Cari reimbursement berdasarkan ID
        Reimbursement reimbursementToUpdate = reimbursementRepository.findById(rbmId)
                .orElseThrow(() -> new NotFoundException("Reimbursement with ID " + rbmId + " not found. Cannot update status."));

        // 2. Tentukan status baru berdasarkan input dari frontend
        String finalStatus;
        if ("Setujui".equalsIgnoreCase(newStatus)) {
            finalStatus = "Belum Diverifikasi";
        } else if ("Ditolak".equalsIgnoreCase(newStatus)) {
            finalStatus = "Ditolak";
        } else {
            // Jika status yang dikirim tidak valid, lemparkan exception agar tidak ada data aneh yang masuk
            throw new IllegalArgumentException("Invalid status update value: " + newStatus);
        }

        // 3. Update field yang relevan
        reimbursementToUpdate.setRbmStatusSubmit(finalStatus);
        reimbursementToUpdate.setRbmModifyBy(modifiedByNpk); // NPK user yang melakukan approval/rejection
        reimbursementToUpdate.setRbmModifyDate(new Date());

        // 4. Simpan perubahan ke database
        Reimbursement updatedReimbursement = reimbursementRepository.save(reimbursementToUpdate);

        // 5. Kembalikan data yang sudah di-update dalam bentuk DTO
        return convertToDto(updatedReimbursement);
    }
}