package com.brandwatch.minibcr.mentiongenerator.service.lucene;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.springframework.stereotype.Service;

@Service
public class LuceneSearchService implements LuceneSearch {

    private final IndexWriter indexWriter;
    private final Directory directory;

    public LuceneSearchService(IndexWriter indexWriter, Directory directory) {
        this.indexWriter = indexWriter;
        this.directory = directory;
    }

    public void indexDocument(String title, String body) {
        try {
            Document document = new Document();
            document.add(new TextField(LuceneResourceConstants.TITLE, title, Field.Store.YES));
            document.add(new TextField(LuceneResourceConstants.BODY, body, Field.Store.YES));
            indexWriter.addDocument(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean search(Query query) throws IOException {
        try {
            IndexReader reader = DirectoryReader.open(indexWriter);
            IndexSearcher searcher = new IndexSearcher(reader);
            TopDocs search = searcher.search(query, LuceneResourceConstants.MAX_SEARCH);
            ScoreDoc[] hits = search.scoreDocs;
            return hits.length > 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
