package com.brandwatch.minibcr.crawler.serializer;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.brandwatch.minibcr.common.model.Resource;


public class ResourceSerializer implements Serializer<Resource> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, Resource resource) {
        byte[] resourceBytes = null;
        try {
            resourceBytes = objectMapper.writeValueAsString(resource).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resourceBytes;
    }
}
