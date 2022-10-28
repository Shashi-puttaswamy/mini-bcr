package com.brandwatch.minibcr.mentiongenerator.model;

import com.brandwatch.minibcr.common.model.Resource;

public class Mention {

    private final long queryId;
    private final Resource resource;

    public Mention(long queryId, Resource resource) {
        this.queryId = queryId;
        this.resource = resource;
    }
}
