package com.brandwatch.minibcr.mentiongenerator.service;

import java.io.IOException;

import org.apache.lucene.search.Query;

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
    public boolean search(Query query) throws IOException {
        return luceneSearchService.search(query);
    }
}
