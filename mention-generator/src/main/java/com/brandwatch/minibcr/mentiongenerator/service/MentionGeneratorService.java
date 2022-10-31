package com.brandwatch.minibcr.mentiongenerator.service;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.springframework.stereotype.Service;

import com.brandwatch.minibcr.common.model.Mention;
import com.brandwatch.minibcr.common.model.Resource;
import com.brandwatch.minibcr.mentiongenerator.model.UserQuery;
import com.brandwatch.minibcr.mentiongenerator.service.lucene.LuceneResourceConstants;
import com.brandwatch.minibcr.mentiongenerator.service.lucene.LuceneSearchService;
import com.brandwatch.minibcr.mentiongenerator.service.query.QueryClient;

@Service
public class MentionGeneratorService {

    private final QueryClient queryClient;
    private final SearchService searchService;
    private final MentionProducer mentionProducer;

    public MentionGeneratorService(LuceneSearchService luceneSearchService, QueryClient queryClient, MentionProducer mentionProducer) {
        this.searchService = new SearchService(luceneSearchService);
        this.queryClient = queryClient;
        this.mentionProducer = mentionProducer;
    }

    public void generate(Resource resource) throws IOException {

        List<UserQuery> queries = queryClient.getQuery();
        searchService.indexDocument(resource.getTitle(), resource.getText());

        for (UserQuery query : queries) {
            Query parsedQuery  = new TermQuery(new Term(LuceneResourceConstants.BODY, query.getQuery()));
            if (resource.getTitle() != null && resource.getText() != null) {
                if (searchService.search(parsedQuery)) {
                    mentionProducer.sendMessage(new Mention(query.getId(), resource));
                }
            }
        }
    }
}
