drop table if exists t_user;

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   id                   bigint not null,
   loginName            varchar(255),
   loginPwd             varchar(255),
   realName             varchar(255),
   primary key (id)
);

insert into t_user (id, loginName, loginPwd, realName) values (1,'zhangsan', '123', '张三');
insert into t_user (id, loginName, loginPwd, realName) values (2,'lisi', '12345', '李四');