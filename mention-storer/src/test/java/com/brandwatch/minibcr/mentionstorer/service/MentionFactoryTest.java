package com.brandwatch.minibcr.mentionstorer.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.brandwatch.minibcr.common.model.Mention;
import com.brandwatch.minibcr.common.model.Resource;

public class MentionFactoryTest {


    private MentionFactory mentionFactory;


    @BeforeEach
    void initMentionFactory() {
        mentionFactory = new MentionFactory();
    }

    @Test
    public void shouldReturnMentionEntity() {
        Mention testMention = new Mention(1L, new Resource("testTitle", "testText"));
        com.brandwatch.minibcr.mentionstorer.entity.Mention mention = mentionFactory.getMention(testMention);
        Assertions.assertEquals(testMention.getQueryId(), mention.getQueryId());
    }
}
