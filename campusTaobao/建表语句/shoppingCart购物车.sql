create table shopping_cart
(
	user_account varchar(15),
    goods_no varchar(15),
    total_cost varchar(15),
    goods_num varchar(20),
    -- good_info中存在该属性，这里就不加了 vip_derate varchar(15),
    primary key (user_account,goods_no)
);
drop table shopping_cart;
insert into shopping_cart values
('111','001','56.36','100'),
('111','002','58.36','2'),
('112','001','56.36','100'),
('112','002','59.36','10')
;
select * from shopping_cart