package com.project.campustaobao.vo;

import java.io.Serializable;

/**
 * 结果对象
 *
 * @author gt5b
 * @date 2023/5/23 23:51:36
 * @since 1.0
 */
public class ResultMessage<T> implements Serializable {
    private static final long serialVersionUID = 1029769657073759423L;
    public static final int SUCCESS_CODE = 1;
    public static final int ERROR_CODE = 0;
    public static final String SUCCESS_MSG = "执行成功";
    public static final String ERROR_MSG = "执行失败";

    /**
     * 执行结果状态码
     * 0：执行失败 、1：执行成功
     */
    private int code;

    /**
     * 执行结果提示信息
     */
    private String msg;

    /**
     * 执行结果数据
     */
    private T data;
    public ResultMessage(){}
    public ResultMessage(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 执行成功构建方法
     */
    public static <T> ResultMessage<T> buildSuccessMsg() {
        return new ResultMessage<T>(SUCCESS_CODE, SUCCESS_MSG, null);
    }

    public static <T> ResultMessage<T> buildSuccessMsg(String msg) {
        return new ResultMessage<T>(SUCCESS_CODE, msg, null);
    }

    public static <T> ResultMessage<T> buildSuccessMsg(T data) {
        return new ResultMessage<T>(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    public static <T> ResultMessage<T> buildSuccessMsg(String msg, T data) {
        return new ResultMessage<T>(SUCCESS_CODE, msg, data);
    }

    /**
     * 执行失败构建方法
     */
    public static <T> ResultMessage<T> buildErrorMsg() {
        return new ResultMessage<T>(ERROR_CODE, ERROR_MSG, null);
    }

    public static <T> ResultMessage<T> buildErrorMsg(String msg) {
        return new ResultMessage<T>(ERROR_CODE, msg, null);
    }

    public static <T> ResultMessage<T> buildErrorMsg(T data) {
        return new ResultMessage<T>(ERROR_CODE, ERROR_MSG, data);
    }

    public static <T> ResultMessage<T> buildErrorMsg(String msg, T data) {
        return new ResultMessage<T>(ERROR_CODE, msg, data);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}

