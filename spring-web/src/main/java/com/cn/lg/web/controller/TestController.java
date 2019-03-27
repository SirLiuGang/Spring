package com.cn.lg.web.controller;

import com.cn.lg.web.dto.ResponseStatus;
import com.cn.lg.web.exception.MyException;
import com.cn.lg.web.service.ITestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 测试用Controller
 * @Auther: 刘钢
 * @Date: 2019/3/24 23:37
 * @Description:
 */
@RestController
@RequestMapping("/test")
public class TestController extends BaseController{

    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @Resource(name = "testService")
    private ITestService testService;

    @GetMapping("/async")
    public ResponseStatus async() {
        String hello = testService.sayHello();  // 正常方法调用
        testService.sync();     // 同步操作
        testService.async();    // 异步操作
        return new ResponseStatus<>(true, "异步方法调用成功", hello);
    }

    @GetMapping("myexception")
    public ResponseStatus myexception() throws MyException {
        testService.throwException();
        return new ResponseStatus(true, "方法调用成功");
    }

}
