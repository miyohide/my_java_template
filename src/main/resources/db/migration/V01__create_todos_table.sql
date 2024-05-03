CREATE TABLE todos (
    id BIGSERIAL PRIMARY KEY,
    title varchar(100) NOT NULL,
    body varchar(1000) NOT NULL,
    user_id BIGINT NOT NULL,
    completed boolean NOT NULL DEFAULT false,
    foreign key (user_id) references users(id)
);
