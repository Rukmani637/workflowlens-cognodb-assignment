package com.workflowlens.controller;

import com.workflowlens.service.SeedService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seed")
public class SeedController {
    private final SeedService seedService;

    public SeedController(SeedService seedService) {
        this.seedService = seedService;
    }

    @PostMapping
    public String seedDatabase() {
        seedService.seedDatabase();
        return "WorkflowLens demo graph seeded successfully";
    }

}


