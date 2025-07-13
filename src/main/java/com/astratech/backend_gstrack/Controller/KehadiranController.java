package com.astratech.backend_gstrack.Controller;

import com.astratech.backend_gstrack.Request.DateRange;
import com.astratech.backend_gstrack.Service.KehadiranService;
import com.astratech.backend_gstrack.VO.Kehadiran;
import com.astratech.backend_gstrack.VO.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
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
    @PostMapping( value = "/checkin", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result checkin(@RequestPart("data") String data, @RequestPart("foto") MultipartFile foto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Kehadiran kehadiran = mapper.readValue(data, Kehadiran.class);

        return kehadiranService.checkIn(kehadiran, foto);
    }

    @PostMapping(value = "/checkout", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result checkout(@RequestParam("foto") MultipartFile foto, @RequestParam("data") String data, @RequestParam("tanggal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tanggal) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Kehadiran kehadiran = mapper.readValue(data, Kehadiran.class);
        return kehadiranService.checkOut(kehadiran, tanggal, foto);
    }

    @PostMapping("/currenthadir")
    public Result getCurrenthadir(@RequestBody Kehadiran kehadiran) {
        return kehadiranService.currentSessionKehadiran(kehadiran);
    }
    @PostMapping("/currentlogged")
    public Result getCurrentLogged(@RequestBody Kehadiran kehadiran) {
        return kehadiranService.currentLoggedKehadiran(kehadiran);
    }
}
