package com.brandwatch.minibcr.mentionapi.repository;

import java.util.List;

import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import com.brandwatch.minibcr.common.model.Mention;


@Repository
public interface MentionRepository extends SolrCrudRepository<Mention, String> {

    List<Mention> findByQueryId(long queryId);

}
