package com.brandwatch.minibcr.mentionstorer.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.brandwatch.minibcr.mentionstorer.entity.Mention;

public interface MentionRepository extends SolrCrudRepository<Mention, String> {

}
