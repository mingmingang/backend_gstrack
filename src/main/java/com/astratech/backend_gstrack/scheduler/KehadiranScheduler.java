package com.astratech.backend_gstrack.scheduler;

import com.astratech.backend_gstrack.Repository.KaryawanRepository;
import com.astratech.backend_gstrack.Repository.KehadiranRepository;
import com.astratech.backend_gstrack.VO.Karyawan;
import com.astratech.backend_gstrack.VO.Kehadiran;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class KehadiranScheduler {
    @Autowired
    KehadiranRepository kehadiranRepository;
    @Autowired
    KaryawanRepository karyawanRepository;
    @Scheduled(cron = "0 * * * * *", zone = "Asia/Jakarta") // jalan tiap menit, real jam 9 doang juga bisa
    public void validateKehadiran() {
        System.out.println("⏰ Scheduler running...");

        // ➕ Tambahin cek jam > 9
        if (LocalTime.now().isAfter(LocalTime.of(9, 0))) {

            Date today = Date.valueOf(LocalDate.now());
            List<Karyawan> karyawanList = karyawanRepository.findAll();
            List<String> npkListPresent = kehadiranRepository.FilterNpk(today); // YANG INI DIPAKE!

            for (Karyawan k : karyawanList) {
                if (!npkListPresent.contains(k.getNpk())) {
                    Kehadiran alpa = new Kehadiran();
                    alpa.setIdKaryawan(k.getNpk());
                    alpa.setTanggalMasuk(today); // Penting biar record-nya valid
                    alpa.setIndikatorKehadiran(0); // Alpa
                    kehadiranRepository.save(alpa);

                    System.out.println("❌ Alpa dimasukin: " + k.getNpk());
                }
            }
        }
    }


//    @Scheduled(initialDelay = 5000, fixedRate = 10000)
//    public void testScheduled()
//    {
//        System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
//    }

}
