package com.cn.lg.test.service.impl;

import com.cn.lg.test.dao.IUserDao;
import com.cn.lg.test.entity.User;
import com.cn.lg.test.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户信息 Service层
 * @Auther: 刘钢
 * @Date: 2019/3/3 17:39
 * @Description:
 */
@Service(value = "userService")
public class UserServiceImpl implements IUserService {

    @Resource(name = "userDao")
    private IUserDao userDao;

    @Override
    public User getById(Long userId) {
        return userDao.getById(userId);
    }
}
