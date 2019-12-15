package com.cn.lg.web.exception;

/**
 * 自定义异常
 * @author: 刘钢
 * @Date: 2019/3/25 0:26
 * @Description:
 */
public class MyException extends Exception {

    private static final long serialVersionUID = 6564955618050108376L;


    public MyException() {
        super();
    }

    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }
}
