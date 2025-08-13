package com.astratech.backend_gstrack.Controller;

import com.astratech.backend_gstrack.Service.IDLService;
import com.astratech.backend_gstrack.VO.IDL;
import com.astratech.backend_gstrack.VO.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class IDLController {
    @Autowired
    private IDLService idlService;

    @GetMapping("/IDL")
    public List<IDL> getAllIDL() { return idlService.getAllIDL(); }

    @GetMapping("/IDL/{id}")
    public IDL getIDLbyIdlNoRequest(@PathVariable("id") String idlNoRequest) { return idlService.getIDLbyIdlNoRequest(idlNoRequest); }

    @GetMapping("/IDL/available-years")
    public ResponseEntity<List<Integer>> getAvailableYears(@RequestParam String idlNpk) {
        List<Integer> years = idlService.getAvailableYearsByNpk(idlNpk); // hasilnya harus List<Integer>
        return ResponseEntity.ok(years);
    }

    @GetMapping("/IDL/available-years-all")
    public ResponseEntity<List<Integer>> getAllAvailableYears() {
        List<Integer> years = idlService.getAllAvailableYears();
        return ResponseEntity.ok(years);
    }

    @GetMapping("/IDL/karyawan")
    public List<IDL> getIDLbyIdlNpk(
            @RequestParam("idlNpk") String idlNpk,
            @RequestParam(value = "status", required = false) String idlStatus,
            @RequestParam(value = "year", required = false) Integer year
    ) {
        boolean hasStatus = idlStatus != null && !idlStatus.isEmpty();
        boolean hasYear = year != null;

        if (hasStatus && hasYear) {
            return idlService.getIDLbyIdlNpkAndIdlStatusAndIdlCreatedDate(idlNpk, idlStatus, year);
        } else if (hasStatus) {
            return idlService.getIDLbyIdlNpkAndIdlStatus(idlNpk, idlStatus);
        } else if (hasYear) {
            return idlService.getIDLbyIdlNpkAndIdlCreatedDate(idlNpk, year);
        } else {
            return idlService.getIDLbyIdlNpk(idlNpk);
        }
    }

    @GetMapping("/IDL/year")
    public List<IDL> getIDLByYear(@RequestParam(value = "year", required = false) Integer year) {
        if (year == null) {
            // Misalnya default ke tahun sekarang
            year = java.time.Year.now().getValue();
        }
        return idlService.getIDLByYear(year);
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

    @PutMapping("/updateIDL")
    public Object updateIDL(HttpServletResponse response, @RequestBody IDL idl) {
        IDL existingIDL = idlService.getIDLbyIdlNoRequest(idl.getIdlNoRequest());

        if (existingIDL == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new Result(404, "Data IDL tidak ditemukan");
        }

        // Update field-field yang bisa dimodifikasi
        existingIDL.setIdlJenisKegiatan(idl.getIdlJenisKegiatan());
        existingIDL.setIdlTanggalBerangkat(idl.getIdlTanggalBerangkat());
        existingIDL.setIdlWaktuBerangkat(idl.getIdlWaktuBerangkat());
        existingIDL.setIdlTanggalKembali(idl.getIdlTanggalKembali());
        existingIDL.setIdlWaktuKembali(idl.getIdlWaktuKembali());
        existingIDL.setIdlLokasiPertama(idl.getIdlLokasiPertama());
        existingIDL.setIdlLokasiKedua(idl.getIdlLokasiKedua());
        existingIDL.setIdlLokasiKetiga(idl.getIdlLokasiKetiga());
        existingIDL.setIdlKeterangan(idl.getIdlKeterangan());
        existingIDL.setIdlBerkasLampiran(idl.getIdlBerkasLampiran());
        existingIDL.setIdlBerkasLampiranName(idl.getIdlBerkasLampiranName());
        existingIDL.setIdlStatus(idl.getIdlStatus());

        // Set modif info
        existingIDL.setIdlModifBy(idl.getIdlModifBy());
        existingIDL.setIdlModifDate(java.time.LocalDateTime.now());

        boolean isSuccess = idlService.saveIDL(existingIDL);
        if (isSuccess) {
            return new Result(200, "IDL berhasil diperbarui");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Gagal memperbarui IDL");
        }
    }

    @PutMapping("/IDL/berangkat")
    public ResponseEntity<?> updateBerangkat(@RequestParam String idlNoRequest) {
        IDL idl = idlService.getIDLbyIdlNoRequest(idlNoRequest);
        if (idl == null) {
            return ResponseEntity.notFound().build();
        }
        idl.setIdlBerangkat(LocalDateTime.now());
        idlService.saveIDL(idl);
        return ResponseEntity.ok("Berangkat time updated");
    }


    @PutMapping("/IDL/pulang")
    public ResponseEntity<?> updatePulang(@RequestParam String idlNoRequest) {
        IDL idl = idlService.getIDLbyIdlNoRequest(idlNoRequest);
        if (idl == null) {
            return ResponseEntity.notFound().build();
        }
        idl.setIdlPulang(LocalDateTime.now());
        idlService.saveIDL(idl);
        return ResponseEntity.ok("Pulang time updated");
    }

    @GetMapping("/IDL/latest/karyawan")
    public IDL getLatestIdlByKaryawan(@RequestParam("idlNpk") String idlNpk) {
        return idlService.getLatestIdlByNpk(idlNpk).orElse(null);
    }

}
