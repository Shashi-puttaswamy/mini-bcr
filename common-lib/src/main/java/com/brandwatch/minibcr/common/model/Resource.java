package com.brandwatch.minibcr.common.model;

import java.time.Instant;

public class Resource {

    private final String title;
    private final String text;
    private final long createdTime;

    public Resource(String title, String text) {
        this.title = title;
        this.text = text;
        this.createdTime = Instant.now().toEpochMilli();
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public long getCreatedTime() {
        return createdTime;
    }
}
