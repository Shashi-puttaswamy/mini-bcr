package com.brandwatch.minibcr.crawler.model.reddit;

import java.util.List;

public class RedditListingData {
    private List<RedditListingChild> children;

    public List<RedditListingChild> getChildren() {
        return children;
    }

    public void setChildren(List<RedditListingChild> children) {
        this.children = children;
    }
}
