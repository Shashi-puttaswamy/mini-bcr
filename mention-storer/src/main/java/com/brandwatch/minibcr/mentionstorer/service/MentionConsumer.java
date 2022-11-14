package com.brandwatch.minibcr.mentionstorer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.brandwatch.minibcr.common.model.Mention;


@Service
public class MentionConsumer {

    private final MentionService mentionService;

    public MentionConsumer(MentionService mentionService) {
        this.mentionService = mentionService;
    }

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(Mention mention) {
        mentionService.save(mention);
    }

}