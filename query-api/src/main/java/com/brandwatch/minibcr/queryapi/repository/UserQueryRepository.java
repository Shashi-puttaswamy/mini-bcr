package com.brandwatch.minibcr.queryapi.repository;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import reactor.util.annotation.NonNull;

import com.brandwatch.minibcr.queryapi.entity.UserQuery;

@Repository
public interface UserQueryRepository extends JpaRepository<UserQuery, Long> {
    @Override
    @NonNull
    @Cacheable("user-query")
    List<UserQuery> findAll();

    @Override
    @CacheEvict(value = "user-query", allEntries = true)
    <S extends UserQuery> @NonNull S save(@NonNull S userQuery);
}
