package com.brandwatch.minibcr.crawler.service.reddit;


import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.brandwatch.minibcr.common.model.Resource;


@Service
public class Producer {

    private final Logger logger = LoggerFactory.getLogger(Producer.class);

    private final KafkaTemplate<String, Resource> kafkaTemplate;

    public Producer(KafkaTemplate<String, Resource> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Value(value = "${kafka.resource.topic.name}")
    private String topicName;

    public void sendMessage(Resource message) {
        ListenableFuture<SendResult<String, Resource>> future =
                kafkaTemplate.send(topicName, new Random().nextLong() + "", message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Resource>>() {

            @Override
            public void onSuccess(SendResult<String, Resource> result) {
                logger.info("sent message with offset -"
                        + result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(@NonNull Throwable ex) {
                logger.error("failed to send message - " + ex.getMessage());
            }
        });
    }
}
