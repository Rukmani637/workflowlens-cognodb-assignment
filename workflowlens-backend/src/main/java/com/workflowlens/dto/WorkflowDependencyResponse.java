package com.workflowlens.dto;

import java.util.List;

public class WorkflowDependencyResponse {
    private WorkflowInfo workflow;
    private List<AgentInfo> agents;
    private List<SkillInfo> skills;
    private List<ConnectorInfo> connectors;
    private List<SystemInfo> systems;
    private List<RelationshipInfo> relationships;

    public WorkflowDependencyResponse(
            WorkflowInfo workflow,
            List<AgentInfo> agents,
            List<SkillInfo> skills,
            List<ConnectorInfo> connectors,
            List<SystemInfo> systems,
            List<RelationshipInfo> relationships) {

        this.workflow = workflow;
        this.agents = agents;
        this.skills = skills;
        this.connectors = connectors;
        this.systems = systems;
        this.relationships = relationships;
    }

    public WorkflowInfo getWorkflow() {
        return workflow;
    }

    public List<AgentInfo> getAgents() {
        return agents;
    }

    public List<SkillInfo> getSkills() {
        return skills;
    }

    public List<ConnectorInfo> getConnectors() {
        return connectors;
    }

    public List<SystemInfo> getSystems() {
        return systems;
    }

    public List<RelationshipInfo> getRelationships() {
        return relationships;
    }

    public record WorkflowInfo(
            String id,
            String name) {
    }

    public record AgentInfo(
            String id,
            String name) {
    }

    public record SkillInfo(
            String id,
            String name) {
    }

    public record ConnectorInfo(
            String id,
            String name) {
    }

    public record SystemInfo(
            String id,
            String name) {
    }

    public record RelationshipInfo(
            String from,
            String to,
            String type) {
    }
}