package com.brandwatch.minibcr.crawler.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brandwatch.minibcr.crawler.service.CrawlerService;
import com.brandwatch.minibcr.crawler.service.reddit.RedditCrawler;

@RestController
@RequestMapping("/crawler/reddit/")
public class RedditCrawlerController {

    private final RedditCrawler redditCrawler;

    public RedditCrawlerController(RedditCrawler redditCrawler) {
        this.redditCrawler = redditCrawler;
    }

    @PostMapping
    public void startCrawl() {
        new CrawlerService(redditCrawler).crawl();
    }

}
