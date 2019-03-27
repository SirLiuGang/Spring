package com.cn.lg.web.controller;

import com.cn.lg.web.dto.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Auther: 刘钢
 * @Date: 2019/3/24 23:43
 * @Description:
 */
public class BaseController {

    @ExceptionHandler
    public ResponseStatus ExceptionHandler(Exception e) {
        String msg;
        if(e instanceof NullPointerException) {
            msg = e.getMessage();
        } else {
            msg = e.getMessage();
        }
        return new ResponseStatus<>(false, msg);
    }
}
