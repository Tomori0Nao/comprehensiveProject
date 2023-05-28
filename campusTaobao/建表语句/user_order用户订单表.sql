drop table user_order;
create table user_order
(
	order_no varchar(20),
    order_time datetime,
    delivery_no varchar(30),
    goods_no varchar(20),  -- 通过商品编号就可以获取商品的名称和价格信息
    order_price varchar(15),                        -- 下单价格
    order_derate varchar(15),                       -- 下单时优惠的费用
    actual_payment varchar(15),
    goods_num int,
    primary key (order_no),
    foreign key (goods_no) references goods_info(goods_no)
);
insert into user_order values
('order1','2023-12-14 15:30:06','巴南区红光社区','sh001','100.96',2),
('order2','2023-12-15 04:19:26','北京市朝阳区','sh002','1000.96',1),
('order3','2023-12-14 15:30:06','巴南区红光社区','sh112','100.96',2)
;
select * from user_order