create table sys_user
(
    id                bigint auto_increment primary key,
    username          varchar(255)           null,
    password          varchar(255)           not null,
    mobile_number     varchar(15)            null comment '手机',
    gender            tinyint    default '0' not null comment '性别',
    email             varchar(50)            null comment '邮箱',
    latest_login_time timestamp              null comment '最后登录的时间',
    create_time       datetime comment '创建时间',
    nickname          varchar(100) comment '创建时间',
    avatar            varchar(300) comment '创建时间',
    enable            tinyint    default 0   not null,
    type              tinyint(2) default 2   null,
    openid            varchar(200)           null,
    constraint user_openId_uindex unique (openid)
)
    comment '用户表' charset = utf8mb4;


create table sys_role
(
    id          mediumint(8) auto_increment
        primary key,
    name        varchar(255) not null,
    description varchar(100) null,
    constraint role_name_uindex
        unique (name)
)
    comment '角色表' charset = utf8mb4;

create table sys_power
(
    id   mediumint(8) auto_increment
        primary key,
    name varchar(255) not null,
    url  varchar(255) not null,
    pid  mediumint(8) null,
    constraint power_name_url_pk
        unique (name, url)
)
    charset = utf8mb4;

create table sys_user_role
(
    role_id bigint not null,
    user_id bigint not null,
    primary key (role_id, user_id)
)
    charset = utf8mb4;

create index user_role_user_id_fk
    on sys_user_role (user_id);

create table sys_role_power
(
    role_id  mediumint(8) not null,
    power_id mediumint(8) not null,
    primary key (role_id, power_id),
    constraint role_power_power_id_fk
        foreign key (power_id) references sys_power (id),
    constraint role_power_role_id_fk
        foreign key (role_id) references sys_role (id)
)
    charset = utf8mb4;
create table sys_whitelist
(
    url varchar(100) not null
        primary key
);
