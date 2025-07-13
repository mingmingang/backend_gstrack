package com.astratech.backend_gstrack.Repository;

import com.astratech.backend_gstrack.VO.RumahSakit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RumahSakitRepository extends JpaRepository<RumahSakit, Integer> {

}