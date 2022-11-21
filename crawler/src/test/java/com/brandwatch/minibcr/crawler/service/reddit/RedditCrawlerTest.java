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

import com.brandwatch.minibcr.crawler.model.reddit.RedditListingChild;
import com.brandwatch.minibcr.crawler.model.reddit.RedditListingChildData;
import com.brandwatch.minibcr.crawler.model.reddit.RedditListingData;
import com.brandwatch.minibcr.crawler.model.reddit.RedditListingResponse;
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
    public final void crawler_shouldGetRedditData_whenAuthenticated() {
        RedditCrawler redditCrawler = new RedditCrawler(redditAuthenticator, redditClient, restTemplate, producer);
        when(redditAuthenticator.authenticate(Mockito.any())).thenReturn("test");
        when(redditClient.getClientName()).thenReturn("test");
        ResponseEntity<RedditListingResponse> subRedditResponseEntity = new ResponseEntity<>(getSubredditTestData(), HttpStatus.OK);
        when(restTemplate.exchange(Mockito.anyString(),
                Mockito.any(), Mockito.any(),
                Mockito.eq(new ParameterizedTypeReference<RedditListingResponse>() {
                })))
                .thenReturn(subRedditResponseEntity);
        redditCrawler.crawl();
        verify(redditClient, times(1)).getClientName();
    }

    private RedditListingResponse getSubredditTestData() {
        RedditListingChildData childData = new RedditListingChildData();
        childData.setSelftext("test").setTitle("test");
        RedditListingChild parentSubreddit = new RedditListingChild();
        parentSubreddit.setData(childData);
        RedditListingData redditListingData = new RedditListingData();
        redditListingData.setChildren(Collections.singletonList(parentSubreddit));
        RedditListingResponse redditListingResponse = new RedditListingResponse();
        redditListingResponse.setData(redditListingData);
        return redditListingResponse;
    }
}
