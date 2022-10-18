package com.brandwatch.minibcr.crawler.service.reddit;

import com.brandwatch.minibcr.crawler.model.Resource;
import com.brandwatch.minibcr.crawler.service.Crawler;
import com.brandwatch.minibcr.crawler.service.reddit.auth.RedditAuthenticator;
import com.brandwatch.minibcr.crawler.service.reddit.auth.RedditClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class RedditCrawler implements Crawler {

    private static final String SUBREDDIT_NAME = "phone";

    private final RedditAuthenticator authenticator;
    private final RedditClient redditClient;

    private final RestTemplate restTemplate;


    public RedditCrawler(RedditAuthenticator authenticator, RedditClient redditClient, RestTemplate restTemplate) {
        this.authenticator = authenticator;
        this.redditClient = redditClient;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Resource> crawl() {
        String token = authenticator.authenticate(redditClient);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.put("User-Agent",
                Collections.singletonList(redditClient.getClientName()));
        //TODO SubReddit Object and Map to Resource object serialize and send to queue.
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        String url = "https://oauth.reddit.com/r/"+ SUBREDDIT_NAME + "/new?limit=100";
        ResponseEntity<Map<String,Object>> response
                = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<Map<String,Object>>() {
        });
        return null;
    }

}
