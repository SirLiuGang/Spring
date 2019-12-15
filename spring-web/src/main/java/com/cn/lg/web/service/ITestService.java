package com.cn.lg.web.service;

import com.cn.lg.web.exception.MyException;

/**
 * 测试用
 * @author: 刘钢
 * @Date: 2019/3/24 23:39
 * @Description:
 */
public interface ITestService {

    /**
     * 返回hello字符串
     * @return
     */
    String sayHello();

    /**
     * 同步操作
     */
    void sync();

    /**
     * 异步操作
     */
    void async();

    /**
     * 抛出自定义异常
     * @throws MyException
     */
    void throwException() throws MyException;
}
