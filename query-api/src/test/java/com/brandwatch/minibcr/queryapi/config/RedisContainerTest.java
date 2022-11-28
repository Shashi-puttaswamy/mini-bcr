package com.brandwatch.minibcr.queryapi.config;

import org.junit.ClassRule;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.GenericContainer;


public class RedisContainerTest {

    @ClassRule
    public static final GenericContainer<?> redisClusterContainer = new GenericContainer<>("redis:latest")
            .withExposedPorts(6379);

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            redisClusterContainer.start();
            TestPropertyValues.of(
                    "spring.redis.host=" + redisClusterContainer.getHost(),
                    "spring.redis.port=" + redisClusterContainer.getFirstMappedPort()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
