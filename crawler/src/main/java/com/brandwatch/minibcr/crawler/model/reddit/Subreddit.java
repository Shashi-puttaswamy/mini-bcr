package com.brandwatch.minibcr.crawler.model.reddit;

import java.util.List;

public class Subreddit {

    private Subreddit data;
    private List<Subreddit> children;

    private String selftext;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setData(Subreddit data) {
        this.data = data;
    }

    public void setChildren(List<Subreddit> children) {
        this.children = children;
    }

    public Subreddit getData() {
        return data;
    }

    public List<Subreddit> getChildren() {
        return children;
    }

    public String getSelftext() {
        return selftext;
    }

    public Subreddit setSelftext(String selftext) {
        this.selftext = selftext;
        return this;
    }
}
