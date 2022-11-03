package com.brandwatch.minibcr.mentionstorer.converter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.brandwatch.minibcr.common.model.Resource;

public class StringToResourceConverter implements Converter<String, Resource> {

    private final Logger logger = LoggerFactory.getLogger(StringToResourceConverter.class);

    private final ObjectMapper objectMapper;

    public StringToResourceConverter() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Resource convert(@NonNull String source) {

        try {
            return this.objectMapper.readValue(source, Resource.class);
        } catch (IOException e) {
            logger.error("Exception occurred converting string to Resource Object: {}", e.getMessage());
        }
        return null;
    }
}
