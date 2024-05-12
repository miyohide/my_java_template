CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    number_of_todos BIGINT NOT NULL DEFAULT 0,
    PRIMARY KEY (id)
);

-- for PostgreSQL
-- CREATE TABLE users (
--     id BIGSERIAL PRIMARY KEY,
--     name varchar(100) NOT NULL,
--     number_of_todos BIGINT NOT NULL DEFAULT 0,
-- );
