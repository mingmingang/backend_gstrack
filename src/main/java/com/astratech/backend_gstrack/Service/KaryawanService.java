package com.astratech.backend_gstrack.Service;

import com.astratech.backend_gstrack.Repository.KaryawanRepository;
import com.astratech.backend_gstrack.Repository.RepositoryBantuan.OrangRepository;
import com.astratech.backend_gstrack.VO.DataBantuan.Orang;
import com.astratech.backend_gstrack.VO.Karyawan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // PERUBAHAN 2: Tambahkan import untuk Transactional
import org.springframework.util.StringUtils;

import java.util.HashMap; // PERUBAHAN 3: Tambahkan import yang diperlukan
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class KaryawanService {

    @Autowired
    private KaryawanRepository karyawanRepository;

    @Autowired
    private OrangRepository orangRepository; // PERUBAHAN 4: Injeksi dependency KeluargaRepository


    /**
     * [LOGIKA DIPERBAIKI]
     * Mengambil data dasar karyawan (NPK, Nama) dan daftar keluarganya (anak, istri, dll).
     * Data keluarga diambil dari tabel 'gs_track_orang', bukan dari histori reimbursement.
     * @param npk Nomor Pokok Karyawan
     * @return Map yang berisi data karyawan dan daftar keluarganya.
     */
    @Transactional(readOnly = true)
    public Map<String, Object> getUserDataWithFamily(String npk) {
        // Langkah 1: Cari data karyawan berdasarkan NPK. (Sudah benar)
        Karyawan karyawan = karyawanRepository.findByNpk(npk);

        // Jika karyawan tidak ditemukan, kembalikan null agar controller bisa mengirim 404 Not Found
        if (karyawan == null) {
            return null;
        }

        // --- [PERBAIKAN 2: Sumber Data yang Benar] ---
        // Panggil method dari OrangRepository untuk mendapatkan daftar keluarga berdasarkan NPK Karyawan.
        List<Orang> daftarKeluarga = orangRepository.findByKryNpk(npk);

        // Ubah List<Orang> menjadi List<Map<String, Object>> sesuai format JSON yang diinginkan
        List<Map<String, Object>> keluargaListJson = daftarKeluarga.stream()
                .map(orang -> { // 'orang' di sini adalah objek dari entitas Orang
                    Map<String, Object> dataOrang = new HashMap<>();
                    dataOrang.put("org_id", orang.getOrgId());
                    dataOrang.put("org_nama", orang.getOrgNama());
                    dataOrang.put("org_hubungan", orang.getOrgHubungan());
                    return dataOrang;
                }).collect(Collectors.toList());


        // Langkah 3: Gabungkan semua data ke dalam satu Map
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("kry_npk", karyawan.getNpk()); // Pastikan nama getter sesuai di entitas Karyawan
        responseData.put("kry_nama", karyawan.getNamaKaryawan());
        // [PERBAIKAN 3: Ganti nama key agar lebih deskriptif]
        responseData.put("keluarga", keluargaListJson);

        return responseData;
    }
    // === AKHIR METHOD BARU ===



    // === METHOD ANDA YANG SUDAH ADA (TIDAK ADA PERUBAHAN) ===

    public Karyawan getKaryawanByNpk(String npk) {
        return karyawanRepository.findByNpk(npk);
    }

    public List<Karyawan> getAllKaryawan() {
        return karyawanRepository.findAllByOrderByNpkAsc();
    }

    public boolean saveKaryawan(Karyawan karyawan) {
        Karyawan result = karyawanRepository.save(karyawan);
        return result != null;
    }

    public boolean updateKaryawan(Karyawan karyawan) {
        Karyawan existingKaryawan = karyawanRepository.findByNpk(karyawan.getNpk());
        if (existingKaryawan == null) {
            return false;
        }

        if (StringUtils.hasLength(karyawan.getNamaKaryawan())) {
            existingKaryawan.setNamaKaryawan(karyawan.getNamaKaryawan());
        }
        if (StringUtils.hasLength(karyawan.getEmail())) {
            existingKaryawan.setEmail(karyawan.getEmail());
        }
        if (StringUtils.hasLength(karyawan.getDepartemen())) {
            existingKaryawan.setDepartemen(karyawan.getDepartemen());
        }
        if (StringUtils.hasLength(karyawan.getNoHandphone())) {
            existingKaryawan.setNoHandphone(karyawan.getNoHandphone());
        }
        if (StringUtils.hasLength(karyawan.getPlant())) {
            existingKaryawan.setPlant(karyawan.getPlant());
        }
        if (StringUtils.hasLength(karyawan.getStatus())) {
            existingKaryawan.setStatus(karyawan.getStatus());
        }

        karyawanRepository.save(existingKaryawan);
        return true;
    }

    public boolean deleteKaryawan(String npk) {
        Karyawan existingKaryawan = karyawanRepository.findByNpk(npk);
        if (existingKaryawan == null) {
            return false;
        }
        karyawanRepository.delete(existingKaryawan);
        return true;
    }
}