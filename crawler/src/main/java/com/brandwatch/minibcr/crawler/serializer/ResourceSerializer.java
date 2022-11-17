package com.brandwatch.minibcr.crawler.serializer;

import java.util.Arrays;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.brandwatch.minibcr.common.model.Resource;


public class ResourceSerializer implements Serializer<Resource> {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final Logger logger = LoggerFactory.getLogger(ResourceSerializer.class);


    @Override
    public byte[] serialize(String topic, Resource resource) {
        try {
            return objectMapper.writeValueAsString(resource).getBytes();
        } catch (Exception e) {
            logger.error(Arrays.toString(e.getStackTrace()));
            throw new SerializationException("Unable to serialize the resource");
        }
    }
}
