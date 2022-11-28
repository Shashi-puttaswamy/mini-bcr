package com.brandwatch.minibcr.mentiongenerator.service;

import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.brandwatch.minibcr.common.model.Resource;

@Service
public class ResourceConsumer {

    private final MentionGeneratorService mentionGeneratorService;

    public ResourceConsumer(MentionGeneratorService mentionGeneratorService) {
        this.mentionGeneratorService = mentionGeneratorService;
    }

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(Resource resource) throws IOException {
        mentionGeneratorService.generate(resource);
    }
}
