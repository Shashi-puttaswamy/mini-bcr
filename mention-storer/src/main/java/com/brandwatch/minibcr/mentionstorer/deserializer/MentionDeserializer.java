package com.brandwatch.minibcr.mentionstorer.deserializer;

import java.io.IOException;

import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.brandwatch.minibcr.common.model.Mention;

public class MentionDeserializer implements Deserializer<Mention> {
    private final Logger logger = LoggerFactory.getLogger(MentionDeserializer.class);

    private final ObjectMapper objectMapper;

    public MentionDeserializer() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Mention deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, Mention.class);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
