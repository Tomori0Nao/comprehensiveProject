create table user_order
(
	order_id varchar(20),
    order_time datetime,
    delivery_address varchar(50),
    goods_price varchar(15),
    actualPayment varchar(15),
    goods_name varchar(50),
    goods_num varchar(20),
    primary key (order_id)
);
insert into user_order values
('order1','2023-12-14 15:30:06','巴南区红光社区','100.96','99.96','靓仔洗衣液','2'),
('order2','2023-12-15 04:19:26','北京市朝阳区','1000.96','99.96','乐子手机','1')
;
select * from user_order