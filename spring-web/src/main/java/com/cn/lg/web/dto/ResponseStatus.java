package com.cn.lg.web.dto;

import java.io.Serializable;

/**
 * 给前端的返回结果封装
 * @author: 刘钢
 * @Date: 2019/3/24 23:43
 * @Description:
 */
public class ResponseStatus<T> implements Serializable {

    private static final long serialVersionUID = 8097557455117095125L;

    private boolean result;

    private String msg;

    private T data;

    public ResponseStatus() {
    }

    public ResponseStatus(boolean result) {
        this.result = result;
    }

    public ResponseStatus(boolean result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    public ResponseStatus(boolean result, String msg, T data) {
        this.result = result;
        this.msg = msg;
        this.data = data;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
