package com.brandwatch.minibcr.mentionapi.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.convert.MappingSolrConverter;
import org.springframework.data.solr.core.convert.SolrConverter;
import org.springframework.data.solr.core.convert.SolrCustomConversions;
import org.springframework.data.solr.core.mapping.SimpleSolrMappingContext;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

import com.brandwatch.minibcr.mentionapi.converter.StringToMentionConverter;
import com.brandwatch.minibcr.mentionapi.converter.StringToResourceConverter;


@Configuration
@EnableSolrRepositories("com.brandwatch.minibcr.mentionapi.repository")
public class SolarConfig {


    @Value("${spring.data.solr.host}")
    private String solrURL;

    @Value("${solr.collection}")
    private String solrCollection;

    @Bean
    public SolrClient solrClient() {
        return new HttpSolrClient.Builder(solrURL).build();
    }

    @Bean
    public SolrTemplate solrTemplate(SolrClient client) {
        SolrTemplate solrTemplate = new SolrTemplate(client);
        solrTemplate.setSolrConverter(solrConverter());
        return solrTemplate;
    }

    @Bean
    public SolrConverter solrConverter() {
        final MappingSolrConverter converter = new MappingSolrConverter(new SimpleSolrMappingContext());
        converter.setCustomConversions(customConversions());
        return converter;
    }

    @Bean
    public CustomConversions customConversions() {
        final List<Object> converters = new ArrayList<>();
        converters.add(StringToResourceConverter.INSTANCE);
        converters.add(StringToMentionConverter.INSTANCE);
        return new SolrCustomConversions(converters);
    }

    @Bean
    public String collectionName() {
        return solrCollection;
    }


}
