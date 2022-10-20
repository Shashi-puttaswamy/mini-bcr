package com.brandwatch.minibcr.crawler.serializer;

import com.brandwatch.minibcr.crawler.model.Resource;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

public class ResourceSerializer implements Serializer<Resource> {

    @Override
    public byte[] serialize(String topic, Resource resource) {
        ObjectMapper mapper = new ObjectMapper();
        byte[] resourceBytes = null;
        try {
            resourceBytes  = mapper.writeValueAsString(resource).getBytes();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return resourceBytes ;
    }
}
