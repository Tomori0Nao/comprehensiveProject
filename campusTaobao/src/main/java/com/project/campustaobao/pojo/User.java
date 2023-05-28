package com.project.campustaobao.pojo;

public class User {
    private String account;
    private String password;
    private String tel;
    private String name;
    private String balance;   //余额
    private String registerDate;  //注册时间
    private String lastLoginTime;   //用户上次登录时间
    private String totalConsumption;  //总的消费额
    private String avatar;    //头像昵称
    private boolean VIP;
    public User(){}

    /**
     * 不指定VIP，则默认非VIP
     * @param account 账号
     * @param password 密码
     */
    public User(String account, String password) {
        this.account = account;
        this.password = password;
        this.VIP = false;
    }

    public User(String account, String password, boolean VIP) {
        this.account = account;
        this.password = password;
        this.VIP = VIP;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isVIP() {
        return VIP;
    }

    public void setVIP(boolean VIP) {
        this.VIP = VIP;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(String totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
