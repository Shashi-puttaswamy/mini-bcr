package com.brandwatch.minibcr.mentiongenerator.config;

import java.io.IOException;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.brandwatch.minibcr.mentiongenerator.service.lucene.LuceneStandardAnalyzer;

@Configuration
public class LuceneConfig {

    @Bean
    public LuceneStandardAnalyzer luceneStandardAnalyzer() {
        return new LuceneStandardAnalyzer();
    }

    @Bean
    public IndexWriterConfig indexWriterConfig() {
        return new IndexWriterConfig(luceneStandardAnalyzer());
    }

    @Bean
    public IndexWriter indexWriter() throws IOException {
        return new IndexWriter(luceneDirectory(), indexWriterConfig());
    }

    @Bean
    public Directory luceneDirectory() {
        return new RAMDirectory();
    }

    @Bean
    public IndexReader indexReader() throws IOException {
        return DirectoryReader.open(luceneDirectory());
    }

    @Bean
    public IndexSearcher indexSearcher() throws IOException {
        return new IndexSearcher(indexReader());
    }


}
