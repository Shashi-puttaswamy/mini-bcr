package com.brandwatch.minibcr.crawler.service.reddit;

import java.util.Collections;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.brandwatch.minibcr.common.model.Resource;
import com.brandwatch.minibcr.crawler.model.reddit.Subreddit;
import com.brandwatch.minibcr.crawler.service.Crawler;
import com.brandwatch.minibcr.crawler.service.reddit.auth.RedditAuthenticator;
import com.brandwatch.minibcr.crawler.service.reddit.auth.RedditClient;


@Service
public class RedditCrawler implements Crawler {

    private static final String REDDIT_URL = "https://oauth.reddit.com/r/phone/new?limit=100";

    private final RedditAuthenticator authenticator;
    private final RedditClient redditClient;

    private final RestTemplate restTemplate;

    private final Producer producer;


    public RedditCrawler(
            RedditAuthenticator authenticator,
            RedditClient redditClient,
            RestTemplate restTemplate,
            Producer producer
    ) {
        this.authenticator = authenticator;
        this.redditClient = redditClient;
        this.restTemplate = restTemplate;
        this.producer = producer;
    }

    @Override
    public void crawl() {
        String token = authenticator.authenticate(redditClient);
        HttpEntity<String> redditEntity = getRedditEntity(token);
        ResponseEntity<Subreddit> response
                = restTemplate.exchange(REDDIT_URL, HttpMethod.GET, redditEntity,
                new ParameterizedTypeReference<Subreddit>() {
                });
        if (response.getBody() == null) {
            return;
        }
        sendToKafka(response.getBody());
    }

    private HttpEntity<String> getRedditEntity(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.put("User-Agent",
                Collections.singletonList(redditClient.getClientName()));
        return new HttpEntity<>("parameters", headers);
    }

    private void sendToKafka(Subreddit subReddit) {
        for (Subreddit childSubreddit : subReddit.getData().getChildren()) {
            String title = childSubreddit.getData().getTitle();
            String body = childSubreddit.getData().getSelftext();
            Resource resource = new Resource(title, body);
            if(title != null || body != null) {
                producer.sendMessage(resource);
            }
        }
    }

}
