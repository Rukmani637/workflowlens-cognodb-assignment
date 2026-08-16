package com.workflowlens.service;

import com.workflowlens.dto.WorkflowDependencyResponse;
import com.workflowlens.repository.GraphRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkflowService {
    private final GraphRepository graphRepository;

    public WorkflowService(GraphRepository graphRepository) {
        this.graphRepository = graphRepository;
    }

    public WorkflowDependencyResponse getWorkflowDependencies(
            String workflowId) {
        return graphRepository.findWorkflowDependencies(workflowId);
    }
}
