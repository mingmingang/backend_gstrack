package com.astratech.backend_gstrack.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class IPController {

    @GetMapping("/ip")
    public Map<String, String> getLocalIp() throws UnknownHostException {
        String ip = InetAddress.getLocalHost().getHostAddress();
        Map<String, String> response = new HashMap<>();
        response.put("ip", ip);
        return response;
    }
}
