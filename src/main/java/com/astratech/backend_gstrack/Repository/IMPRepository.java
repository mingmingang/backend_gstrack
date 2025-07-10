package com.astratech.backend_gstrack.Repository;

import com.astratech.backend_gstrack.VO.IMP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("IMPRepository")
public interface IMPRepository extends JpaRepository<IMP, Serializable> {
    IMP getIMPByImpId(int impId);
    List<IMP> findAllByOrderByImpIdDesc();

}
