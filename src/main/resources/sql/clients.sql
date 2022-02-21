-- auto-generated definition
create table clients
(
    id      int auto_increment
        primary key,
    name    varchar(255)  not null,
    account int default 0 not null,
    constraint clients_id_uindex
        unique (id)
);