package com.project.campustaobao.pojo;

/**
 * 购物车里的商品
 */
public class ShoppingCartGoods {
    private String cartGoodsNo;
    private String cartGoodsName;
    private String cartGoodsImageName;
    private String cartGoodsStoreName;
    private String cartGoodsPrice;
    private String totalCost;
    private int cartGoodsNumber;
    private int maxNumOfSinglePurchase;
    private String vipDerate;
    public ShoppingCartGoods(){}

    public String getCartGoodsNo() {
        return cartGoodsNo;
    }

    public void setCartGoodsNo(String cartGoodsNo) {
        this.cartGoodsNo = cartGoodsNo;
    }

    public String getCartGoodsName() {
        return cartGoodsName;
    }

    public void setCartGoodsName(String cartGoodsName) {
        this.cartGoodsName = cartGoodsName;
    }

    public String getCartGoodsImageName() {
        return cartGoodsImageName;
    }

    public void setCartGoodsImageName(String cartGoodsImageName) {
        this.cartGoodsImageName = cartGoodsImageName;
    }

    public String getCartGoodsStoreName() {
        return cartGoodsStoreName;
    }

    public void setCartGoodsStoreName(String cartGoodsStoreName) {
        this.cartGoodsStoreName = cartGoodsStoreName;
    }

    public String getCartGoodsPrice() {
        return cartGoodsPrice;
    }

    public void setCartGoodsPrice(String cartGoodsPrice) {
        this.cartGoodsPrice = cartGoodsPrice;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public int getCartGoodsNumber() {
        return cartGoodsNumber;
    }

    public void setCartGoodsNumber(int cartGoodsNumber) {
        this.cartGoodsNumber = cartGoodsNumber;
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

    public ShoppingCartGoods(String cartGoodsName, String cartGoodsImageName, String cartGoodsStoreName, String cartGoodsPrice, String totalCost, int cartGoodsNumber,  String vipDerate,int maxNumOfSinglePurchase) {
        this.cartGoodsName = cartGoodsName;
        this.cartGoodsImageName = cartGoodsImageName;
        this.cartGoodsStoreName = cartGoodsStoreName;
        this.cartGoodsPrice = cartGoodsPrice;
        this.totalCost = totalCost;
        this.cartGoodsNumber = cartGoodsNumber;
        this.maxNumOfSinglePurchase = maxNumOfSinglePurchase;
        this.vipDerate = vipDerate;
    }

    public ShoppingCartGoods(String cartGoodsNo, String cartGoodsName, String cartGoodsImageName, String cartGoodsStoreName, String cartGoodsPrice, String totalCost, int cartGoodsNumber, int maxNumOfSinglePurchase, String vipDerate) {
        this.cartGoodsNo = cartGoodsNo;
        this.cartGoodsName = cartGoodsName;
        this.cartGoodsImageName = cartGoodsImageName;
        this.cartGoodsStoreName = cartGoodsStoreName;
        this.cartGoodsPrice = cartGoodsPrice;
        this.totalCost = totalCost;
        this.cartGoodsNumber = cartGoodsNumber;
        this.maxNumOfSinglePurchase = maxNumOfSinglePurchase;
        this.vipDerate = vipDerate;
    }
}
