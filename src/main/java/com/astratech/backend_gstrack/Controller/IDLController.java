package com.astratech.backend_gstrack.Controller;

import com.astratech.backend_gstrack.Service.IDLService;
import com.astratech.backend_gstrack.VO.IDL;
import com.astratech.backend_gstrack.VO.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IDLController {
    @Autowired
    private IDLService idlService;

    @GetMapping("/IDL")
    public List<IDL> getAllIDL() { return idlService.getAllIDL(); }

    @GetMapping("/IDL/{id}")
    public IDL getIDLbyIdlNoRequest(@PathVariable("id") String idlNoRequest) { return idlService.getIDLbyIdlNoRequest(idlNoRequest); }

    @GetMapping("/IDL/karyawan")
    public List<IDL> getIDLbyIdlNpk(
            @RequestParam("idlNpk") String idlNpk,
            @RequestParam(value = "status", required = false) String idlStatus,
            @RequestParam(value = "year", required = false) Integer year
    ) {
        if (idlStatus != null && idlStatus.isEmpty()
        && year != null && year < 2020) {
            return idlService.getIDLbyIdlNpkAndIdlStatusAndIdlCreatedDate(idlNpk, idlStatus, year);
        } else if (idlStatus != null && idlStatus.isEmpty()) {
            return idlService.getIDLbyIdlNpkAndIdlStatus(idlNpk, idlStatus);
        } else if (year != null && year < 2020) {
            return idlService.getIDLbyIdlNpkAndIdlCreatedDate(idlNpk, year);
        } else {
            return idlService.getIDLbyIdlNpk(idlNpk);
        }
    }

    @PostMapping("/IDL")
    public Object saveIDL(HttpServletResponse response, @RequestBody IDL idl) {
        boolean isSuccess = idlService.saveIDL(idl);
        if (isSuccess) {
            return new Result(200, "IDL berhasil disimpan");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Gagal menyimpan IDL");
        }
    }
}
