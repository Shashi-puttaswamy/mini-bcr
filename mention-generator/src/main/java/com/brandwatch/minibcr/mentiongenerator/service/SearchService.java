package com.brandwatch.minibcr.mentiongenerator.service;

import com.brandwatch.minibcr.mentiongenerator.service.lucene.LuceneSearch;
import com.brandwatch.minibcr.mentiongenerator.service.lucene.LuceneSearchService;

public class SearchService implements LuceneSearch {

    private final LuceneSearchService luceneSearchService;

    public SearchService(LuceneSearchService luceneSearchService) {
        this.luceneSearchService = luceneSearchService;
    }

    @Override
    public void indexDocument(String title, String body) {
         luceneSearchService.indexDocument(title, body);
    }

    @Override
    public boolean search(String queryString) {
        return luceneSearchService.search(queryString);
    }
}
