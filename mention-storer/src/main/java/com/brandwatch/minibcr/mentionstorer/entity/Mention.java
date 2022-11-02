package com.brandwatch.minibcr.mentionstorer.entity;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.brandwatch.minibcr.common.model.Resource;

@SolrDocument
public class Mention {

    @Id
    @Field
    @Indexed(type = "uuid")
    private String id;

    @Field
    private Resource resource;

    @Indexed
    private String queryId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
