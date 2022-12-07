package com.brandwatch.minibcr.mentiongenerator.service.lucene;

import java.io.IOException;
import java.util.Arrays;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LuceneSearchService implements LuceneSearch {

    private final Logger logger = LoggerFactory.getLogger(LuceneSearchService.class);

    private final IndexWriter indexWriter;


    public LuceneSearchService(IndexWriter indexWriter) {
        this.indexWriter = indexWriter;
    }

    public void indexDocument(String title, String body) {
        try {
            deleteIndex();
            Document document = new Document();
            document.add(new TextField(LuceneResourceConstants.TITLE, title, Field.Store.YES));
            document.add(new TextField(LuceneResourceConstants.BODY, body, Field.Store.YES));
            indexWriter.addDocument(document);
            indexWriter.commit();
        } catch (IOException e) {
            logger.error(Arrays.toString(e.getStackTrace()));
        }
    }

    private void deleteIndex() throws IOException {
        indexWriter.deleteAll();
        indexWriter.commit();
    }

    public boolean search(String queryString) {
        Query query = getQuery(queryString);
        try {
            IndexReader reader = DirectoryReader.open(indexWriter);
            IndexSearcher searcher = new IndexSearcher(reader);
            TopDocs search = searcher.search(query, LuceneResourceConstants.MAX_SEARCH);
            ScoreDoc[] hits = search.scoreDocs;
            reader.close();
            return hits.length > 0;
        } catch (IOException e) {
            logger.error(Arrays.toString(e.getStackTrace()));
        }
        return false;
    }

    public BooleanQuery getQuery(String queryString) {
        BooleanQuery.Builder builder = new BooleanQuery.Builder();
        TermQuery bodyTermQuery = new TermQuery(new Term(LuceneResourceConstants.BODY, queryString));
        TermQuery tittleTermQuery = new TermQuery(new Term(LuceneResourceConstants.TITLE, queryString));
        builder.add(new BooleanClause(bodyTermQuery, BooleanClause.Occur.SHOULD));
        builder.add(new BooleanClause(tittleTermQuery, BooleanClause.Occur.SHOULD));
        return builder.build();
    }
}
