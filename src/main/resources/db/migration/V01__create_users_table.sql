CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name varchar(100) NOT NULL
    number_of_todos LONG NOT NULL DEFAULT 0
);
