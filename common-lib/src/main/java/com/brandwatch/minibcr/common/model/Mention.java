package com.brandwatch.minibcr.common.model;

public class Mention {

    private final long queryId;
    private final Resource resource;

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
}
