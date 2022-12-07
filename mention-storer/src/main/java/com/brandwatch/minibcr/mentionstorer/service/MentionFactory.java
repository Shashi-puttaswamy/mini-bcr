package com.brandwatch.minibcr.mentionstorer.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.brandwatch.minibcr.mentionstorer.entity.Mention;

@Service
public class MentionFactory {

    public Mention getMention(com.brandwatch.minibcr.common.model.Mention mention) {
        Mention mentionEntity = new Mention();
        mentionEntity.setResource(mention.getResource());
        mentionEntity.setTitle(mention.getResource().getTitle());
        mentionEntity.setBody(mention.getResource().getText());
        mentionEntity.setQueryId(mention.getQueryId());
        mentionEntity.setId(UUID.randomUUID().toString());
        return mentionEntity;
    }
}
