package com.astratech.backend_gstrack.Controller;

import com.astratech.backend_gstrack.Service.TokenService;
import com.astratech.backend_gstrack.VO.Result;
import com.astratech.backend_gstrack.VO.Token;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@CrossOrigin
public class TokenController {
    private final TokenService tokenService;
    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    @PostMapping("/register-token")
    public Result saveToken(Token token) {
        return tokenService.saveToken(token);
    }
}
