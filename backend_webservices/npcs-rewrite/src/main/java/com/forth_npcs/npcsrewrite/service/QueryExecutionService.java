package com.forth_npcs.npcsrewrite.service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class QueryExecutionService {

    private final RestTemplate restTemplate;

    public QueryExecutionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String executeQuery(String repositoryUrl, String query) {
        String queryUrl = UriComponentsBuilder.fromUriString(repositoryUrl)
                .queryParam("query", query)
                .toUriString();

        try {
            // Execute the query
            return restTemplate.getForObject(queryUrl, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error executing query: " + e.getMessage();
        }
    }
}
