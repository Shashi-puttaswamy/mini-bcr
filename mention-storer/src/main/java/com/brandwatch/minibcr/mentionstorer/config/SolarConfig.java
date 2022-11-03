package com.brandwatch.minibcr.mentionstorer.config;

import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.convert.MappingSolrConverter;
import org.springframework.data.solr.core.convert.SolrConverter;
import org.springframework.data.solr.core.mapping.SimpleSolrMappingContext;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

import com.brandwatch.minibcr.mentionstorer.converter.ResourceCustomConversion;
import com.brandwatch.minibcr.mentionstorer.converter.StringToResourceConverter;

@Configuration
@EnableSolrRepositories(basePackages = "com.brandwatch.minibcr.mentionstorer.repository")
public class SolarConfig {


    @Value("${spring.data.solr.host}")
    private String solrURL;

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

        final StringToResourceConverter stringToOrderTypeConverter = new StringToResourceConverter();

        final MappingSolrConverter converter = new MappingSolrConverter(new SimpleSolrMappingContext());
        converter.setCustomConversions(new ResourceCustomConversion(List.of(stringToOrderTypeConverter)));
        return converter;
    }

}
