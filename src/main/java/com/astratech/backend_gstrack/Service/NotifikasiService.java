package com.astratech.backend_gstrack.Service;

import com.astratech.backend_gstrack.Repository.NotifikasiRepository;
import com.astratech.backend_gstrack.Repository.TokenRepositry;
import com.astratech.backend_gstrack.VO.Notifikasi;
import com.astratech.backend_gstrack.VO.Result;
import com.astratech.backend_gstrack.VO.Token;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class NotifikasiService {
    @Autowired private NotifikasiRepository notifikasiRepository;
    @Autowired private TokenRepositry tokenRepositry;
    private final String EXPO_PUSH_URL = "https://exp.host/--/api/v2/push/send";
    public Result getNotifikasi(Notifikasi notifikasi) {
        String npk = notifikasi.getIdKaryawan();
        return new Result(200,"SUKSES",notifikasiRepository.findAllbyNPK(npk));
    }
    public Result saveNotifkasi(Notifikasi notifikasi) {
        notifikasi.setStatusDibaca(0);
        notifikasi.setTanggalNotif(LocalDateTime.now());
        String npk = notifikasi.getIdKaryawan();
        Token token = tokenRepositry.findByKryNpk(npk);
        String uniqueToken = token.getToken();
        String judul = notifikasi.getJudulNotifikasi();
        String pesan = notifikasi.getPesanNotifikasi();
        if (uniqueToken != null && uniqueToken.startsWith("ExponentPushToken")) {
            sendPush(uniqueToken, judul, pesan);
        }
        return new Result(200,"SUKSES",notifikasiRepository.save(notifikasi));
    }

    private void sendPush(String token, String title, String body) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> msg = new HashMap<>();
        msg.put("to", token);
        msg.put("title", title);
        msg.put("body", body);
        msg.put("sound", "default");

        HttpEntity<Map<String, Object>> req = new HttpEntity<>(msg, headers);
        rest.postForObject(EXPO_PUSH_URL, req, String.class);

        try {
            String res = rest.postForObject(EXPO_PUSH_URL, req, String.class);
            System.out.println("✅ Response dari Expo: " + res);
        } catch (Exception e) {
            System.out.println("🚨 Push notif gagal:");
            e.printStackTrace();
        }
    }

    public Result updateStatusNotifkasi(Notifikasi notifikasi) {
        Integer notif = notifikasi.getIdNotif();
        Notifikasi data = notifikasiRepository.findById(notif).get();
        data.setStatusDibaca(1);
        return new Result(200,"SUKSES",notifikasiRepository.save(data));
    }
    public Result totalNotifikasi(Notifikasi notifikasi) {
        String npk = notifikasi.getIdKaryawan();
        return new Result(200,"SUKSES",notifikasiRepository.countByNPK(npk));
    }
}
