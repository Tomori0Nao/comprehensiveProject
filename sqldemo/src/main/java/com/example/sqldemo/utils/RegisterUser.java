package com.example.sqldemo.utils;

public class RegisterUser {
    public static  String register(String tel){
        long hash = tel.hashCode();
        return hash > 0 ? String.valueOf(hash) : "sk" + (-hash);
    }

}
