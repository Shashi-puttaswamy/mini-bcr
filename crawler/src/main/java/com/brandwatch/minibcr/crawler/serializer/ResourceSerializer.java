package com.brandwatch.minibcr.crawler.serializer;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.brandwatch.minibcr.common.model.Resource;


public class ResourceSerializer implements Serializer<Resource> {

    @Override
    public byte[] serialize(String topic, Resource resource) {
        ObjectMapper mapper = new ObjectMapper();
        byte[] resourceBytes = null;
        try {
            resourceBytes = mapper.writeValueAsString(resource).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resourceBytes;
    }
}
