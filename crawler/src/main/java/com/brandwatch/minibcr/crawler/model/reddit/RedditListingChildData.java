package com.brandwatch.minibcr.crawler.model.reddit;

public class RedditListingChildData {

    private String selftext;

    private String title;

    public String getSelftext() {
        return selftext;
    }

    public RedditListingChildData setSelftext(String selftext) {
        this.selftext = selftext;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
