package com.brandwatch.minibcr.queryapi.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "user_query")
public class UserQuery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;
    @Column(name = "query", nullable = false)
    private String query;
    @Column(name = "created_date", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public UserQuery setQuery(String query) {
        this.query = query;
        return this;
    }

    public UserQuery setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }
}
