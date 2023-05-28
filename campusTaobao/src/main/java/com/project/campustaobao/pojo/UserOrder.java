package com.project.campustaobao.pojo;

/**
 * 用户订单
 * @author gt5b
 * @version 1.0
 * 2023-5.26 14:30
 */
public class UserOrder {
    private String orderId;
    private String orderTime;
    private String deliveryAddress;
    private String goodsPrice;
    private String vipDerate;
    private String actualPayment;
    private String goodsName;
    private String goodsNumber;
    public UserOrder(){}

    public UserOrder(String orderId, String orderTime,
                     String deliveryAddress, String goodsPrice,
                     String vipDerate, String actualPayment,
                     String goodsName, String goodsNumber) {
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.deliveryAddress = deliveryAddress;
        this.goodsPrice = goodsPrice;
        this.vipDerate = vipDerate;
        this.actualPayment = actualPayment;
        this.goodsName = goodsName;
        this.goodsNumber = goodsNumber;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getActualPayment() {
        return actualPayment;
    }

    public void setActualPayment(String actualPayment) {
        this.actualPayment = actualPayment;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getVipDerate() {
        return vipDerate;
    }

    public void setVipDerate(String vipDerate) {
        this.vipDerate = vipDerate;
    }

    public String getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber;
    }
}
