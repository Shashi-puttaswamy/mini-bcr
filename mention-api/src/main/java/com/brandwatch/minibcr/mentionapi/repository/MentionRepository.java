package com.brandwatch.minibcr.mentionapi.repository;

import java.util.List;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.brandwatch.minibcr.common.model.Mention;


public interface MentionRepository extends SolrCrudRepository<Mention, String> {

    List<Mention> findByQueryId(long queryId);

}
