package com.brandwatch.minibcr.crawler.service;

import com.brandwatch.minibcr.crawler.model.Resource;

import java.util.List;

public class CrawlerService implements Crawler{

    private final Crawler crawler;

    public CrawlerService(Crawler crawler) {
        this.crawler = crawler;
    }

    public List<Resource> crawl(){
        return this.crawler.crawl();
    }
}
