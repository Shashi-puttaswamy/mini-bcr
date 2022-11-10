package com.brandwatch.minibcr.mentionapi.config;

import org.junit.ClassRule;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.SolrContainer;
import org.testcontainers.utility.DockerImageName;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = {SolrContainerTest.Initializer.class})
public class SolrContainerTest {

    @ClassRule
    public static SolrContainer solrContainer = new SolrContainer(DockerImageName.parse("solr:8.3.0"))
            .withCollection("mention");

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            solrContainer.start();
            String baseUrl = "http://" + solrContainer.getHost() + ":" + solrContainer.getSolrPort() + "/solr";
            TestPropertyValues.of(
                    "spring.data.solr.host=" + baseUrl,
                    "spring.data.solr.port=" + solrContainer.getSolrPort()
            ).applyTo(configurableApplicationContext.getEnvironment());

        }
    }

}
