package com.brandwatch.minibcr.crawler.service.reddit.auth;

import java.util.Collections;
import java.util.Objects;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.brandwatch.minibcr.crawler.config.RestConfig;
import com.brandwatch.minibcr.crawler.model.reddit.Authentication;


@Service
public class RedditAuthenticator {

    private static final String URL = "https://www.reddit.com/api/v1/access_token";

    private final RestTemplate restTemplate;

    public RedditAuthenticator(RestConfig config) {
        this.restTemplate = config.restTemplate();
    }

    public String authenticate(RedditClient client) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(client.getClientId(), client.getSecret());
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.put("User-Agent", Collections.singletonList(client.getClientName()));
        String body = "grant_type=client_credentials";
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        ResponseEntity<Authentication> response = restTemplate.exchange(
                URL, HttpMethod.POST, request, new ParameterizedTypeReference<Authentication>() {
                });
        return Objects.requireNonNull(response.getBody()).getAccessToken();
    }
}
