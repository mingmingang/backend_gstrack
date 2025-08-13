//JaminanService
package com.astratech.backend_gstrack.Service;

import com.astratech.backend_gstrack.Repository.JaminanRepository;
import com.astratech.backend_gstrack.Repository.KaryawanRepository;
import com.astratech.backend_gstrack.Repository.RepositoryBantuan.OrangRepository;
import com.astratech.backend_gstrack.Repository.RepositoryBantuan.RumahSakitRepository;
import com.astratech.backend_gstrack.VO.DataBantuan.Orang;
import com.astratech.backend_gstrack.VO.DataBantuan.RumahSakit;
import com.astratech.backend_gstrack.VO.Jaminan;
import com.astratech.backend_gstrack.VO.Karyawan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class JaminanService {
    @Qualifier("JaminanRepository")
    @Autowired
    private JaminanRepository jaminanRepository;

    @Autowired
    private KaryawanRepository karyawanRepository;

    @Autowired
    private OrangRepository orangRepository;

    @Autowired
    private RumahSakitRepository rumahSakitRepository;

    //fulldata
    public List<Map<String, Object>> getJaminanWithOrangAndPlafonAndRsByNpk(String npk) {
        List<Object[]> results = jaminanRepository.findByNpkWithOrangAndPlafonAndRs(npk);
        List<Map<String, Object>> jaminanList = new ArrayList<>();

        for (Object[] result : results) {
            Jaminan jaminan = (Jaminan) result[0];
            String orgNama = (String) result[1];
            String orgHubungan = (String) result[2];
            Integer jumlahPlafon = (Integer) result[3];
            Integer penggunaanPlafon = (Integer) result[4];
            String namaKaryawan = (String) result[5];
            String rsNama = (String) result[6];

            Map<String, Object> map = new HashMap<>();
            map.put("psjId", jaminan.getPsjId());
            map.put("psjNoRequestRecord", jaminan.getPsjNoRequestRecord());
            map.put("kryNpk", jaminan.getKryNpk());
            map.put("namaKaryawan", namaKaryawan);
            map.put("psjPlant", jaminan.getPsjPlant());
            map.put("psjDepartemen", jaminan.getPsjDepartemen());
            map.put("psjTanggalPeriksa", jaminan.getPsjTanggalPeriksa());
            map.put("rsId", jaminan.getRsId());
            map.put("rsNama", rsNama);
            map.put("psjTipeJaminan", jaminan.getPsjTipeJaminan());
            map.put("orgId", jaminan.getOrgId());
            map.put("orgNama", orgNama != null ? orgNama : "Unknown");
            map.put("orgHubungan", orgHubungan != null ? orgHubungan : "Unknown");
            map.put("psjKeterangan", jaminan.getPsjKeterangan());
            map.put("psjStatus", jaminan.getPsjStatus());
            map.put("psjCreatedBy", jaminan.getPsjCreatedBy());
            map.put("psjCreatedDate", jaminan.getPsjCreatedDate());
            map.put("psjModifBy", jaminan.getPsjModifBy());
            map.put("psjModifDate", jaminan.getPsjModifDate());
            map.put("psjAlasanPenolakan", jaminan.getPsjAlasanPenolakan());
            map.put("jumlahPlafon", jumlahPlafon);
            map.put("penggunaanPlafon", penggunaanPlafon);

            jaminanList.add(map);
        }
        return jaminanList;
    }


    //plafon
    public Map<String, Object> getPlafonInfoByNpk(String npk) {
        Karyawan karyawan = karyawanRepository.findById(npk).orElse(null);
        if (karyawan == null) return Collections.emptyMap();

        int jumlahPlafon = karyawan.getJumlah_plafon() != null ? karyawan.getJumlah_plafon() : 0;
        int penggunaanPlafon = karyawan.getPenggunaan_plafon() != null ? karyawan.getPenggunaan_plafon() : 0;
        int sisaPlafon = jumlahPlafon - penggunaanPlafon;

        Map<String, Object> plafonInfo = new HashMap<>();
        plafonInfo.put("jumlahPlafon", jumlahPlafon);
        plafonInfo.put("penggunaanPlafon", penggunaanPlafon);
        plafonInfo.put("sisaPlafon", sisaPlafon);

        return plafonInfo;
    }



    public List<Orang> getOrangByKryNpk(String npk) {
        return orangRepository.findByKryNpk(npk);
    }

    //rumah sakit
    public List<RumahSakit> getRumahSakitRayon() {
        return rumahSakitRepository.findByRsTipeOrderByRsNamaAsc("Rayon");
    }

    public Jaminan getJaminanByNoRequest(String noRequest) {
        return jaminanRepository.findByPsjNoRequestRecord(noRequest);
    }

    //all jaminan (hanya jaminan tidak fk)
    public List<Jaminan> getAllJaminan() {
        return jaminanRepository.findAllOrderByCustomStatusAndPsjCreatedDateDesc();
    }

    public List<Map<String, Object>> getOrangNamaHubunganByKryNpk(String npk) {
        List<Object[]> results = orangRepository.findNamaHubunganByKryNpkOrdered(npk);
        List<Map<String, Object>> response = new ArrayList<>();

        for (Object[] obj : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("orgId", obj[0]);
            map.put("nama", obj[1]);
            map.put("hubungan", obj[2]);
            response.add(map);
        }

        return response;
    }

    public List<Jaminan> getJaminanByNpk(String npk) {
        return jaminanRepository.findByKryNpkOrderByPsjCreatedDateDesc(npk);
    }

    public List<Jaminan> getJaminanByNpkAndStatusAndYear(String npk, String status, Integer year) {
        return jaminanRepository.findByNpkAndStatusAndYear(npk, status, year);
    }

    public List<Jaminan> getJaminanByNpkAndStatus(String npk, String status) {
        return jaminanRepository.findByNpkAndStatus(npk, status);
    }

    public List<Jaminan> getJaminanByNpkAndYear(String npk, Integer year) {
        return jaminanRepository.findByNpkAndYear(npk, year);
    }

//    public boolean saveJaminan(Jaminan jaminan) {
//        Jaminan result = jaminanRepository.save(jaminan);
//        return result != null;
//    }

    public boolean saveJaminan(Jaminan jaminan) {
        // Cek tipe surat jaminan
        String tipe = jaminan.getPsjTipeJaminan();

        if ("Rawat Inap".equalsIgnoreCase(tipe)) {
            jaminan.setPsjStatus("Selesai");
        } else if ("Rawat Jalan".equalsIgnoreCase(tipe)) {
            // Ambil sisa plafon via HTTP request
            try {
                String apiUrl = "http://localhost:8080/karyawan/plafon-info?npk=" + jaminan.getKryNpk();
                java.net.URL url = new java.net.URL(apiUrl);
                java.net.HttpURLConnection con = (java.net.HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");

                int status = con.getResponseCode();
                if (status == 200) {
                    java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuilder content = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                    in.close();
                    con.disconnect();

                    // Parse JSON
                    com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
                    java.util.Map<String, Integer> plafonMap = mapper.readValue(content.toString(), java.util.Map.class);
                    Integer sisaPlafon = plafonMap.get("sisaPlafon");

                    if (sisaPlafon != null && sisaPlafon > 0) {
                        jaminan.setPsjStatus("Selesai");
                    } else {
                        jaminan.setPsjStatus("Menunggu Persetujuan HC");
                    }
                } else {
                    // fallback jika API error
                    jaminan.setPsjStatus("Menunggu Persetujuan HC");
                }
            } catch (Exception e) {
                e.printStackTrace();
                jaminan.setPsjStatus("Menunggu Persetujuan HC");
            }
        } else {
            jaminan.setPsjStatus("Menunggu Persetujuan HC");
        }
        jaminan.setPsjNoRequestRecord(generateNoRequest());
        Jaminan result = jaminanRepository.save(jaminan);
        return result != null;
    }

    public String generateNoRequest() {
        Optional<String> lastNoRequestOpt = jaminanRepository.findMaxNoRequestRecord();

        int nextNumber = 1;

        if (lastNoRequestOpt.isPresent()) {
            String lastNoRequest = lastNoRequestOpt.get();

            // 2. Extract angka dari string terakhir (angka setelah "REQ-")
            Pattern pattern = Pattern.compile("REQ-(\\d+)");
            Matcher matcher = pattern.matcher(lastNoRequest);

            if (matcher.matches()) {
                String numberPart = matcher.group(1);
                nextNumber = Integer.parseInt(numberPart) + 1;
            }
        }

        // 3. Format nomor dengan leading zero 3 digit, misal 1 → "001"
        return String.format("REQ-%03d", nextNumber);
    }

}