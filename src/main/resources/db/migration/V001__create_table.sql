create table PUBLIC.MEMBER
(
    ID   INTEGER not null AUTO_INCREMENT,
    NAME CHARACTER VARYING,
    constraint MEMBER_PK
        primary key (ID)
);
