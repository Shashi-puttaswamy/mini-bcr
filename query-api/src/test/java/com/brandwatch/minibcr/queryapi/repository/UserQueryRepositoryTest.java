package com.brandwatch.minibcr.queryapi.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.brandwatch.minibcr.queryapi.config.TestDbInitializer;
import com.brandwatch.minibcr.queryapi.entity.UserQuery;

@SpringBootTest
public class UserQueryRepositoryTest extends TestDbInitializer {

    @Autowired
    private UserQueryRepository userQueryRepository;

    @Test
    @DisplayName("should save userQuery successfully")
    void shouldSaveUserQuery() {
        UserQuery userQuery = new UserQuery();
        userQuery.setQuery("iphone");
        UserQuery savedUserQuery = userQueryRepository.save(userQuery);


        assertThat(savedUserQuery)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(userQuery);
    }
}
