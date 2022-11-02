package com.brandwatch.minibcr.mentionstorer.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.SolrCrudRepository;

import com.brandwatch.minibcr.mentionstorer.entity.Mention;

public interface MentionRepository extends SolrCrudRepository<Mention, String> {

    Mention findMentionBy(String id);

    List<Mention> findByQueryId(String queryId, Pageable pageable);

}
