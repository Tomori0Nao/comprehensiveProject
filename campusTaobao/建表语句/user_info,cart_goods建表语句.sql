
-- use mybatis_test

/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2023/5/23 17:25:52                           */
/*==============================================================*/

drop table if exists shopping_cart;
drop table if exists goods_info;
drop table if exists user_info;

/*==============================================================*/
/* Table: goods_info                                            */
/*==============================================================*/
create table goods_info
(
   goods_no             varchar(20) not null,
   goods_name           varchar(50),
   goods_type           varchar(20),
   goods_price          varchar(15),
   goods_purchase_price varchar(15),
   goods_brand          varchar(20),
   goods_image_name     varchar(100),
   goods_weight         varchar(20),
   goods_origin         varchar(50),
   goods_special_info   varchar(50),
   goods_num            varchar(20),
   goods_month_sales    varchar(10),
   goods_purchase_date  date,
   store_no             varchar(15),
   primary key (goods_no)
);

/*==============================================================*/
/* Table: shopping_cart                                         */
/*==============================================================*/
create table shopping_cart
(
   user_account         varchar(15) not null,
   goods_no             varchar(20),
   cart_goods_num       varchar(10),
   primary key (user_account),
   foreign key (user_account) references user_info (user_account),
   foreign key (goods_no) references goods_info (goods_no)
);

/*==============================================================*/
/* Table: user_info                                             */
/*==============================================================*/
create table user_info
(
   user_account         varchar(15) not null,
   user_password          varchar(20),
   user_tel             varchar(15),
   user_name            varchar(30),
   user_balance         varchar(50),
   register_date        date,
   last_login_time      datetime,
   total_consumption    varchar(50),
   cart_no              varchar(10),
   primary key (user_account)
)
;
create table goods_image
(
	image_id varchar(20) not null,
    image_name varchar(100) not null,
    primary key (image_id)
);
drop table goods_image;
insert into goods_image  
values('1','homeGoods-1'),('2','homeGoods-2'),('3','homeGoods-3'),('4','homeGoods-3'),('5','homeGoods-5')

;
-- 创建vip表
create table vip_user
(
	vip_user_account varchar(16) not null,
    expiration_time datetime,
    integral varchar(10),
    saving varchar(10),
    primary key (vip_user_account)
);
drop table vip_user
insert into vip_user  
values('66666vip','2023/6/23/14:30:36','100','100'),('888888vip','2025/6/23/14:30:36','1000','1000')



