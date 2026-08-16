package com.workflowlens.controller;

import com.workflowlens.dto.WorkflowDependencyResponse;
import com.workflowlens.service.WorkflowService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/workflows")
public class WorkflowController {
    private final WorkflowService workflowService;

    public WorkflowController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }
    @GetMapping("/{workflowId}/dependencies")
    public WorkflowDependencyResponse getWorkflowDependencies(
            @PathVariable String workflowId) {

        return workflowService.getWorkflowDependencies(workflowId);
    }

}
