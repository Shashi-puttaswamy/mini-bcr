package com.brandwatch.minibcr.crawler.service;

public class CrawlerService implements Crawler {

    private final Crawler crawler;

    public CrawlerService(Crawler crawler) {
        this.crawler = crawler;
    }

    public void crawl() {
        this.crawler.crawl();
    }
}
