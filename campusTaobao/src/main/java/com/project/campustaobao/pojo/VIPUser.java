package com.project.campustaobao.pojo;

public class VIPUser {
    private String vipAccount;  //vip账号
    private String expirationTime;  //有效时间
    private String integral; //积分
    private String saving;  //节省的费用

    public VIPUser(){}

    public VIPUser(String vipAccount, String expirationTime) {
        this.vipAccount = vipAccount;
        this.expirationTime = expirationTime;
    }

    public String getVipAccount() {
        return vipAccount;
    }

    public void setVipAccount(String vipAccount) {
        this.vipAccount = vipAccount;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getSaving() {
        return saving;
    }

    public void setSaving(String saving) {
        this.saving = saving;
    }

    @Override
    public String toString() {
        return "VIPUser{" +
                "vipAccount='" + vipAccount + '\'' +
                ", expirationTime='" + expirationTime + '\'' +
                ", integral='" + integral + '\'' +
                ", saving='" + saving + '\'' +
                '}';
    }
}
