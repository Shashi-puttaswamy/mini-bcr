package com.brandwatch.minibcr.mentionstorer.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.brandwatch.minibcr.common.model.Mention;

@Service
public class MentionFactory {

    public com.brandwatch.minibcr.mentionstorer.entity.Mention getMention(Mention mention) {
        com.brandwatch.minibcr.mentionstorer.entity.Mention mentionEntity = new com.brandwatch.minibcr.mentionstorer.entity.Mention();
        mentionEntity.setTitle(mention.getResource().getTitle());
        mentionEntity.setBody(mention.getResource().getText());
        mentionEntity.setQueryId(mention.getQueryId());
        mentionEntity.setId(UUID.randomUUID().toString());
        return mentionEntity;
    }
}
