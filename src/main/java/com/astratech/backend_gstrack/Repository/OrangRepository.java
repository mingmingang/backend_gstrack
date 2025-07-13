//OrangRepository
package com.astratech.backend_gstrack.Repository;

import com.astratech.backend_gstrack.VO.Orang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrangRepository extends JpaRepository<Orang, Integer> {

    List<Orang> findByKryNpk(String kryNpk);

}