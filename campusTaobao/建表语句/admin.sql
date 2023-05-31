-- 管理员
use mybatis_test;
drop table admin;
select * from admin;
create table admin
(
	admin_account varchar(15),
    admin_password varchar(20),
    admin_nake_name varchar(30),
    admin_type varchar(20),
    registerDate date,
    is_baned boolean,
    primary key (admin_account)
)
;
insert into admin values
('admin001','123456','我是系统管理员','系统管理员','2022-12-16',false),
('admin002','123455','我是商品管理员','商品专员','2022-12-17',false),
('admin003','123454','我是营销管理员','营销经理','2022-12-23',false),
('admin004','123453','我是营销管理员','营销经理','2022-12-23',true)