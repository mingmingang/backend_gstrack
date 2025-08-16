package com.astratech.backend_gstrack.Repository;

import com.astratech.backend_gstrack.VO.Cuti;
import com.astratech.backend_gstrack.VO.Lembur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("LemburRepository")
public interface LemburRepository extends JpaRepository<Lembur, Serializable> {
    Lembur findBylbrId(String lbrId);
    List<Lembur> findByLbrStatusIgnoreCase(String status);
}
