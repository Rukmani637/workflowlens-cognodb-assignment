package com.workflowlens.service;

import com.workflowlens.dto.SystemImpactResponse;
import com.workflowlens.repository.GraphRepository;
import org.springframework.stereotype.Service;

@Service
public class SystemService {
    private final GraphRepository graphRepository;

    public SystemService(GraphRepository graphRepository) {
        this.graphRepository = graphRepository;
    }

    public SystemImpactResponse getSystemImpact(String systemId) {

        return graphRepository.findSystemImpact(systemId);
    }
}

