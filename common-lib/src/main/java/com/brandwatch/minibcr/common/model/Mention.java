package com.brandwatch.minibcr.common.model;

public class Mention {

    private String id;
    private long queryId;
    private Resource resource;

    public Mention() {
    }

    public Mention(long queryId, Resource resource) {
        this.queryId = queryId;
        this.resource = resource;
    }

    public long getQueryId() {
        return queryId;
    }

    public Resource getResource() {
        return resource;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
