package com.example.accesskeybackend.template.controller;

import com.example.accesskeybackend.template.service.WebService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/web/checkIpv6Support")
@AllArgsConstructor
public class WebPublicController {

    private final WebService webService;

    @GetMapping
    public Map<String, Boolean> check(
            @RequestParam(required = true) final String siteUrl
    ) {
        return Collections.singletonMap("success", webService.isIPv6(siteUrl));
    }
}
