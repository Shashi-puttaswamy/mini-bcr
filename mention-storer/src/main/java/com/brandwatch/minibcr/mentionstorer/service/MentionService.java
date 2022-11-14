package com.brandwatch.minibcr.mentionstorer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.brandwatch.minibcr.common.model.Mention;
import com.brandwatch.minibcr.mentionstorer.repository.MentionRepository;

@Service
public class MentionService implements MentionServiceInterface {
    private final Logger logger = LoggerFactory.getLogger(MentionService.class);


    private final MentionRepository mentionRepository;
    private final MentionFactory mentionFactory;

    public MentionService(MentionRepository mentionRepository, MentionFactory mentionFactory) {
        this.mentionRepository = mentionRepository;
        this.mentionFactory = mentionFactory;
    }

    @Override
    public void save(Mention mention) {
        com.brandwatch.minibcr.mentionstorer.entity.Mention mentionEntity = mentionRepository.save(mentionFactory.getMention(mention));
        logger.info("saved " + mentionEntity.getId());
    }
}
