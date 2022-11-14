package com.brandwatch.minibcr.mentionapi.converter;

import java.io.IOException;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.brandwatch.minibcr.common.model.Resource;

@ReadingConverter
public enum StringToResourceConverter implements Converter<String, Resource> {
    INSTANCE;

    @Override
    public Resource convert(@NonNull String source) {
        try {
            return new ObjectMapper().readValue(source, Resource.class);
        } catch (final IOException e) {
            throw new RuntimeException("Invalid string, unable to deserialize");
        }
    }
}
