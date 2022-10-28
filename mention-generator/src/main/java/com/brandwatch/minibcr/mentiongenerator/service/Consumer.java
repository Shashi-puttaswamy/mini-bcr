package com.brandwatch.minibcr.mentiongenerator.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.brandwatch.minibcr.common.model.Resource;

@Service
public class Consumer {

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(Resource resource) {
        System.out.println(resource.getClass().getName());
    }
}
