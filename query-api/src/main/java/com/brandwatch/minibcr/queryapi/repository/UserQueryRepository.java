package com.brandwatch.minibcr.queryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brandwatch.minibcr.queryapi.entity.UserQuery;

public interface UserQueryRepository extends JpaRepository<UserQuery, Long> {
}
