package com.brandwatch.minibcr.mentionstorer.converter;

import java.util.List;

import org.springframework.data.solr.core.convert.SolrCustomConversions;

public class ResourceCustomConversion extends SolrCustomConversions {

    public ResourceCustomConversion(List<?> converters) {
        super(converters);
    }
}
