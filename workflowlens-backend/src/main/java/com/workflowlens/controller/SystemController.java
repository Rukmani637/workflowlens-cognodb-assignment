package com.workflowlens.controller;

import com.workflowlens.dto.SystemImpactResponse;
import com.workflowlens.service.SystemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/systems")
public class SystemController {
    private final SystemService systemService;

    public SystemController(SystemService systemService) {
        this.systemService = systemService;
    }

    @GetMapping("/{systemId}/impact")
    public SystemImpactResponse getSystemImpact(
            @PathVariable String systemId) {

        return systemService.getSystemImpact(systemId);
    }
}
