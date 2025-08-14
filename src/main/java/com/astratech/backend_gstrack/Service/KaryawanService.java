package com.astratech.backend_gstrack.Service;

import com.astratech.backend_gstrack.Repository.KaryawanRepository;
import com.astratech.backend_gstrack.Repository.RepositoryBantuan.OrangRepository;
import com.astratech.backend_gstrack.VO.DataBantuan.Orang;
import com.astratech.backend_gstrack.VO.Karyawan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//Karyawan Service
@Service
public class KaryawanService {

    @Autowired
    private KaryawanRepository karyawanRepository;
    @Autowired
    private OrangRepository orangRepository;


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

        if (karyawan.getNamaKaryawan() != null) {
            existingKaryawan.setNamaKaryawan(karyawan.getNamaKaryawan());
        }
        if (karyawan.getEmail() != null) {
            existingKaryawan.setEmail(karyawan.getEmail());
        }
        if (karyawan.getDepartemen() != null) {
            existingKaryawan.setDepartemen(karyawan.getDepartemen());
        }
        if (karyawan.getNoHandphone() != null) {
            existingKaryawan.setNoHandphone(karyawan.getNoHandphone());
        }
        if (karyawan.getPlant() != null) {
            existingKaryawan.setPlant(karyawan.getPlant());
        }
        if (karyawan.getStatus() != null) {
            existingKaryawan.setStatus(karyawan.getStatus());
        }
        if (karyawan.getFotoKaryawan() != null) {
            existingKaryawan.setFotoKaryawan(karyawan.getFotoKaryawan());
        }
        if (karyawan.getTanggalLahir() != null) {
            existingKaryawan.setTanggalLahir(karyawan.getTanggalLahir());
        }
        if (karyawan.getAlamat() != null) {
            existingKaryawan.setAlamat(karyawan.getAlamat());
        }
        if (karyawan.getModifBy() != null) {
            existingKaryawan.setModifBy(karyawan.getModifBy());
        }
        if (karyawan.getModifDate() != null) {
            existingKaryawan.setModifDate(karyawan.getModifDate());
        }


        karyawanRepository.save(existingKaryawan);
        return true;
    }

    @Transactional
    public boolean updatePassword(String npk, String newPassword) {
        int updated = karyawanRepository.updatePassword(npk, newPassword);
        return updated > 0;
    }

    public boolean deleteKaryawan(String npk) {
        Karyawan existingKaryawan = karyawanRepository.findByNpk(npk);
        if (existingKaryawan == null) {
            return false;
        }
        karyawanRepository.delete(existingKaryawan);
        return true;
    }
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
}
