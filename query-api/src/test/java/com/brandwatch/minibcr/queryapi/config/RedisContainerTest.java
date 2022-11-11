package com.brandwatch.minibcr.queryapi.config;

import org.junit.ClassRule;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.GenericContainer;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = {RedisContainerTest.Initializer.class})
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
