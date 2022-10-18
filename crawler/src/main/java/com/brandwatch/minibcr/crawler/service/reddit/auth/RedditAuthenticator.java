package com.brandwatch.minibcr.crawler.service.reddit.auth;

import com.brandwatch.minibcr.crawler.config.RestConfig;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@Service
public class RedditAuthenticator {
    private static final String URL = "https://www.reddit.com/api/v1/access_token";

    private final RestTemplate restTemplate;

    public RedditAuthenticator(RestConfig config) {
        this.restTemplate = config.restTemplate();
    }

    public String authenticate(RedditClient client) {
        return getAuthToken(client);
    }

    private String getAuthToken(RedditClient client) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(client.getClientId(), client.getSecret());
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.put("User-Agent", Collections.singletonList(client.getClientName()));
        String body = "grant_type=client_credentials";
        HttpEntity<String> request
                = new HttpEntity<>(body, headers);
        ResponseEntity<Map<String,Object>> response
                = restTemplate.exchange(URL, HttpMethod.POST, request, new ParameterizedTypeReference<Map<String,Object>>() {
        });
        return (String) Objects.requireNonNull(response.getBody()).get("access_token");
    }

}
