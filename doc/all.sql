drop table if exists `demo`;
create table `demo` (
    `id` bigint not null comment 'id',
    `name` varchar(50) comment '名称',
    `password` varchar(50) comment '密码',
    primary key (`id`)
) engine=innodb default charset=utf8mb4 comment '测试';

insert into `demo` values (1,'测试','12345');

drop table if exists `ebook`;
create table `ebook` (
    `id` bigint not null comment 'id',
    `name` varchar(50) comment '名称',
    `category1_id` bigint comment '分类1id',
    `category2_id` bigint comment '分类2id',
    `description` varchar(200) comment '描述',
    `cover` varchar(200) comment '封面',
    `doc_count` int comment '文档数',
    `view_count` int comment '阅读数',
    `vote_count` int comment '点赞数',
    primary key (`id`)
) engine=innodb default charset=utf8mb4 comment '电子书';

insert into `ebook` (id, name, description) values (1, 'Spring Boot 入门教程', '零基础入门，Java企业开发');
insert into `ebook` (id, name, description) values (2, 'Vue1 入门教程', '零基础入门，Vue企业开发');
insert into `ebook` (id, name, description) values (3, 'Vue2 入门教程', '零基础入门，Vue企业开发');
insert into `ebook` (id, name, description) values (4, 'Vue3 入门教程', '零基础入门，Vue企业开发');
insert into `ebook` (id, name, description) values (5, 'Vue4 入门教程', '零基础入门，Vue企业开发');

drop table if exists `category`;
create table `category` (
    `id` bigint not null comment 'id',
    `parent` bigint not null default 0 comment '父id',
    `name` varchar(50) not null comment '名称',
    `sort` int comment '顺序',
    primary key (`id`)
)engine=innodb default charset=utf8mb4 comment='分类';

insert into `category` (id, parent, name, sort) values (100,000,'前端开发',100);
insert into `category` (id, parent, name, sort) values (101,100,'Vue',101);
insert into `category` (id, parent, name, sort) values (102,000,'HTML',102);
insert into `category` (id, parent, name, sort) values (200,000,'基础应用',200);
insert into `category` (id, parent, name, sort) values (201,200,'基础应用',201);

drop table if exists `doc`;
create table `doc` (
    `id` bigint not null comment 'id',
    `ebook_id` bigint not null default 0 comment '电子书id',
    `parent` bigint not null default 0 comment '父id',
    `name` varchar(50) not null comment '名称',
    `sort` int comment '顺序',
    `view_count` int default 0 comment '阅读数',
    `vote_count` int default 0 comment '点赞数',
    primary key (`id`)
)engine=innodb default charset=utf8mb4 comment='文档';

insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (1, 1, 0, '文档1', 1, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (2, 1, 0, '文档1', 2, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (3, 1, 0, '文档1', 3, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (4, 1, 0, '文档1', 4, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (5, 1, 0, '文档1', 5, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (6, 1, 0, '文档1', 6, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (7, 1, 0, '文档1', 7, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (8, 1, 0, '文档1', 8, 0, 0);

drop table if exists `content`;
create table `content` (
    `id` bigint not null comment '文档id',
    `content` mediumtext not null comment '内容',
    primary key (`id`)
)engine=innodb default charset=utf8mb4 comment='文档';

drop table if exists `user`;
create table `user` (
    `id` bigint not null comment 'ID',
    `login_name` varchar(50) not null comment '登陆名',
    `name` varchar(50) not null comment '昵称',
    `password` char(32) not null comment '密码',
    primary key (`id`),
    unique key `login_name_unique` (`login_name`)
)engine=innodb default charset=utf8mb4 comment='用户';
