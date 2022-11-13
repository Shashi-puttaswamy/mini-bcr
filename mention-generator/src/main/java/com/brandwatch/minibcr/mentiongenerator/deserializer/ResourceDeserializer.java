package com.brandwatch.minibcr.mentiongenerator.deserializer;

import java.io.IOException;

import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.brandwatch.minibcr.common.model.Resource;

public class ResourceDeserializer implements Deserializer<Resource> {
    private final Logger logger = LoggerFactory.getLogger(ResourceDeserializer.class);

    private final ObjectMapper objectMapper;

    public ResourceDeserializer() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Resource deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, Resource.class);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
