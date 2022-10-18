package com.brandwatch.minibcr.crawler.service;

import com.brandwatch.minibcr.crawler.model.Resource;

import java.util.List;

public interface Crawler {
    List<Resource> crawl();
}
