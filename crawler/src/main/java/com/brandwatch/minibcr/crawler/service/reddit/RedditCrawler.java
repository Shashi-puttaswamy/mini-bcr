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
import com.brandwatch.minibcr.crawler.model.reddit.SubReddit;
import com.brandwatch.minibcr.crawler.service.Crawler;
import com.brandwatch.minibcr.crawler.service.reddit.auth.RedditAuthenticator;
import com.brandwatch.minibcr.crawler.service.reddit.auth.RedditClient;


@Service
public class RedditCrawler implements Crawler {

    private static final String SUBREDDIT_NAME = "phone";
    private static final String REDDIT_URL = "https://oauth.reddit.com/r/%s/new?limit=100";

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
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.put("User-Agent",
                Collections.singletonList(redditClient.getClientName()));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<SubReddit> response
                = restTemplate.exchange(getRedditUrl(), HttpMethod.GET, entity,
                new ParameterizedTypeReference<SubReddit>() {
                });
        if (response.getBody() == null) {
            return;
        }
        sendToKafka(response.getBody());
    }

    private void sendToKafka(SubReddit subReddit) {
        for (SubReddit childSubreddit : subReddit.getData().getChildren()) {
            SubReddit subredditData = childSubreddit.getData();
            if (subredditData.getSelftext() != null || subredditData.getTitle()  != null ) {
            Resource resource = new Resource(subredditData.getTitle(), subredditData.getSelftext());
            producer.sendMessage(resource);
            }
        }
    }

    private String getRedditUrl() {
        return String.format(REDDIT_URL, SUBREDDIT_NAME);
    }

}
