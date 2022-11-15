package com.brandwatch.minibcr.mentionstorer.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.brandwatch.minibcr.common.model.Mention;

public class MentionConsumerTest {

    private MentionConsumer mentionConsumer;
    private MentionService mentionServiceMock;

    @BeforeEach
    void initMentionConsumer() {
        mentionServiceMock = Mockito.mock(MentionService.class);
        mentionConsumer = new MentionConsumer(mentionServiceMock);
    }

    @Test
    public void shouldCallSaveWhenConsumed() {
        Mention testMention = new Mention();
        mentionConsumer.consume(testMention);
        verify(mentionServiceMock, times(1)).save(testMention);
    }
}
