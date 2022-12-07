CREATE SCHEMA IF NOT EXISTS public;
CREATE TABLE IF NOT EXISTS user_query
(
    id bigserial NOT NULL,
    created_date timestamp NOT NULL,
    query VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);
