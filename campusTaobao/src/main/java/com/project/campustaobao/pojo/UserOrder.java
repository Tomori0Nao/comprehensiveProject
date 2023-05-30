package com.project.campustaobao.pojo;

/**
 * 用户订单
 * @author gt5b
 * @version 1.0
 * 2023-5.26 14:30
 */
public class UserOrder {
    private String userAccount;
    private String orderNo;
    private String orderTime;
    private String deliveryAddress;
    private String orderPrice;
    private String orderDerate;
    private String actualPayment;
    private String goodsName;
    private int goodsNumber;
    private String goodsImageName;
    private String storeName;
    public UserOrder(){}

    public UserOrder(String userAccount, String orderNo, String orderTime, String deliveryAddress, String goodsPrice, String vipDerate, String actualPayment, String goodsName, int goodsNumber, String goodsImageName, String storeName) {
        this.userAccount = userAccount;
        this.orderNo = orderNo;
        this.orderTime = orderTime;
        this.deliveryAddress = deliveryAddress;
        this.orderPrice = goodsPrice;
        this.orderDerate = vipDerate;
        this.actualPayment = actualPayment;
        this.goodsName = goodsName;
        this.goodsNumber = goodsNumber;
        this.goodsImageName = goodsImageName;
        this.storeName = storeName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public String toString() {
        return "UserOrder{" +
                "userAccount='" + userAccount + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", goodsPrice='" + orderPrice + '\'' +
                ", vipDerate='" + orderDerate + '\'' +
                ", actualPayment='" + actualPayment + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsNumber='" + goodsNumber + '\'' +
                ", goodsImageName='" + goodsImageName + '\'' +
                ", storeName='" + storeName + '\'' +
                '}';
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderDerate() {
        return orderDerate;
    }

    public void setOrderDerate(String orderDerate) {
        this.orderDerate = orderDerate;
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

    public int getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(int goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getGoodsImageName() {
        return goodsImageName;
    }

    public void setGoodsImageName(String goodsImageName) {
        this.goodsImageName = goodsImageName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
