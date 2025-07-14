package com.astratech.backend_gstrack.Service;

import com.astratech.backend_gstrack.Repository.TokenRepositry;
import com.astratech.backend_gstrack.VO.Result;
import com.astratech.backend_gstrack.VO.Token;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TokenService {
    @Autowired private TokenRepositry tokenRepositry;
    public Result saveToken(Token token) {
        return new Result(200,"SUKSES", tokenRepositry.save(token));
    }
}
