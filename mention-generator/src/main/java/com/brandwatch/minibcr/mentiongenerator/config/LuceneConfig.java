package com.brandwatch.minibcr.mentiongenerator.config;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LuceneConfig {

    @Bean
    public StandardAnalyzer standardAnalyzer() {
        return new StandardAnalyzer();
    }

    @Bean
    public Directory getDirectory() {
        return new RAMDirectory();
    }

    @Bean
    public IndexWriterConfig getIndexWriterConfig() {
        return new IndexWriterConfig(standardAnalyzer());
    }

    @Bean
    public IndexWriter getIndexWriter() throws IOException {
        return new IndexWriter(getDirectory(), getIndexWriterConfig());
    }
}
