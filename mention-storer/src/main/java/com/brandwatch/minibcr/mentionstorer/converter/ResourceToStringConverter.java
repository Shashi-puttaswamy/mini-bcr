package com.brandwatch.minibcr.mentionstorer.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.brandwatch.minibcr.common.model.Resource;

@WritingConverter
public enum ResourceToStringConverter implements Converter<Resource, String> {
    INSTANCE;

    @Override
    public String convert(@NonNull final Resource entity) {
        final ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(entity);
        } catch (final JsonProcessingException e) {
            throw new RuntimeException("Unable to serialize the object");
        }
    }
}
