package com.project.campustaobao.pojo;

public class Goods {
    private String goodsNo; //商品编号
    private String goodsName; //商品名称
    private String goodsType; //商品种类
    private String goodsPrice;  //商品价格
    private String goodsPurchasePrice;  //商品进价
    private String goodsBrand;  //商品品牌
    private String goodsImageName; //商品图片名称
    private String goodsWeight;  //商品重量
    private String goodsOrigin;  //商品产地
    private String goodsSpecialInfo;  //商品特有信息
    private int goodsNumber;  //商品(剩余)数量
    private int maxNumOfSinglePurchase; //单次购买的最大个数
    private String goodsMonthSales; //商品月销售额
    private String goodsPurchaseDate;  //商品进货日期
    //private String storeNo;  //商品所属的店铺编号
    private String storeName; // 店铺名字
    private String vipDerate; //vip会员减免费用
    public Goods(){}

    public Goods(String goodsNo, String goodsName) {
        this.goodsNo = goodsNo;
        this.goodsName = goodsName;
    }

    public Goods(String goodsNo, String goodsName, String goodsType, String goodsPrice, String goodsPurchasePrice) {
        this.goodsNo = goodsNo;
        this.goodsName = goodsName;
        this.goodsType = goodsType;
        this.goodsPrice = goodsPrice;
        this.goodsPurchasePrice = goodsPurchasePrice;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsPurchasePrice() {
        return goodsPurchasePrice;
    }

    public void setGoodsPurchasePrice(String goodsPurchasePrice) {
        this.goodsPurchasePrice = goodsPurchasePrice;
    }

    public String getGoodsBrand() {
        return goodsBrand;
    }

    public void setGoodsBrand(String goodsBrand) {
        this.goodsBrand = goodsBrand;
    }

    public String getGoodsImageName() {
        return goodsImageName;
    }

    public void setGoodsImageName(String goodsImageName) {
        this.goodsImageName = goodsImageName;
    }

    public String getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(String goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public String getGoodsOrigin() {
        return goodsOrigin;
    }

    public void setGoodsOrigin(String goodsOrigin) {
        this.goodsOrigin = goodsOrigin;
    }

    public String getGoodsSpecialInfo() {
        return goodsSpecialInfo;
    }

    public void setGoodsSpecialInfo(String goodsSpecialInfo) {
        this.goodsSpecialInfo = goodsSpecialInfo;
    }

    public int getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(int goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getGoodsMonthSales() {
        return goodsMonthSales;
    }

    public void setGoodsMonthSales(String goodsMonthSales) {
        this.goodsMonthSales = goodsMonthSales;
    }

    public String getGoodsPurchaseDate() {
        return goodsPurchaseDate;
    }

    public void setGoodsPurchaseDate(String goodsPurchaseDate) {
        this.goodsPurchaseDate = goodsPurchaseDate;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreNo(String storeName) {
        this.storeName = storeName;
    }

    public String getVipDerate() {
        return vipDerate;
    }

    public void setVipDerate(String vipDerate) {
        this.vipDerate = vipDerate;
    }

    public int getMaxNumOfSinglePurchase() {
        return maxNumOfSinglePurchase;
    }

    public void setMaxNumOfSinglePurchase(int maxNumOfSinglePurchase) {
        this.maxNumOfSinglePurchase = maxNumOfSinglePurchase;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsNo='" + goodsNo + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", goodsPrice='" + goodsPrice + '\'' +
                ", goodsPurchasePrice='" + goodsPurchasePrice + '\'' +
                ", goodsBrand='" + goodsBrand + '\'' +
                ", goodsImageName='" + goodsImageName + '\'' +
                ", goodsWeight='" + goodsWeight + '\'' +
                ", goodsOrigin='" + goodsOrigin + '\'' +
                ", goodsSpecialInfo='" + goodsSpecialInfo + '\'' +
                ", goodsNumber=" + goodsNumber +
                ", maxNumOfSinglePurchase=" + maxNumOfSinglePurchase +
                ", goodsMonthSales='" + goodsMonthSales + '\'' +
                ", goodsPurchaseDate='" + goodsPurchaseDate + '\'' +
                ", storeName='" + storeName + '\'' +
                ", vipDerate='" + vipDerate + '\'' +
                '}';
    }
}
