package com.workflowlens.controller;

import com.workflowlens.dto.WorkflowResponse;
import com.workflowlens.service.TeamService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }
    @GetMapping("/{teamId}/workflows")
    public List<WorkflowResponse> getTeamWorkflows(
            @PathVariable String teamId) {

        return teamService.getTeamWorkflows(teamId);
    }
}
