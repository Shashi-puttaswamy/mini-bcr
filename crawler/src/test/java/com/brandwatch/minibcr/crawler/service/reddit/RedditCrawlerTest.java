package com.brandwatch.minibcr.crawler.service.reddit;

import com.brandwatch.minibcr.crawler.model.reddit.SubReddit;
import com.brandwatch.minibcr.crawler.service.reddit.auth.RedditAuthenticator;
import com.brandwatch.minibcr.crawler.service.reddit.auth.RedditClient;
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

import java.util.Collections;

import static org.mockito.Mockito.times;

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
    public final void crawl() {
        RedditCrawler redditCrawler = new RedditCrawler(redditAuthenticator,redditClient,restTemplate,producer);
        Mockito.when(redditAuthenticator.authenticate(Mockito.any())).thenReturn("test");
        Mockito.when(redditClient.getClientName()).thenReturn("test");
        ResponseEntity<SubReddit> subRedditResponseEntity = new ResponseEntity<>(getSubredditTestData(), HttpStatus.OK);
        Mockito.when(restTemplate.exchange(Mockito.anyString(),
                Mockito.any(),Mockito.any(),
                        Mockito.eq(new ParameterizedTypeReference<SubReddit>() {})))
                .thenReturn(subRedditResponseEntity);
        redditCrawler.crawl();
        Mockito.verify(redditClient, times(1)).getClientName();
    }

    private SubReddit getSubredditTestData() {
        SubReddit child = new SubReddit();
        child.setSelftext("test").setTitle("test");
        SubReddit parentSubreddit = new SubReddit();
        SubReddit dataSubreddit = new SubReddit();
        dataSubreddit.setChildren(Collections.singletonList(child));
        parentSubreddit.setData(dataSubreddit);
        return parentSubreddit;
    }
}
