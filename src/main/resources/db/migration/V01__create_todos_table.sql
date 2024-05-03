CREATE TABLE todos (
    id BIGSERIAL PRIMARY KEY,
    title varchar(100) NOT NULL,
    body varchar(1000) NOT NULL
);
