create  table article_list
(
    article_id bigint auto_increment comment '文章id' primary key,
    title varchar(50) null comment '文章标题',
    class_id bigint null comment '分类id',
    top tinyint(1) default 0 null comment '是否置顶（0否1是）',
    cover_picture varchar(225) null comment '列表图片URL',
    create_time datetime null comment '保存时间',
    hot int(10) default 0 null comment '文章点击数',
    status tinyint(2) default 1 comment '文章状态 1,公开 2 私密, 3删除 ',
    digest varchar(200) null comment '文章摘要',
    uid bigint comment '作者' not null
)
    charset=utf8mb4;

create table article_content
(
    article_id bigint comment '文章id' primary key,
    content text comment '文章内容' null
)
    charset=utf8mb4;

create table article_class
(
    class_id bigint auto_increment comment '分类id' primary key,
    class_name varchar(30) null comment '分类名称',
    uid bigint comment '用户id' not null

)
    charset=utf8mb4;

create table article_comment
(
    comment_id bigint auto_increment comment '评论id' primary key,
    p_id bigint null comment '被评论id',
    article_id bigint comment '被评论id' not null,
    create_time datetime null comment '保存时间',
    comment varchar(500) null comment '评论内容',
    uid bigint comment '评论人' not null
)
    charset=utf8mb4;

create table article_like
(
    uid bigint auto_increment comment '用户id',
    article_id bigint comment '文章id',
    create_time datetime null comment '保存时间',
    constraint table_name_pk
        primary key (uid, article_id)
)
    charset=utf8mb4;

