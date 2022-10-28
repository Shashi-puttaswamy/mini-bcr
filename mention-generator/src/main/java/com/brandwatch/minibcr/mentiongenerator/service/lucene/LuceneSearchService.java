package com.brandwatch.minibcr.mentiongenerator.service.lucene;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.springframework.stereotype.Service;

@Service
public class LuceneSearchService {

    private final IndexWriter indexWriter;
    private final IndexSearcher indexSearcher;

    public LuceneSearchService(IndexWriter indexWriter, IndexSearcher indexSearcher) {
        this.indexWriter = indexWriter;
        this.indexSearcher = indexSearcher;
    }

    public void indexDocument(String title, String body) {
        try {
            Document document = new Document();
            document.add(new TextField("title", title, Field.Store.YES));
            document.add(new TextField("body", body, Field.Store.YES));
            indexWriter.addDocument(document);
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean search(Query query) {
        try {
            TopDocs topDocs = indexSearcher.search(query, 1);
            ScoreDoc[] hits = topDocs.scoreDocs;
            return hits.length > 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
