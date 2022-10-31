package com.brandwatch.minibcr.mentiongenerator.service;

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

import com.brandwatch.minibcr.common.model.Mention;

@Service
public class MentionProducer {
    private final Logger logger = LoggerFactory.getLogger(MentionProducer.class);

    private final KafkaTemplate<String, Mention> kafkaTemplate;

    public MentionProducer(KafkaTemplate<String, Mention> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Value(value = "${kafka.resource.topic.name}")
    private String topicName;

    public void sendMessage(Mention message) {
        ListenableFuture<SendResult<String, Mention>> future =
                kafkaTemplate.send(topicName, new Random().nextLong() + "", message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Mention>>() {

            @Override
            public void onSuccess(SendResult<String, Mention> result) {
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
