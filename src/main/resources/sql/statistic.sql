-- auto-generated definition
create table statistic
(
    id           int auto_increment
        primary key,
    atm_id       int not null,
    slot_1_sub   int not null,
    slot_2_add   int not null,
    slot_2_sub   int not null,
    slot_5_add   int not null,
    slot_5_sub   int not null,
    slot_1_add   int not null,
    slot_10_sub  int not null,
    slot_20_add  int not null,
    slot_20_sub  int not null,
    slot_50_add  int not null,
    slot_50_sub  int not null,
    slot_100_add int not null,
    slot_100_sub int not null,
    slot_10_add  int not null,
    constraint bills_statistic_id_uindex
        unique (id),
    constraint statistic_atms_id_fk
        foreign key (atm_id) references atms (id)
);

