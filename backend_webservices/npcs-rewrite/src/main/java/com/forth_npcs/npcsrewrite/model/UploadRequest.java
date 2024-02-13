package com.forth_npcs.npcsrewrite.model;

public class UploadRequest {
    private String repositoryUrl;
    private String filepath;

    // Getters and setters
    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String path) {
        this.filepath = path;
    }
}
