package com.cn.lg.test.dao.impl;

import com.cn.lg.test.dao.IUserDao;
import com.cn.lg.test.entity.User;
import org.springframework.stereotype.Service;

/**
 * 获取用户信息 Dao层
 * @author: 刘钢
 * @Date: 2019/3/3 17:45
 * @Description:
 */
@Service(value = "userDao")
public class UserDaoImpl implements IUserDao {
    @Override
    public User getById(Long userId) {
        return new User(userId, "张三", "男");
    }
}
