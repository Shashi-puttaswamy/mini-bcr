package com.brandwatch.minibcr.crawler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.brandwatch.minibcr.crawler.service.reddit.auth.RedditClient;


@Configuration
public class RedditConfig {

    private static final String CLIENT_ID = "Fll4jWWRYjJKYYZUHu6rSQ";
    private static final String CLIENT_SECRET = "1A0gdRtSkCI31qumAMIRRIeSCql2tw";

    private static final String CLIENT_NAME = "bw-crawler-test";

    @Bean
    public RedditClient redditClient() {
        return new RedditClient(CLIENT_ID, CLIENT_SECRET, CLIENT_NAME);
    }
}
