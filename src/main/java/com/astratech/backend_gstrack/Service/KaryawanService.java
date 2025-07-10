package com.astratech.backend_gstrack.Service;

import com.astratech.backend_gstrack.Repository.KaryawanRepository;
import com.astratech.backend_gstrack.Repository.RepositoryBantuan.KeluargaRepository; // PERUBAHAN 1: Tambahkan import untuk KeluargaRepository
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
    private KeluargaRepository keluargaRepository; // PERUBAHAN 4: Injeksi dependency KeluargaRepository


    // === METHOD BARU UNTUK KEBUTUHAN HALAMAN "TAMBAH REIMBURSEMENT" ===
    /**
     * Mengambil data dasar karyawan (NPK, Nama) dan daftar keluarganya.
     * Dijalankan dalam satu transaksi read-only untuk konsistensi data.
     * @param npk Nomor Pokok Karyawan
     * @return Map yang berisi data karyawan dan list keluarga, atau null jika karyawan tidak ditemukan.
     */
    @Transactional(readOnly = true) // PERUBAHAN 5: Menambahkan method baru
    public Map<String, Object> getUserDataWithFamily(String npk) {
        // Langkah 1: Cari data karyawan berdasarkan NPK. findByNpk sudah Anda miliki.
        Karyawan karyawan = karyawanRepository.findByNpk(npk);

        // Jika karyawan tidak ditemukan, kembalikan null agar controller bisa mengirim 404
        if (karyawan == null) {
            return null;
        }

        // Langkah 2: Panggil method dari KeluargaRepository untuk mendapatkan daftar keluarga.
        // Anda perlu memastikan KeluargaRepository memiliki method findKeluargaByNpk.
        // Kita akan menggunakan stream untuk mengubah list Keluarga VO menjadi list Map<String, Object>.
        List<Map<String, Object>> keluargaList = keluargaRepository.findKeluargaByNpk(npk).stream()
                .map(keluarga -> {
                    Map<String, Object> dataKeluarga = new HashMap<>();
                    dataKeluarga.put("klg_id", keluarga.getKlgId());

                    // Ambil data dari relasi 'orang' jika ada, untuk menghindari NullPointerException
                    if (keluarga.getOrang() != null) {
                        dataKeluarga.put("org_nama", keluarga.getOrang().getOrgNama());
                        dataKeluarga.put("org_hubungan", keluarga.getOrang().getOrgHubungan());
                    } else {
                        dataKeluarga.put("org_nama", "Data tidak lengkap");
                        dataKeluarga.put("org_hubungan", "-");
                    }
                    return dataKeluarga;
                }).collect(Collectors.toList());


        // Langkah 3: Gabungkan semua data ke dalam satu Map dengan format yang diharapkan frontend
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("kry_npk", karyawan.getNpk());
        responseData.put("kry_nama", karyawan.getNamaKaryawan()); // Sesuaikan getter jika namanya berbeda
        responseData.put("keluarga", keluargaList);

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