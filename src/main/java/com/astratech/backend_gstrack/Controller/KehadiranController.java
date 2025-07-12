package com.astratech.backend_gstrack.Controller;

import com.astratech.backend_gstrack.Request.DateRange;
import com.astratech.backend_gstrack.Service.KehadiranService;
import com.astratech.backend_gstrack.VO.Kehadiran;
import com.astratech.backend_gstrack.VO.Result;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@RestController
@RequestMapping("/kehadiran")
@CrossOrigin
public class KehadiranController {
    private final KehadiranService kehadiranService;
    public KehadiranController(KehadiranService kehadiranService)
    {
        this.kehadiranService = kehadiranService;
    }
    @PostMapping("/get")
    public Result getKehadiran(@RequestBody DateRange range){
        return kehadiranService.getKehadiran(range.getStartDate(),range.getEndDate());
    }
    @PostMapping("/checkin")
    public Result checkin(
            @RequestPart("data") Kehadiran kehadiran,
            @RequestPart("foto") MultipartFile foto
    ) {
        return kehadiranService.checkIn(kehadiran, foto);
    }

    @PostMapping("/checkout")
    public Result checkout(@RequestParam("foto") MultipartFile foto, @RequestParam("kryNpk") String npk, @RequestParam("tanggal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tanggal) {
        return kehadiranService.checkOut(npk, tanggal, foto);
    }

}
