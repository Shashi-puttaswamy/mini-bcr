package com.brandwatch.minibcr.mentionapi.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.brandwatch.minibcr.common.model.Mention;
import com.brandwatch.minibcr.common.model.Resource;
import com.brandwatch.minibcr.mentionapi.config.SolrContainerTest;

@SpringBootTest
public class MentionRepositoryTest extends SolrContainerTest {

    @Autowired
    private MentionRepository mentionRepository;

    @Test
    @DisplayName("test solr integration test behaviour")
    public void shouldSaveMention() {

        Mention mention = new Mention(1L,new Resource("title","test"));
        Mention savedMention = mentionRepository.save(mention);

        Assertions.assertEquals(mention.getQueryId(), savedMention.getQueryId());
    }
}
