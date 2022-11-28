package com.brandwatch.minibcr.mentiongenerator.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${kafka.resource.topic.name}")
    private String resourceTopicName;

    @Bean
    public NewTopic resourceTopic() {
        return TopicBuilder.name(resourceTopicName).build();
    }
}
