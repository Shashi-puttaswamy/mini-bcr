package com.brandwatch.minibcr.crawler.service.reddit.auth;

public class RedditClient {

    private final String clientId;
    private final String secret;

    private final String clientName;

    public RedditClient(String clientId, String secret, String clientName) {
        this.clientId = clientId;
        this.secret = secret;
        this.clientName = clientName;
    }

    public String getClientId() {
        return clientId;
    }

    public String getSecret() {
        return secret;
    }

    public String getClientName() {
        return clientName;
    }
}
