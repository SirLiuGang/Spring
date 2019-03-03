package com.cn.lg.test.controller;

import com.cn.lg.test.entity.User;
import com.cn.lg.test.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户信息控制层
 * @Auther: 刘钢
 * @Date: 2019/3/3 17:38
 * @Description:
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Resource(name = "userService")
    private IUserService userService;

    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    @ResponseBody
    public User getUserById(Long userId) {
        return userService.getById(userId);
    }
}
