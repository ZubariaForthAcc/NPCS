package com.forth_npcs.npcsrewrite.model;
public class QueryRequest {
    private String repositoryUrl;
    private String query;

    // Getters and setters
    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
