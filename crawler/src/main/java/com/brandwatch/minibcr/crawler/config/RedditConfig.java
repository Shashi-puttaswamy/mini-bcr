package com.brandwatch.minibcr.crawler.config;

import com.brandwatch.minibcr.crawler.service.reddit.auth.RedditClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedditConfig {
    static private final String CLIENT_ID = "Fll4jWWRYjJKYYZUHu6rSQ";
    static private final String CLIENT_SECRET = "1A0gdRtSkCI31qumAMIRRIeSCql2tw";

    static private final String CLIENT_NAME = "bw-crawler-test";

    @Bean
    public RedditClient redditClient() {
        return new RedditClient(CLIENT_ID,CLIENT_SECRET,CLIENT_NAME);
    }
}
