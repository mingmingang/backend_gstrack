package com.astratech.backend_gstrack.Controller;

import com.astratech.backend_gstrack.Service.NotifikasiService;
import com.astratech.backend_gstrack.VO.Notifikasi;
import com.astratech.backend_gstrack.VO.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifikasi")
@CrossOrigin
public class NotifikasiController {
    private final NotifikasiService service;
    public NotifikasiController(NotifikasiService service) {
        this.service = service;
    }

    @PostMapping("/getAll")
    public Result getAll(@RequestBody Notifikasi notifikasi) {
        return service.getNotifikasi(notifikasi);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Notifikasi notifikasi) {
        return service.saveNotifkasi(notifikasi);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Notifikasi notifikasi) {
        return service.updateStatusNotifkasi(notifikasi);
    }
    @PostMapping("/count")
    public Result count(@RequestBody Notifikasi notifikasi) {
        return service.totalNotifikasi(notifikasi);
    }
}
