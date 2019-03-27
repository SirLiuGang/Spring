package com.cn.lg.web.service.impl;

import com.cn.lg.web.exception.MyException;
import com.cn.lg.web.service.ITestService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 测试用
 * @Auther: 刘钢
 * @Date: 2019/3/24 23:38
 * @Description:
 */
@Service(value = "testService")
public class TestServiceImpl implements ITestService {

    /**
     * 返回hello字符串
     * @return  hello
     */
    @Override
    public String sayHello() {
        return "hello";
    }

    /**
     * 同步操作
     */
    @Override
    public void sync() {
        this.async();
    }


    /**
     * 异步操作
     */
    @Override
    @Async
    public void async() {
        System.out.println("sleep 3s begin");
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sleep 3s end");
    }

    /**
     * 抛出自定义异常
     * @throws MyException
     */
    @Override
    public void throwException() throws MyException {
        throw new MyException("my exception");
    }
}
