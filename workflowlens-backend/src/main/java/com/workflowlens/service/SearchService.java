package com.workflowlens.service;

import com.workflowlens.dto.SearchResultResponse;
import com.workflowlens.repository.GraphRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private final GraphRepository graphRepository;

    public SearchService(GraphRepository graphRepository) {
        this.graphRepository = graphRepository;
    }

    public List<SearchResultResponse> search(String query) {

        return graphRepository.search(query);
    }
}
