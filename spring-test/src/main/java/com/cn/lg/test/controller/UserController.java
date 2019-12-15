package com.cn.lg.test.controller;

import com.cn.lg.test.entity.User;
import com.cn.lg.test.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户信息控制层
 * @author: 刘钢
 * @Date: 2019/3/3 17:38
 * @Description:
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Resource(name = "userService")
    private IUserService userService;

    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    @ResponseBody
    public User getUserById(Long userId) {
        LOG.info("userId = {}", userId);
        return userService.getById(userId);
    }

    @RequestMapping(value = "/getUserName", method = RequestMethod.GET)
    @ResponseBody
    public User getUserName(String userName) {
        return new User(1L, userName, "男");
    }
}
