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
insert into `ebook` (id, name, description) values (2, 'Vue 入门教程', '零基础入门，Vue企业开发');