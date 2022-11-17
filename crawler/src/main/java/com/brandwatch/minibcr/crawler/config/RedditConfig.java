package com.brandwatch.minibcr.crawler.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.brandwatch.minibcr.crawler.service.reddit.auth.RedditClient;


@Configuration
public class RedditConfig {

    @Value("${reddit.client.id}")
    private String clientId;
    @Value("${reddit.client.secret}")
    private String clientSecret;
    @Value("${reddit.client.name}")
    private String clientName;

    @Bean
    public RedditClient redditClient() {
        return new RedditClient(clientId, clientSecret, clientName);
    }
}
