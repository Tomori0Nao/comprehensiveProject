use mybatis_test;
-- 创建普通用户信息表
create table user_info
(
	user_account varchar(20) primary key not null,
    user_password varchar(20),
    user_tel varchar(11),
    user_VIP boolean
);
select * from user_info;
-- 向user_info中插入数据
insert into user_info 
(user_account,user_password,user_VIP,user_tel)
values('vip666','666',true,'666666'),('mk123454','666666',true,'1234567891')
;
delete from user_info where user_account = 'sk-71668952'
;
drop table user_info

