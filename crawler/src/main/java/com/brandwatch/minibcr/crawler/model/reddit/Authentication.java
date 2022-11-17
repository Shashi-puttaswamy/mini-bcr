package com.brandwatch.minibcr.crawler.model.reddit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Authentication {
    @JsonProperty("access_token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
