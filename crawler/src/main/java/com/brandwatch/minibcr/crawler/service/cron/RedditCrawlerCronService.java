package com.brandwatch.minibcr.crawler.service.cron;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.brandwatch.minibcr.crawler.service.reddit.RedditCrawler;

@Service
public class RedditCrawlerCronService {

    private final RedditCrawler redditCrawler;

    public RedditCrawlerCronService(RedditCrawler redditCrawler) {
        this.redditCrawler = redditCrawler;
    }

    @Scheduled(cron = "0 0 8 * * *")
    public void crawlResource() {
        redditCrawler.crawl();
    }
}
