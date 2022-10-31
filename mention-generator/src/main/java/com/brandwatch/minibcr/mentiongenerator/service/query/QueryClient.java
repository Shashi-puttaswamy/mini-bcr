package com.brandwatch.minibcr.mentiongenerator.service.query;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.brandwatch.minibcr.mentiongenerator.model.UserQuery;

@Service
public class QueryClient {

    private static final String URL = "http://localhost:8084/query";

    private final RestTemplate restTemplate;

    public QueryClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<UserQuery> getQuery() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<List<UserQuery>> response
                = restTemplate.exchange(URL, HttpMethod.GET, entity,
                new ParameterizedTypeReference<List<UserQuery>>() {
                });
        return response.getBody();
    }
}
