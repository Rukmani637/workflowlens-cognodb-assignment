package com.workflowlens.dto;

public class SearchResultResponse {
    private String type;
    private String id;
    private String name;

    public SearchResultResponse(
            String type,
            String id,
            String name) {

        this.type = type;
        this.id = id;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
