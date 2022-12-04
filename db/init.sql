CREATE SCHEMA IF NOT EXISTS public;
create table IF NOT EXISTS user_query
(
    id
    bigserial
    not
    null,
    created_date
    timestamp
    not
    null,
    query
    varchar
(
    255
) not null,
    primary key
(
    id
)
    );