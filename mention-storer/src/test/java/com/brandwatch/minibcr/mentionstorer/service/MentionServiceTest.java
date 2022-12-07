package com.brandwatch.minibcr.mentionstorer.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.brandwatch.minibcr.common.model.Mention;
import com.brandwatch.minibcr.common.model.Resource;
import com.brandwatch.minibcr.mentionstorer.repository.MentionRepository;

public class MentionServiceTest {

    private MentionRepository mentionRepositoryMock;
    private MentionService mentionService;

    @BeforeEach
    void initMentionService() {
        mentionRepositoryMock = Mockito.mock(MentionRepository.class);
        mentionService = new MentionService(mentionRepositoryMock, new MentionFactory());
    }

    @Test
    public void shouldSaveWhenMentionGiven() {
        Mention mention = new Mention(1L, new Resource("testTitle", "testBody"));
        when(mentionRepositoryMock
                .save(Matchers.any(com.brandwatch.minibcr.mentionstorer.entity.Mention.class)))
                .thenReturn(new com.brandwatch.minibcr.mentionstorer.entity.Mention());
        mentionService.save(mention);
        verify(mentionRepositoryMock, times(1))
                .save(Matchers.any(com.brandwatch.minibcr.mentionstorer.entity.Mention.class));

    }
}
