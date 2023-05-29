use mybatis_test;
drop table user_order;
create table user_order
(
	order_no varchar(20),
    user_account varchar(15),
    order_time datetime,
    delivery_no varchar(30),
    goods_no varchar(20),  -- 通过商品编号就可以获取商品的名称和价格信息
    order_price varchar(15),                        -- 下单价格
    order_derate varchar(15),                       -- 下单时优惠的费用
    actual_payment varchar(15),
    goods_num int,
    primary key (order_no)
    -- 以下外键约束待添加
    -- foreign key (goods_no) references goods_info(goods_no)
    -- foreign key (user_account) references user_info(user_account)
    -- foreign key (delivery_no) references delivery_address(delivery_no)
);
insert into user_order values
('order1','111','2023-12-14 15:30:06','123','111','12.3','1.33','100.96',2),
('order2','111','2023-12-14 17:38:06','123','111','12.3','1.33','10.89',3),
('order3','121','2023-12-14 17:38:06','123','111','12.3','1.33','10.89',3)
;
select * from user_order