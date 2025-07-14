package com.astratech.backend_gstrack.Repository;

import com.astratech.backend_gstrack.VO.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import static com.astratech.backend_gstrack.Constant.TokenConstant.qToken;

public interface TokenRepositry extends JpaRepository<Token, Integer> {
    @Query(value = qToken,nativeQuery = true)
    Token findByKryNpk(String kryNpk);
}
