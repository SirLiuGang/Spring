package com.cn.lg.test.dao;

import com.cn.lg.test.entity.User;

/**
 * @author: 刘钢
 * @Date: 2019/3/3 17:45
 * @Description:
 */
public interface IUserDao {

    User getById(Long userId);
}
