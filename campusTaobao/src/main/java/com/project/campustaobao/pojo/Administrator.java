package com.project.campustaobao.pojo;

/**
 * @author gtb
 * @version 1.0
 * 2023-5-30 11:21
 */
public class Administrator {
    private static final String SUPER_ADMINISTRATOR  = "系统管理员";
    private static final String COMMODITY_SPECIALIST = "商品专员";
    private static final String MARKETING_MANAGER = "营销经理";
    private String adminType;  //管理员类别
    private String adminAccount;//管理员账号
    private String adminPassword; //管理员密码
    private String adminNakeName;//管理员昵称
    private String registerDate;//管理员注册时间
    private boolean isBaned;   //true则说明管理员被禁用

    public Administrator() {
    }

    public Administrator(String adminType, String adminAccount, String adminPassword, String adminNakeName, String registerDate, boolean isBaned) {
        this.adminType = adminType;
        this.adminAccount = adminAccount;
        this.adminPassword = adminPassword;
        this.adminNakeName = adminNakeName;
        this.registerDate = registerDate;
        this.isBaned = isBaned;
    }

    public String getAdminType() {
        return adminType;
    }

    public void setAdminType(String adminType) {
        this.adminType = adminType;
    }

    public String getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminNakeName() {
        return adminNakeName;
    }

    public void setAdminNakeName(String adminNakeName) {
        this.adminNakeName = adminNakeName;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public boolean isBaned() {
        return isBaned;
    }

    public void setBaned(boolean baned) {
        isBaned = baned;
    }
}
