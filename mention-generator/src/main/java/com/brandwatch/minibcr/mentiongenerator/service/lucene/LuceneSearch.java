package com.brandwatch.minibcr.mentiongenerator.service.lucene;

import java.io.IOException;

public interface LuceneSearch {

    void indexDocument(String title, String body);

    boolean search(String queryString) throws IOException;
}
