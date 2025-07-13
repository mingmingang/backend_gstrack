package com.astratech.backend_gstrack.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/service")
public class ServerResponseController {
    @GetMapping("/shoot")
    public ResponseEntity<String> shoot()
    {
        return ResponseEntity.ok("Connection OK");
    }
}
