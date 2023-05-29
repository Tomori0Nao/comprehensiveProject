use mybatis_test;
drop table delivery_address;
create table delivery_address
(
	address_no varchar(30),         -- 地址编号
    user_account varchar(15),       -- 用户账号
    consignee_name varchar(30),      -- 收获姓名，考虑到收货人姓名未必是真名，因此就不与user表连接
    consignee_tel varchar(15),       -- 收货人电话
    address varchar(60),             -- 地址
    primary key (address_no)
    -- foreign key (user_account) references user_info (user_account)
)
;
insert into delivery_address
values('add000001','111','李白','6958376952','重庆市巴南社区'),
('add000002','111','张飞','19687413206','北京市朝阳区'),
('add000003','222','关三庚','663-5698-523','南天门仙人阁'),
('add000004','111','张部阶','186354783201','锁妖塔第66层'),
('add000005','222','车子发','19875364135','蜀山修仙社区长青楼第6单元16层223户')
;
select * from delivery_address;