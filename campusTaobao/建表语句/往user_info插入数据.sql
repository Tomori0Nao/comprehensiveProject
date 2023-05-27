-- 往user_info中插入数据
use mybatis_test;
insert into user_info   
values("111","222","15369856230","小淘气","1300.36","2023-05-21","2023-05-20 14:30:36","100,36","user1"),
("222","333","15369856230","大淘气","1900.36","2023-05-21","2025-05-20 14:30:36","100,36","user2")
;

delete from user_info where user_account = "sk75496046";
select * from user_info