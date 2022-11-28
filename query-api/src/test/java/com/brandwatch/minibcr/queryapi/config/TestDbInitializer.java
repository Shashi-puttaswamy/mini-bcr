package com.brandwatch.minibcr.queryapi.config;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        initializers = {PostgresContainerTest.Initializer.class, RedisContainerTest.Initializer.class}
)
public class TestDbInitializer {

}
