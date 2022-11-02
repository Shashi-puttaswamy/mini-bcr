package com.brandwatch.minibcr.mentiongenerator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.brandwatch.minibcr.common.model.Mention;
import com.brandwatch.minibcr.common.model.Resource;
import com.brandwatch.minibcr.mentiongenerator.model.UserQuery;
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

    public void generate(Resource resource) {

        List<UserQuery> queries = queryClient.getQuery();
        searchService.indexDocument(resource.getTitle(), resource.getText());

        for (UserQuery query : queries) {
            if (resource.getTitle() != null && resource.getText() != null) {
                if (searchService.search(query.getQuery())) {
                    mentionProducer.sendMessage(new Mention(query.getId(), resource));
                }
            }
        }
    }
}
