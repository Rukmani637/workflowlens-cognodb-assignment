package com.workflowlens.service;

import com.workflowlens.dto.WorkflowResponse;
import com.workflowlens.repository.GraphRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private final GraphRepository graphRepository;

    public TeamService(GraphRepository graphRepository) {
        this.graphRepository = graphRepository;
    }
        public List<WorkflowResponse> getTeamWorkflows(String teamId) {

            return graphRepository.findWorkflowsByTeam(teamId);

    }


}
