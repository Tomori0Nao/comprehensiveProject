package com.project.campustaobao.pojo;

/**
 * @author gt5b
 * @version 1.0
 *  2023-5-28 23:53
 */
public class DeliveryAddress {
    private String addressNo;
    private String userAccount;
    private String consigneeName;
    private String consigneeTel;
    private String address;

    public DeliveryAddress() {
    }

    public DeliveryAddress(String addressNo, String userAccount, String consigneeName, String consigneeTel, String address) {
        this.addressNo = addressNo;
        this.userAccount = userAccount;
        this.consigneeName = consigneeName;
        this.consigneeTel = consigneeTel;
        this.address = address;
    }

    public String getAddressNo() {
        return addressNo;
    }

    public void setAddressNo(String addressNo) {
        this.addressNo = addressNo;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeTel() {
        return consigneeTel;
    }

    public void setConsigneeTel(String consigneeTel) {
        this.consigneeTel = consigneeTel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
}
