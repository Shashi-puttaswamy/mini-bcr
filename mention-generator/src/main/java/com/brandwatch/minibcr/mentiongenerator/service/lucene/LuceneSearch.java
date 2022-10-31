package com.brandwatch.minibcr.mentiongenerator.service.lucene;

import java.io.IOException;

import org.apache.lucene.search.Query;

public interface LuceneSearch {

    void indexDocument(String title, String body);

    boolean search(Query query) throws IOException;
}
