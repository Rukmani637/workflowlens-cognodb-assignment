package com.workflowlens.dto;

public class WorkflowResponse {
    private String id;
    private String name;
    private String description;
    private String status;

    public WorkflowResponse() {
    }
    public WorkflowResponse(String id, String name, String description, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }
}
