package com.astratech.backend_gstrack.Controller;

import com.astratech.backend_gstrack.Request.DateRange;
import com.astratech.backend_gstrack.Service.KehadiranService;
import com.astratech.backend_gstrack.VO.Kehadiran;
import com.astratech.backend_gstrack.VO.Result;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/save")
    public Result saveKehadiran(@RequestBody Kehadiran kehadiran)
    {
        return kehadiranService.saveKehadiran(kehadiran);
    }
}
