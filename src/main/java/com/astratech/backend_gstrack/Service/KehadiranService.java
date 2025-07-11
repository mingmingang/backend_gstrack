package com.astratech.backend_gstrack.Service;

import com.astratech.backend_gstrack.Repository.KehadiranRepository;
import com.astratech.backend_gstrack.VO.Kehadiran;
import com.astratech.backend_gstrack.VO.Result;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@Transactional
public class KehadiranService {
    @Autowired private KehadiranRepository kehadiranRepository;
    public Result getKehadiran(Date startDate, Date endDate) {
        List<Kehadiran> kehadiranList =
                (startDate != null && endDate != null)
                        ? kehadiranRepository.FilterDate(startDate, endDate)
                        : kehadiranRepository.findAll();

        String message = kehadiranList.isEmpty() ? "No data available yet." : "SUKSES";
        return new Result(200, message, kehadiranList);
    }
    public Result saveKehadiran(Kehadiran kehadiran)
    {
        Kehadiran data = kehadiranRepository.save(kehadiran);
        return new Result(200,"SUKSES",data);
    }

}
