package com.brandwatch.minibcr.mentiongenerator.serializer;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.brandwatch.minibcr.common.model.Mention;

public class MentionSerializer implements Serializer<Mention> {

    @Override
    public byte[] serialize(String topic, Mention mention) {
        ObjectMapper mapper = new ObjectMapper();
        byte[] resourceBytes = null;
        try {
            resourceBytes = mapper.writeValueAsString(mention).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resourceBytes;
    }
}
