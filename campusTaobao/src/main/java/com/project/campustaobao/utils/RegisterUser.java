package com.project.campustaobao.utils;

public class RegisterUser {
    /**
     * 生成唯一的账号
     * @param tel 用户手机号
     * @return 字符串--生成的手机号
     */
    public static  String generateAccount(String tel){
        long hash = tel.hashCode();
        return hash > 0 ? String.valueOf(hash) : "sk" + (-hash);
    }

}
