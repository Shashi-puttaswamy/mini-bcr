package com.brandwatch.minibcr.crawler.service.reddit;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.brandwatch.minibcr.crawler.model.reddit.Subreddit;
import com.brandwatch.minibcr.crawler.service.reddit.auth.RedditAuthenticator;
import com.brandwatch.minibcr.crawler.service.reddit.auth.RedditClient;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RedditCrawlerTest {


    @Mock
    private RedditAuthenticator redditAuthenticator;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private RedditClient redditClient;

    @Mock
    private Producer producer;


    @Test
    public final void testCrawl() {
        RedditCrawler redditCrawler = new RedditCrawler(redditAuthenticator, redditClient, restTemplate, producer);
        when(redditAuthenticator.authenticate(Mockito.any())).thenReturn("test");
        when(redditClient.getClientName()).thenReturn("test");
        ResponseEntity<Subreddit> subRedditResponseEntity = new ResponseEntity<>(getSubredditTestData(), HttpStatus.OK);
        when(restTemplate.exchange(Mockito.anyString(),
                        Mockito.any(), Mockito.any(),
                        Mockito.eq(new ParameterizedTypeReference<Subreddit>() {
                        })))
                .thenReturn(subRedditResponseEntity);
        redditCrawler.crawl();
        verify(redditClient, times(1)).getClientName();
    }

    private Subreddit getSubredditTestData() {
        Subreddit child = new Subreddit();
        child.setSelftext("test").setTittle("test");
        Subreddit parentSubreddit = new Subreddit();
        Subreddit dataSubreddit = new Subreddit();
        dataSubreddit.setChildren(Collections.singletonList(child));
        parentSubreddit.setData(dataSubreddit);
        return parentSubreddit;
    }
}
