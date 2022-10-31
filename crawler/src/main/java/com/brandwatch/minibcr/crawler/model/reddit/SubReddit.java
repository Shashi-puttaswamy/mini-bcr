package com.brandwatch.minibcr.crawler.model.reddit;

import java.util.List;

public class SubReddit {

    private SubReddit data;
    private List<SubReddit> children;

    private String selftext;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setData(SubReddit data) {
        this.data = data;
    }

    public void setChildren(List<SubReddit> children) {
        this.children = children;
    }

    public SubReddit getData() {
        return data;
    }

    public List<SubReddit> getChildren() {
        return children;
    }

    public String getSelftext() {
        return selftext;
    }

    public SubReddit setSelftext(String selftext) {
        this.selftext = selftext;
        return this;
    }
}
