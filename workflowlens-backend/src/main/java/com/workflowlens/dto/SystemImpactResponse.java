package com.workflowlens.dto;

import java.util.List;

public class SystemImpactResponse {
    private SystemInfo system;
    private List<WorkflowInfo> affectedWorkflows;
    private List<TeamInfo> affectedTeams;
    public SystemImpactResponse(SystemInfo system, List<WorkflowInfo> affectedWorkflows, List<TeamInfo> affectedTeams) {
        this.system = system;
        this.affectedWorkflows = affectedWorkflows;
        this.affectedTeams = affectedTeams;
    }

    public SystemInfo getSystem() {
        return system;
    }

    public List<WorkflowInfo> getAffectedWorkflows() {
        return affectedWorkflows;
    }

    public List<TeamInfo> getAffectedTeams() {
        return affectedTeams;
    }
    public record SystemInfo(
            String id,
            String name) {
    }

    public record WorkflowInfo(
            String id,
            String name) {
    }

    public record TeamInfo(
            String id,
            String name) {
    }
}
