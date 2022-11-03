package com.brandwatch.minibcr.crawler.model.reddit;

import java.util.List;

public class Subreddit {

    private Subreddit data;
    private List<Subreddit> children;

    private String selftext;

    private String tittle;

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
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
